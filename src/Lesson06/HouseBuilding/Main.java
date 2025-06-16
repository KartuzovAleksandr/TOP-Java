// (С) Бегичев Богдан
// Top Academy гр. П21
package Lesson06.HouseBuilding;

    public class Main {
        public static void main(String[] args) {
            House house = new House();
            Team team = new Team();

            team.addWorker(new Worker());
            team.addWorker(new Worker());
            team.addWorker(new Worker());
            team.addWorker(new TeamLeader());

            team.buildHouse(house);
        }
    }

