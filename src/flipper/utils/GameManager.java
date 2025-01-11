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
        score += points;
    }

    public int getScore() {
        return score;
    }
}