package flipper.utils;

public class GameManager {
    private static GameManager instance;
    private int score;

    private GameManager() {
        score = 0;
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void addPoints(int points) {
        if (points > 0) {
            score += points;
        } else {
            System.out.println("Punkte müssen positiv sein!");
        }
    }
    public int getScore() {
        return score;
    }

    public void resetScore() {
        this.score = 0;
    }

}