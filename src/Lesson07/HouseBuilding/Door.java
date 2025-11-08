package Lesson07.HouseBuilding;

public class Door implements IPart {
    private boolean isBuilt;

    @Override
    public boolean isBuilt() {
        return isBuilt;
    }

    @Override
    public void setBuilt(boolean built) {
        isBuilt = built;
    }

    @Override
    public void build() {
        isBuilt = true;
        System.out.println("Дверь установлена.");
    }
}
