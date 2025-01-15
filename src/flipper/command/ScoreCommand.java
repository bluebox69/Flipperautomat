package flipper.command;

import flipper.utils.GameManager;

public class ScoreCommand implements Command {

    private int points;

    public ScoreCommand(int points) {
        this.points = points;
    }

    @Override
    public void execute() {
        System.out.println("Punkte vergeben: " + points);
        GameManager.getInstance().addPoints(points);
    }

    public int getPoints() {
        return points; // Getter für Punkte
    }
}
