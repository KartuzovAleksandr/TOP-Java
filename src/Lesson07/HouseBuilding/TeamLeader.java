package Lesson07.HouseBuilding;

import java.util.HashMap;
import java.util.Map;

public class TeamLeader implements IWorker {
    private Map<String, Integer> plan;

    public TeamLeader() {
        plan = new HashMap<>();
        plan.put("Фундамент", 1);
        plan.put("Стена", 4);
        plan.put("Дверь", 1);
        plan.put("Окно", 4);
        plan.put("Крыша", 1);
    }

    @Override
    public void work(House house) {
        int totalParts = getTotalParts();
        int builtParts = calculateBuiltParts(house);

        System.out.println("\nОтчет бригадира о состоянии строительства:");
        System.out.println("Фундамент: " + (house.getBasement().isBuilt() ? "построен" : "не построен"));
        System.out.println("Стены: " + house.getWalls().size() + " из " + plan.get("Стена") + " построено");
        System.out.println("Двери: " + house.getDoors().size() + " из " + plan.get("Дверь") + " установлено");
        System.out.println("Окна: " + house.getWindows().size() + " из " + plan.get("Окно") + " установлено");
        System.out.println("Крыша: " + (house.getRoof().isBuilt() ? "построена" : "не построена"));

        System.out.println("Построено " + builtParts + " из " + totalParts + " частей дома.\n");
    }

    private int getTotalParts() {
        return plan.values().stream().mapToInt(Integer::intValue).sum();
    }

    private int calculateBuiltParts(House house) {
        int builtParts = 0;

        if (house.getBasement().isBuilt()) {
            builtParts += plan.get("Фундамент");
        }

        builtParts += Math.min(house.getWalls().size(), plan.get("Стена"));
        builtParts += Math.min(house.getDoors().size(), plan.get("Дверь"));
        builtParts += Math.min(house.getWindows().size(), plan.get("Окно"));

        if (house.getRoof().isBuilt()) {
            builtParts += plan.get("Крыша");
        }

        return builtParts;
    }
}
