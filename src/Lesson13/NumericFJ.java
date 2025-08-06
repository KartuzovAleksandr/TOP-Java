package Lesson13;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class NumericFJ {
    // Константа для порога разбиения задач
    private static final int THRESHOLD = 10_000;
    // Максимальное количество подзадач, ограниченное числом ядер
    private static final int MAX_SPLITS = Math.min(Runtime.getRuntime().availableProcessors(), 8);

    public static void main(String[] args) {
        // Генерируем входные данные
        Random random = new Random();
        List<Integer> inputData = random.ints(100_000, 1, 1000).boxed().toList();

        // Типы задач для выполнения
        String[] methods = {"3or5", "prime", "average", "same"};

        // Выполнение с CompletableFuture
        System.out.println("\n=== Выполнение с CompletableFuture ===");
        runWithCompletableFuture(inputData, methods);

        // Выполнение с ForkJoinPool
        System.out.println("\n=== Выполнение с ForkJoinPool ===");
        runWithForkJoin(inputData, methods);
    }

    // Реализация с CompletableFuture
    private static void runWithCompletableFuture(List<Integer> inputData, String[] methods) {
        try (ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {
            System.out.println("Создан пул потоков с " + Runtime.getRuntime().availableProcessors() + " потоками");

            for (String method : methods) {
                System.out.println("\nВыполнение задачи: " + method);
                long startTime = System.nanoTime();

                try {
                    switch (method) {
                        case "3or5", "prime" -> {
                            List<CompletableFuture<List<Integer>>> futures = new ArrayList<>();
                            int size = inputData.size();
                            int chunkSize = Math.max(size / MAX_SPLITS, 1);
                            for (int i = 0; i < size; i += chunkSize) {
                                int end = Math.min(i + chunkSize, size);
                                List<Integer> subList = inputData.subList(i, end);
                                int finalI = i;
                                CompletableFuture<List<Integer>> future = CompletableFuture.supplyAsync(
                                        () -> new CompletableFutureTask(subList, finalI / chunkSize + 1, method).callList(),
                                        executor);
                                futures.add(future);
                            }
                            List<Integer> resultList = futures.stream()
                                    .map(CompletableFuture::join)
                                    .flatMap(List::stream)
                                    .toList();
                            System.out.println("Результат задачи " + method + ": " + resultList.size() + " элементов");
                        }
                        case "average" -> {
                            List<CompletableFuture<Double>> futures = new ArrayList<>();
                            int size = inputData.size();
                            int chunkSize = Math.max(size / MAX_SPLITS, 1);
                            for (int i = 0; i < size; i += chunkSize) {
                                int end = Math.min(i + chunkSize, size);
                                List<Integer> subList = inputData.subList(i, end);
                                int finalI = i;
                                CompletableFuture<Double> future = CompletableFuture.supplyAsync(
                                        () -> new CompletableFutureTask(subList, finalI / chunkSize + 1, method).callDouble(),
                                        executor);
                                futures.add(future);
                            }
                            double result = computeWeightedAverage(futures, size, chunkSize);
                            System.out.println("Результат задачи " + method + ": " + String.format("%.2f", result));
                        }
                        case "same" -> {
                            List<CompletableFuture<Long>> futures = new ArrayList<>();
                            int size = inputData.size();
                            int chunkSize = Math.max(size / MAX_SPLITS, 1);
                            for (int i = 0; i < size; i += chunkSize) {
                                int end = Math.min(i + chunkSize, size);
                                List<Integer> subList = inputData.subList(i, end);
                                int finalI = i;
                                CompletableFuture<Long> future = CompletableFuture.supplyAsync(
                                        () -> new CompletableFutureTask(subList, finalI / chunkSize + 1, method).callLong(),
                                        executor);
                                futures.add(future);
                            }
                            long result = futures.stream()
                                    .map(CompletableFuture::join)
                                    .mapToLong(Long::longValue)
                                    .max()
                                    .orElse(0L);
                            System.out.println("Результат задачи " + method + ": " + result);
                        }
                        default -> throw new IllegalArgumentException("Неизвестный метод: " + method);
                    }

                    long duration = (System.nanoTime() - startTime) / 1_000_000;
                    System.out.println("Время выполнения задачи " + method + ": " + duration + " мс");
                } catch (Exception e) {
                    System.err.println("Ошибка при обработке результата " + method + ": " + e.getMessage());
                }
            }
        }
    }

    // Реализация с ForkJoinPool
    private static void runWithForkJoin(List<Integer> inputData, String[] methods) {
        try (ForkJoinPool pool = new ForkJoinPool(MAX_SPLITS)) {
            System.out.println("Создан ForkJoinPool с параллелизмом " + pool.getParallelism());

            for (String method : methods) {
                System.out.println("\nВыполнение задачи: " + method);
                long startTime = System.nanoTime();

                try {
                    switch (method) {
                        case "3or5", "prime" -> {
                            List<Integer> resultList = pool.invoke(new ForkJoinTaskList(inputData, method));
                            System.out.println("Результат задачи " + method + ": " + resultList.size() + " элементов");
                        }
                        case "average" -> {
                            double result = pool.invoke(new ForkJoinTaskDouble(inputData));
                            System.out.println("Результат задачи " + method + ": " + String.format("%.2f", result));
                        }
                        case "same" -> {
                            long result = pool.invoke(new ForkJoinTaskLong(inputData));
                            System.out.println("Результат задачи " + method + ": " + result);
                        }
                        default -> throw new IllegalArgumentException("Неизвестный метод: " + method);
                    }

                    long duration = (System.nanoTime() - startTime) / 1_000_000;
                    System.out.println("Время выполнения задачи " + method + ": " + duration + " мс");
                } catch (Exception e) {
                    System.err.println("Ошибка при обработке результата " + method + ": " + e.getMessage());
                }
            }
        }
    }

    // Метод для вычисления взвешенного среднего (для CompletableFuture)
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

    // Класс для задач CompletableFuture
    static class CompletableFutureTask {
        private final List<Integer> data;
        private final int taskId;
        private final String method;

        public CompletableFutureTask(List<Integer> data, int taskId, String method) {
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
                try (ExecutorService subExecutor = Executors.newFixedThreadPool(MAX_SPLITS)) {
                    List<CompletableFuture<List<Integer>>> subFutures = new ArrayList<>();
                    int size = data.size();
                    int chunkSize = Math.max(size / MAX_SPLITS, 1);

                    for (int i = 0; i < size; i += chunkSize) {
                        int end = Math.min(i + chunkSize, size);
                        List<Integer> subList = data.subList(i, end);
                        int finalI = i;
                        CompletableFuture<List<Integer>> subFuture = CompletableFuture.supplyAsync(
                                () -> new CompletableFutureTask(subList, taskId * 10 + finalI / chunkSize + 1, method).callList(),
                                subExecutor);
                        subFutures.add(subFuture);
                    }

                    result = subFutures.stream()
                            .map(CompletableFuture::join)
                            .flatMap(List::stream)
                            .collect(Collectors.toList());
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
                try (ExecutorService subExecutor = Executors.newFixedThreadPool(MAX_SPLITS)) {
                    List<CompletableFuture<Double>> subFutures = new ArrayList<>();
                    int size = data.size();
                    int chunkSize = Math.max(size / MAX_SPLITS, 1);

                    for (int i = 0; i < size; i += chunkSize) {
                        int end = Math.min(i + chunkSize, size);
                        List<Integer> subList = data.subList(i, end);
                        int finalI = i;
                        CompletableFuture<Double> subFuture = CompletableFuture.supplyAsync(
                                () -> new CompletableFutureTask(subList, taskId * 10 + finalI / chunkSize + 1, method).callDouble(),
                                subExecutor);
                        subFutures.add(subFuture);
                    }

                    result = computeWeightedAverage(subFutures, size, chunkSize);
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
                try (ExecutorService subExecutor = Executors.newFixedThreadPool(MAX_SPLITS)) {
                    List<CompletableFuture<Long>> subFutures = new ArrayList<>();
                    int size = data.size();
                    int chunkSize = Math.max(size / MAX_SPLITS, 1);

                    for (int i = 0; i < size; i += chunkSize) {
                        int end = Math.min(i + chunkSize, size);
                        List<Integer> subList = data.subList(i, end);
                        int finalI = i;
                        CompletableFuture<Long> subFuture = CompletableFuture.supplyAsync(
                                () -> new CompletableFutureTask(subList, taskId * 10 + finalI / chunkSize + 1, method).callLong(),
                                subExecutor);
                        subFutures.add(subFuture);
                    }

                    result = subFutures.stream()
                            .map(CompletableFuture::join)
                            .mapToLong(Long::longValue)
                            .max()
                            .orElse(0L);
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

    // Класс для задач ForkJoin (List<Integer>)
    static class ForkJoinTaskList extends RecursiveTask<List<Integer>> {
        private final List<Integer> data;
        private final String method;

        public ForkJoinTaskList(List<Integer> data, String method) {
            this.data = data;
            this.method = method;
        }

        @Override
        protected List<Integer> compute() {
            long startTime = System.nanoTime();
            List<Integer> result;
            if (data.size() <= THRESHOLD) {
                result = switch (method) {
                    case "3or5" -> data.stream()
                            .filter(n -> n % 3 == 0 || n % 5 == 0)
                            .collect(Collectors.toList());
                    case "prime" -> data.stream()
                            .filter(this::isPrime)
                            .collect(Collectors.toList());
                    default -> throw new IllegalArgumentException("Неизвестный метод для List: " + method);
                };
            } else {
                int size = data.size();
                int chunkSize = Math.max(size / MAX_SPLITS, 1);
                List<ForkJoinTaskList> tasks = new ArrayList<>();
                for (int i = 0; i < size; i += chunkSize) {
                    int end = Math.min(i + chunkSize, size);
                    ForkJoinTaskList task = new ForkJoinTaskList(data.subList(i, end), method);
                    tasks.add(task);
                    task.fork();
                }

                result = new ArrayList<>();
                for (ForkJoinTaskList task : tasks) {
                    result.addAll(task.join());
                }
            }

            long duration = (System.nanoTime() - startTime) / 1_000_000;
            System.out.println("ForkJoin задача (" + method + ") выполнена в потоке " +
                    Thread.currentThread().getName() + " (размер: " + data.size() + ", время: " + duration + " мс)");
            return result;
        }

        private boolean isPrime(int num) {
            if (num < 2) return false;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) return false;
            }
            return true;
        }
    }

    // Класс для задач ForkJoin (Double)
    static class ForkJoinTaskDouble extends RecursiveTask<Double> {
        private final List<Integer> data;

        public ForkJoinTaskDouble(List<Integer> data) {
            this.data = data;
        }

        @Override
        protected Double compute() {
            long startTime = System.nanoTime();
            double result;
            if (data.size() <= THRESHOLD) {
                result = data.stream()
                        .mapToDouble(Integer::doubleValue)
                        .average()
                        .orElse(0.0);
            } else {
                int size = data.size();
                int chunkSize = Math.max(size / MAX_SPLITS, 1);
                List<ForkJoinTaskDouble> tasks = new ArrayList<>();
                for (int i = 0; i < size; i += chunkSize) {
                    int end = Math.min(i + chunkSize, size);
                    ForkJoinTaskDouble task = new ForkJoinTaskDouble(data.subList(i, end));
                    tasks.add(task);
                    task.fork();
                }

                double sum = 0.0;
                int totalSize = 0;
                for (ForkJoinTaskDouble task : tasks) {
                    double avg = task.join();
                    int subSize = Math.min(chunkSize, size - totalSize);
                    sum += avg * subSize;
                    totalSize += subSize;
                }
                result = totalSize > 0 ? sum / totalSize : 0.0;
            }

            long duration = (System.nanoTime() - startTime) / 1_000_000;
            System.out.println("ForkJoin задача (average) выполнена в потоке " +
                    Thread.currentThread().getName() + " (размер: " + data.size() + ", время: " + duration + " мс)");
            return result;
        }
    }

    // Класс для задач ForkJoin (Long)
    static class ForkJoinTaskLong extends RecursiveTask<Long> {
        private final List<Integer> data;

        public ForkJoinTaskLong(List<Integer> data) {
            this.data = data;
        }

        @Override
        protected Long compute() {
            long startTime = System.nanoTime();
            long result;
            if (data.size() <= THRESHOLD) {
                Map<Integer, Long> frequencyMap = data.stream()
                        .collect(Collectors.groupingBy(
                                n -> n,
                                Collectors.counting()
                        ));
                result = frequencyMap.values().stream()
                        .max(Long::compare)
                        .orElse(0L);
            } else {
                int size = data.size();
                int chunkSize = Math.max(size / MAX_SPLITS, 1);
                List<ForkJoinTaskLong> tasks = new ArrayList<>();
                for (int i = 0; i < size; i += chunkSize) {
                    int end = Math.min(i + chunkSize, size);
                    ForkJoinTaskLong task = new ForkJoinTaskLong(data.subList(i, end));
                    tasks.add(task);
                    task.fork();
                }

                result = tasks.stream()
                        .map(ForkJoinTask::join)
                        .mapToLong(Long::longValue)
                        .max()
                        .orElse(0L);
            }

            long duration = (System.nanoTime() - startTime) / 1_000_000;
            System.out.println("ForkJoin задача (same) выполнена в потоке " +
                    Thread.currentThread().getName() + " (размер: " + data.size() + ", время: " + duration + " мс)");
            return result;
        }
    }
}