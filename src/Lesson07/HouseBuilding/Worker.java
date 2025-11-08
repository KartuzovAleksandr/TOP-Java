package Lesson07.HouseBuilding;

public class Worker implements IWorker {
    @Override
    public void work(House house) {
        if (!house.getBasement().isBuilt()) {
            house.getBasement().build();
        } else if (house.getWalls().size() < 4) {
            Wall wall = new Wall();
            wall.build();
            house.getWalls().add(wall);
        } else if (house.getDoors().isEmpty()) {
            Door door = new Door();
            door.build();
            house.getDoors().add(door);
        } else if (house.getWindows().size() < 4) {
            Window window = new Window();
            window.build();
            house.getWindows().add(window);
        } else if (!house.getRoof().isBuilt()) {
            house.getRoof().build();
        }
    }
}
