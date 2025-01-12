package flipper.visitor;

import flipper.command.Command;
import flipper.command.ScoreCommand;
import flipper.element.FlipperElement;

public class PunkteVisitor implements Visitor {
    private int totalPoints = 0;

    @Override
    public void visit(FlipperElement element) {
        // Punkte werden auf Basis der Eigenschaften des Elements berechnet
        Command command = element.getCommand();
        if (command instanceof ScoreCommand) {
            int points = ((ScoreCommand) command).getPoints();
            totalPoints += points; // Summiere Punkte
        }
    }

    public int getTotalPoints() {
        return totalPoints;
    }
}
