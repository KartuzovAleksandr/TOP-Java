package Lesson06.HouseBuilding;

import java.util.ArrayList;
import java.util.List;

public class House {
    private Basement basement;
    private List<Wall> walls;
    private List<Door> doors;
    private List<Window> windows;
    private Roof roof;

    public House() {
        this.basement = new Basement();
        this.walls = new ArrayList<>();
        this.doors = new ArrayList<>();
        this.windows = new ArrayList<>();
        this.roof = new Roof();
    }

    public Basement getBasement() {
        return basement;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Door> getDoors() {
        return doors;
    }

    public List<Window> getWindows() {
        return windows;
    }

    public Roof getRoof() {
        return roof;
    }
}

