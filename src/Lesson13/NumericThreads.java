package Lesson13;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class NumericThreads {
    // Константа для порога разбиения задач
    private static final int THRESHOLD = 10_000;
    // Максимальное количество подзадач, ограниченное числом ядер
    private static final int MAX_SPLITS = Math.min(Runtime.getRuntime().availableProcessors(), 8);

    public static void main(String[] args) {
        // Создаем пул потоков с количеством потоков, равным числу логических ядер
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        System.out.println("Создан пул потоков с " + Runtime.getRuntime().availableProcessors() + " потоками");

        // Генерируем входные данные
        Random random = new Random();
        List<Integer> inputData = random.ints(100_000, 1, 1000).boxed().toList();

        // Типы задач для выполнения
        String[] methods = {"3or5", "prime", "average", "same"};

        for (String method : methods) {
            System.out.println("\nВыполнение задачи: " + method);
            // Замер времени выполнения всей операции
            long startTime = System.nanoTime();

            try {
                switch (method) {
                    case "3or5", "prime" -> {
                        // Список для хранения CompletableFuture<List<Integer>>
                        List<CompletableFuture<List<Integer>>> futures = new ArrayList<>();
                        // Динамическое разбиение данных на подзадачи
                        int size = inputData.size();
                        int chunkSize = Math.max(size / MAX_SPLITS, 1);
                        for (int i = 0; i < size; i += chunkSize) {
                            int end = Math.min(i + chunkSize, size);
                            List<Integer> subList = inputData.subList(i, end);
                            int finalI = i;
                            CompletableFuture<List<Integer>> future = CompletableFuture.supplyAsync(
                                    () -> new CalculationTask(subList, finalI / chunkSize + 1, method).callList(),
                                    executor);
                            futures.add(future);
                        }
                        // Асинхронно собираем результаты
                        List<Integer> resultList = futures.stream()
                                .map(CompletableFuture::join)
                                .flatMap(List::stream)
                                .toList();
                        // Выводим результат
                        System.out.println("Результат задачи " + method + ": " + resultList.size() + " элементов");
                    }
                    case "average" -> {
                        // Список для хранения CompletableFuture<Double>
                        List<CompletableFuture<Double>> futures = new ArrayList<>();
                        int size = inputData.size();
                        int chunkSize = Math.max(size / MAX_SPLITS, 1);
                        for (int i = 0; i < size; i += chunkSize) {
                            int end = Math.min(i + chunkSize, size);
                            List<Integer> subList = inputData.subList(i, end);
                            int finalI = i;
                            CompletableFuture<Double> future = CompletableFuture.supplyAsync(
                                    () -> new CalculationTask(subList, finalI / chunkSize + 1, method).callDouble(),
                                    executor);
                            futures.add(future);
                        }
                        // Собираем взвешенное среднее
                        double result = computeWeightedAverage(futures, size, chunkSize);
                        // Выводим результат
                        System.out.println("Результат задачи " + method + ": " + String.format("%.2f", result));
                    }
                    case "same" -> {
                        // Список для хранения CompletableFuture<Long>
                        List<CompletableFuture<Long>> futures = new ArrayList<>();
                        int size = inputData.size();
                        int chunkSize = Math.max(size / MAX_SPLITS, 1);
                        for (int i = 0; i < size; i += chunkSize) {
                            int end = Math.min(i + chunkSize, size);
                            List<Integer> subList = inputData.subList(i, end);
                            int finalI = i;
                            CompletableFuture<Long> future = CompletableFuture.supplyAsync(
                                    () -> new CalculationTask(subList, finalI / chunkSize + 1, method).callLong(),
                                    executor);
                            futures.add(future);
                        }
                        // Находим максимум
                        long result = futures.stream()
                                .map(CompletableFuture::join)
                                .mapToLong(Long::longValue)
                                .max()
                                .orElse(0L);
                        // Выводим результат
                        System.out.println("Результат задачи " + method + ": " + result);
                    }
                    default -> throw new IllegalArgumentException("Неизвестный метод: " + method);
                }

                // Вычисляем время выполнения операции
                long duration = (System.nanoTime() - startTime) / 1_000_000;
                System.out.println("Время выполнения задачи " + method + ": " + duration + " мс");
            } catch (Exception e) {
                System.err.println("Ошибка при обработке результата " + method + ": " + e.getMessage());
            }
        }

        // Завершаем работу пула потоков
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                System.out.println("Пул потоков принудительно завершен");
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            System.err.println("Ожидание прервано: " + e.getMessage());
        }

        System.out.println("\nВсе задачи завершены");
    }

    // Метод для вычисления взвешенного среднего (устраняет дублирование кода)
    private static double computeWeightedAverage(List<CompletableFuture<Double>> futures, int size, int chunkSize) {
        double sum = 0.0;
        int totalSize = 0;
        for (CompletableFuture<Double> future : futures) {
            Double avg = future.join();
            int subSize = Math.min(chunkSize, size - totalSize);
            sum += avg * subSize;
            totalSize += subSize;
        }
        return totalSize > 0 ? sum / totalSize : 0.0;
    }

    // Класс, реализующий интерфейсы Callable для разных типов возвращаемых значений
    static class CalculationTask {
        private final List<Integer> data;
        private final int taskId;
        private final String method;

        public CalculationTask(List<Integer> data, int taskId, String method) {
            this.data = data;
            this.taskId = taskId;
            this.method = method;
        }

        public List<Integer> callList() {
            long startTime = System.nanoTime();
            List<Integer> result;
            if (data.size() <= THRESHOLD) {
                result = switch (method) {
                    case "3or5" -> compute3or5();
                    case "prime" -> computePrime();
                    default -> throw new IllegalArgumentException("Неизвестный метод для List: " + method);
                };
            } else {
                ExecutorService subExecutor = Executors.newFixedThreadPool(MAX_SPLITS);
                List<CompletableFuture<List<Integer>>> subFutures = new ArrayList<>();
                int size = data.size();
                int chunkSize = Math.max(size / MAX_SPLITS, 1);

                for (int i = 0; i < size; i += chunkSize) {
                    int end = Math.min(i + chunkSize, size);
                    List<Integer> subList = data.subList(i, end);
                    int finalI = i;
                    CompletableFuture<List<Integer>> subFuture = CompletableFuture.supplyAsync(
                            () -> new CalculationTask(subList, taskId * 10 + finalI / chunkSize + 1, method).callList(),
                            subExecutor);
                    subFutures.add(subFuture);
                }

                result = subFutures.stream()
                        .map(CompletableFuture::join)
                        .flatMap(List::stream)
                        .collect(Collectors.toList());

                subExecutor.shutdown();
                try {
                    if (!subExecutor.awaitTermination(30, TimeUnit.SECONDS)) {
                        subExecutor.shutdownNow();
                        System.out.println("Подпулов для подзадачи " + taskId + " (" + method + ") принудительно завершен");
                    }
                } catch (InterruptedException e) {
                    subExecutor.shutdownNow();
                    System.err.println("Ожидание подзадачи " + taskId + " (" + method + ") прервано: " + e.getMessage());
                }
            }

            long duration = (System.nanoTime() - startTime) / 1_000_000;
            System.out.println("Подзадача " + taskId + " (" + method + ") выполнена в потоке " +
                    Thread.currentThread().getName() + " (размер: " + data.size() + ", время: " + duration + " мс)");
            return result;
        }

        public Double callDouble() {
            long startTime = System.nanoTime();
            double result;
            if (data.size() <= THRESHOLD) {
                result = computeAverage();
            } else {
                ExecutorService subExecutor = Executors.newFixedThreadPool(MAX_SPLITS);
                List<CompletableFuture<Double>> subFutures = new ArrayList<>();
                int size = data.size();
                int chunkSize = Math.max(size / MAX_SPLITS, 1);

                for (int i = 0; i < size; i += chunkSize) {
                    int end = Math.min(i + chunkSize, size);
                    List<Integer> subList = data.subList(i, end);
                    int finalI = i;
                    CompletableFuture<Double> subFuture = CompletableFuture.supplyAsync(
                            () -> new CalculationTask(subList, taskId * 10 + finalI / chunkSize + 1, method).callDouble(),
                            subExecutor);
                    subFutures.add(subFuture);
                }

                result = computeWeightedAverage(subFutures, size, chunkSize);

                subExecutor.shutdown();
                try {
                    if (!subExecutor.awaitTermination(30, TimeUnit.SECONDS)) {
                        subExecutor.shutdownNow();
                        System.out.println("Подпулов для подзадачи " + taskId + " (" + method + ") принудительно завершен");
                    }
                } catch (InterruptedException e) {
                    subExecutor.shutdownNow();
                    System.err.println("Ожидание подзадачи " + taskId + " (" + method + ") прервано: " + e.getMessage());
                }
            }

            long duration = (System.nanoTime() - startTime) / 1_000_000;
            System.out.println("Подзадача " + taskId + " (" + method + ") выполнена в потоке " +
                    Thread.currentThread().getName() + " (размер: " + data.size() + ", время: " + duration + " мс)");
            return result;
        }

        public Long callLong() {
            long startTime = System.nanoTime();
            long result;
            if (data.size() <= THRESHOLD) {
                result = computeSame();
            } else {
                ExecutorService subExecutor = Executors.newFixedThreadPool(MAX_SPLITS);
                List<CompletableFuture<Long>> subFutures = new ArrayList<>();
                int size = data.size();
                int chunkSize = Math.max(size / MAX_SPLITS, 1);

                for (int i = 0; i < size; i += chunkSize) {
                    int end = Math.min(i + chunkSize, size);
                    List<Integer> subList = data.subList(i, end);
                    int finalI = i;
                    CompletableFuture<Long> subFuture = CompletableFuture.supplyAsync(
                            () -> new CalculationTask(subList, taskId * 10 + finalI / chunkSize + 1, method).callLong(),
                            subExecutor);
                    subFutures.add(subFuture);
                }

                result = subFutures.stream()
                        .map(CompletableFuture::join)
                        .mapToLong(Long::longValue)
                        .max()
                        .orElse(0L);

                subExecutor.shutdown();
                try {
                    if (!subExecutor.awaitTermination(30, TimeUnit.SECONDS)) {
                        subExecutor.shutdownNow();
                        System.out.println("Подпулов для подзадачи " + taskId + " (" + method + ") принудительно завершен");
                    }
                } catch (InterruptedException e) {
                    subExecutor.shutdownNow();
                    System.err.println("Ожидание подзадачи " + taskId + " (" + method + ") прервано: " + e.getMessage());
                }
            }

            long duration = (System.nanoTime() - startTime) / 1_000_000;
            System.out.println("Подзадача " + taskId + " (" + method + ") выполнена в потоке " +
                    Thread.currentThread().getName() + " (размер: " + data.size() + ", время: " + duration + " мс)");
            return result;
        }

        private List<Integer> compute3or5() {
            return data.stream()
                    .filter(this::isDivisibleBy3Or5)
                    .collect(Collectors.toList());
        }

        private List<Integer> computePrime() {
            return data.stream()
                    .filter(this::isPrime)
                    .collect(Collectors.toList());
        }

        private double computeAverage() {
            return data.stream()
                    .mapToDouble(Integer::doubleValue)
                    .average()
                    .orElse(0.0);
        }

        private long computeSame() {
            Map<Integer, Long> frequencyMap = data.stream()
                    .collect(Collectors.groupingBy(
                            n -> n,
                            Collectors.counting()
                    ));
            return frequencyMap.values().stream()
                    .max(Long::compare)
                    .orElse(0L);
        }

        private boolean isDivisibleBy3Or5(int num) {
            return num % 3 == 0 || num % 5 == 0;
        }

        private boolean isPrime(int num) {
            if (num < 2) return false;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) return false;
            }
            return true;
        }
    }
}