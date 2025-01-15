package flipper.visitor;

import flipper.command.Command;
import flipper.command.ScoreCommand;
import flipper.composite.FlipperGroup;
import flipper.element.Bumper;
import flipper.element.FlipperElement;
import flipper.element.Ramp;
import flipper.element.Target;
import flipper.utils.Constants;
import flipper.utils.GameManager;

public class PunkteVisitor implements Visitor {

    private final FlipperGroup targetGroup;

    public PunkteVisitor(FlipperGroup targetGroup) {
        this.targetGroup = targetGroup;
    }

    @Override
    public void visit(FlipperElement element) {
        if (element instanceof Bumper) {
            handleBumper((Bumper) element);
        } else if (element instanceof Target) {
            handleTarget((Target) element);
        } else if (element instanceof Ramp) {
            handleRamp((Ramp) element);
        } else if (element.isHit()) {
            handleDefaultElement(element);
        }
    }

    private void handleBumper(Bumper bumper) {
        int hitCount = bumper.getHitCount();
        int basePoints = Constants.BUMPER_POINTS;
        int points = basePoints * hitCount;

        // Bonus hinzufügen, falls der Bonusmodus aktiv ist
        if (targetGroup.isBonusActive()) {
            points += basePoints * (int) Math.pow(2, bumper.getHitCount() - 1);
            System.out.println(bumper.getName() + " Bonus: +" + (points - basePoints));
        }
        GameManager.getInstance().addPoints(points);
        System.out.println(bumper.getName() + " hat Punkte hinzugefügt: " + points);
    }

    private void handleTarget(Target target) {
        if (target.isRecentlyHit()) {
            Command command = target.getCommand();
            if (command instanceof ScoreCommand) {
                int points = ((ScoreCommand) command).getPoints();
                GameManager.getInstance().addPoints(points);
                System.out.println(target.getName() + " hat Punkte hinzugefügt: " + points);
            }
        }
    }

    private void handleRamp(Ramp ramp) {
        if (ramp.isHit()) {
            Command command = ramp.getCommand();
            if (command instanceof ScoreCommand) {
                int points = ((ScoreCommand) command).getPoints();
                GameManager.getInstance().addPoints(points);
                System.out.println(ramp.getName() + " hat Punkte hinzugefügt: " + points);
            }
        }
    }

    private void handleDefaultElement(FlipperElement element) {
        Command command = element.getCommand();
        if (command instanceof ScoreCommand) {
            int points = ((ScoreCommand) command).getPoints();
            GameManager.getInstance().addPoints(points);
            System.out.println(element.getName() + " hat Punkte hinzugefügt: " + points);
        }
    }
}
