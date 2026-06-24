public class WaterJug {
    private int capacity;
    private int currentWater;

    public WaterJug(int capacity) {
        this.capacity = capacity;
        this.currentWater = 0;
    }

    public static void main(String[] args) {
        WaterJug jug = new WaterJug(2); 
        System.out.println("Jug created with capacity: " + jug.capacity);
    }
}