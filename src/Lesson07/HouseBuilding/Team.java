package Lesson07.HouseBuilding;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<IWorker> workers;

    public Team() {
        this.workers = new ArrayList<>();
    }

    public void addWorker(IWorker worker) {
        workers.add(worker);
    }

    public void buildHouse(House house) {
        while (!isHouseComplete(house)) {
            for (IWorker worker : workers) {
                worker.work(house);
            }
        }
        System.out.println("Строительство дома завершено!");
        drawHouse();
    }

    private boolean isHouseComplete(House house) {
        return house.getBasement().isBuilt() &&
                house.getWalls().size() == 4 &&
                house.getDoors().size() == 1 &&
                house.getWindows().size() == 4 &&
                house.getRoof().isBuilt();
    }

    private void drawHouse() {
        System.out.println("Рисунок дома:");
        System.out.println("   /\\");
        System.out.println("  /  \\");
        System.out.println(" /____\\");
        System.out.println("|      |");
        System.out.println("| []   |");
        System.out.println("|______|");
    }
}

