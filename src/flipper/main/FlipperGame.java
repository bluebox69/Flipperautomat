package flipper.main;

import flipper.element.FlipperElement;
import flipper.element.Ramp;
import flipper.element.Target;
import flipper.factory.FactorySelector;
import flipper.state.EndState;
import flipper.state.FlipperMachine;
import flipper.utils.Constants;
import flipper.utils.GameManager;
import flipper.visitor.PunkteVisitor;

import java.util.Random;


public class FlipperGame {
    private Random random = new Random();
    private FlipperMachine flipperMachine;
    private FlipperElement bumper;
    private FlipperElement[] targets;
    private FlipperElement ramp;

    public FlipperGame(FlipperMachine flipperMachine, FlipperElement bumper, FlipperElement[] targets, FlipperElement ramp) {
        this.flipperMachine = flipperMachine;
        this.bumper = bumper;
        this.targets = targets;
        this.ramp = ramp;
    }

    public void simulateBallMovement() {
        if (!flipperMachine.getCurrentStateName().equals("PlayingState")) {
            System.out.println("Ballbewegung ist nur im PlayingState erlaubt!");
            return;
        }

        int roll = random.nextInt(100); // Zufallszahl zwischen 0 und 99

        int remainingBalls = flipperMachine.getBallCount(); // Anzahl der verbleibenden Bälle
        int currentBall = 4 - remainingBalls; // Ballnummer (4 - Anzahl der verbleibenden Bälle)


        System.out.println(FactorySelector.getFactory().renderBall(currentBall)); //Hier soll immer die Ballnummer angezeigt werden


        if (roll < Constants.BUMPER_CHANCE) {
            // 50 % Wahrscheinlichkeit für Bumper
            System.out.println("Bumper getroffen!");
            bumper.hit();
        } else if (roll < Constants.BUMPER_CHANCE + Constants.TARGET_CHANCE) {
            // 30 % Wahrscheinlichkeit für Target
            for (FlipperElement target : targets) {
                if (target != null && !((Target) target).isHit()) {
                    System.out.println("Target getroffen!");
                    target.hit();
                    break;
                }
            }
        } else if (roll < Constants.RAMP_CHANCE){
            // 20 % Wahrscheinlichkeit für Rampe
            if (ramp != null && !((Ramp) ramp).isOpen()) {
                System.out.println("Rampe getroffen! Jackpot!");
                ramp.hit();
            }
        }

        // Prüfe auf Ballverlust
        int lossRoll = random.nextInt(100);
        if (lossRoll < Constants.BALL_LOSS_CHANCE) {
            System.out.println("Ball verloren!");
            flipperMachine.loseBall();

            if (flipperMachine.getBallCount() == 0) {
                System.out.println(FactorySelector.getFactory().renderGameOver());
                flipperMachine.setState(new EndState());
            }
        }

        if (flipperMachine.getBallCount() == 0) {
            PunkteVisitor punkteVisitor = new PunkteVisitor();
            bumper.accept(punkteVisitor);
            for (FlipperElement target : targets) {
                target.accept(punkteVisitor);
            }
            ramp.accept(punkteVisitor);

            System.out.println("Endgültiger Highscore: " + punkteVisitor.getTotalPoints());
        }

    }
}
