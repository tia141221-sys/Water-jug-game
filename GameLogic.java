package main;
public class GameLogic {

    public boolean checkWin(int current, int target) {
        return current == target;
    }

    public static void main(String[] args) {

        GameLogic game = new GameLogic();

        int currentWater = 4;
        int targetWater = 2; // change করে test করতে পারো

        if (game.checkWin(currentWater, targetWater)) {
            System.out.println("You Win!");
        } else {
            System.out.println("Try Again!");
        }
    }
}
