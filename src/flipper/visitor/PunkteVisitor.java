package flipper.visitor;

import flipper.command.Command;
import flipper.command.ScoreCommand;
import flipper.element.Bumper;
import flipper.element.FlipperElement;
import flipper.utils.Constants;
import flipper.utils.GameManager;

public class PunkteVisitor implements Visitor {

    @Override
    public void visit(FlipperElement element) {
        if (element instanceof Bumper) {
            // Sonderfall: Punkte basieren auf Trefferanzahl
            Bumper bumper = (Bumper) element;
            int points = bumper.getHitCount() * Constants.BUMPER_POINTS; // Beispiel: 50 Punkte pro Treffer
            GameManager.getInstance().addPoints(points);
            System.out.println(bumper.getName() + " hat Punkte hinzugefügt: " + points);
        } else if (element.isHit()) { // Punkte nur zählen, wenn das Element getroffen wurde
            Command command = element.getCommand();
            if (command instanceof ScoreCommand) {
                int points = ((ScoreCommand) command).getPoints();
                GameManager.getInstance().addPoints(points);
                System.out.println(element.getName() + " hat Punkte hinzugefügt: " + points);
            }
        }
    }
}
