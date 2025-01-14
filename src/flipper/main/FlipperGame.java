package flipper.main;

import flipper.element.Bumper;
import flipper.element.FlipperElement;
import flipper.element.Ramp;
import flipper.element.Target;
import flipper.factory.FactorySelector;
import flipper.factory.PoisonFactory;
import flipper.factory.SlantFactory;
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

        boolean ballInPlay = true; // Flag, um zu überprüfen, ob der Ball im Spiel ist

        int remainingBalls = flipperMachine.getBallCount(); // Anzahl der verbleibenden Bälle
        int currentBall = 4 - remainingBalls; // Ballnummer (4 - Anzahl der verbleibenden Bälle)


        FactorySelector.setFactory(new SlantFactory());
        System.out.println(FactorySelector.getFactory().renderBall(currentBall)); //Hier soll immer die Ballnummer angezeigt werden
        FactorySelector.setFactory(new PoisonFactory());

        while (ballInPlay) {
            System.out.println("Flipper schießt den Ball!"); // Nachricht für jeden "Roll"
            if (((Ramp) ramp).isOpen()) {
                System.out.println("Rampe ist geöffnet");
            } else {
                System.out.println("Rampe ist geschlossen");
            }
            System.out.println("-------");


            int roll = random.nextInt(100); // Zufallszahl für Treffer wird generiert

            if (roll < Constants.BUMPER_CHANCE) {
                bumper.hit(); //bumper getroffen!
                wait(300);
            } else if (roll < Constants.BUMPER_CHANCE + Constants.TARGET_CHANCE) {
                for (FlipperElement target : targets) {
                    if (target != null && !target.isHit()) {
                        target.hit(); //Target getroffen!
                        wait(300);
                        break;
                    }
                }
            } else if (ramp != null && !((Ramp) ramp).isOpen()) {
                System.out.println("Schuss Richtung Rampe..");
                ramp.hit();
            } else {
                System.out.println("Nichts getroffen!");
            }

            // Prüfe, ob der Ball verloren geht
            int lossRoll = random.nextInt(100);
            if (lossRoll < Constants.BALL_LOSS_CHANCE) {
                System.out.println("Ball verloren!");
                flipperMachine.loseBall();
                ballInPlay = false; // Beende die Schleife, da der Ball verloren geht
            } else {
                wait(800);
                System.out.println("");
                System.out.println("-------nächster Schuss------");

            }

            // Punkte anzeigen, wenn der Ball verloren geht
            if (!ballInPlay) {
                System.out.println("------ Punkteaufteilung ------");
                PunkteVisitor punkteVisitor = new PunkteVisitor();
                bumper.accept(punkteVisitor);
                if (bumper instanceof Bumper) { // Bumper hitCount reset
                    ((Bumper) bumper).reset();
                }
                for (FlipperElement target : targets) {
                    target.accept(punkteVisitor);
                }
                ramp.accept(punkteVisitor);

                System.out.println("Punkte in dieser Runde: " + GameManager.getInstance().getScore());


                if (flipperMachine.getBallCount() == 0) {
                    System.out.println(FactorySelector.getFactory().renderGameOver());
                    System.out.println("Spiel beendet! Dein Highscore: " + GameManager.getInstance().getScore());
                    System.out.println("-------");
                    flipperMachine.setState(new EndState());
                }
            }
        }
    }
    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}

