package flipper.main;

import flipper.composite.FlipperComponent;
import flipper.composite.FlipperGroup;
import flipper.element.*;
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
    private final FlipperGroup targetGroup;
    private final Random random = new Random();
    private final FlipperMachine flipperMachine;
    private final FlipperElement bumper;
    private final ExtraBall extraBall;
    private final FlipperElement[] targets;
    private final FlipperElement ramp;

    public FlipperGame(
            FlipperMachine flipperMachine,
            FlipperElement bumper,
            FlipperElement[] targets,
            FlipperElement ramp,
            FlipperGroup targetGroup,
            ExtraBall extraBall
    ) {
        this.flipperMachine = flipperMachine;
        this.bumper = bumper;
        this.targets = targets;
        this.ramp = ramp;
        this.targetGroup = targetGroup;
        this.extraBall = extraBall;
    }

    public void simulateBallMovement() {
        if (!flipperMachine.getCurrentStateName().equals("PlayingState")) {
            System.out.println("Ballbewegung ist nur im PlayingState erlaubt!");
            return;
        }

        int remainingBalls = flipperMachine.getBallCount();
        int totalBalls = 3;
        if (extraBall.isGranted()) {
            totalBalls++;
        }
        int currentBall = totalBalls - remainingBalls + 1; // +1, da Ballnummer ab 1 beginnt

        displayCurrentBall(currentBall);

        boolean ballInPlay = true;
        while (ballInPlay) {
            System.out.println("Flipper schießt den Ball!");
            displayRampStatus();
            System.out.println("-------");

            int roll = random.nextInt(100);
            ballInPlay = handleRoll(roll);

            if (!ballInPlay) {
                handleBallLoss();
            }
        }
    }

    private void displayCurrentBall(int currentBall) {
        FactorySelector.setFactory(new SlantFactory());
        System.out.println(FactorySelector.getFactory().renderBall(currentBall));
        FactorySelector.setFactory(new PoisonFactory());
    }

    private void displayRampStatus() {
        if (((Ramp) ramp).isOpen()) {
            System.out.println("Rampe ist geöffnet");
        } else {
            System.out.println("Rampe ist geschlossen");
        }
    }

    private boolean handleRoll(int roll) {
        if (roll < Constants.BUMPER_CHANCE) {
            handleBumperHit();
        } else if (roll < Constants.BUMPER_CHANCE + Constants.TARGET_CHANCE) {
            handleTargetHit();
        } else if (roll < Constants.BUMPER_CHANCE + Constants.TARGET_CHANCE + Constants.RAMP_CHANCE) {
            handleRampHit();
        } else {
            System.out.println("Nichts getroffen!");
        }
        return !isBallLost();
    }

    private void handleBumperHit() {
        bumper.hit();
        targetGroup.checkAndActivateBonus();
        wait(300);
    }

    private void handleTargetHit() {
        boolean targetHit = false;
        for (FlipperComponent target : targetGroup.getTargets()) {
            if (target instanceof FlipperElement && !((FlipperElement) target).isHit()) {
                target.hit();
                if (target instanceof Target) {
                    ((Target) target).getMediator().notify(target, "TargetHit");
                }
                wait(300);
                targetHit = true;
                break;
            }
        }
        if (!targetHit) {
            System.out.println("Alle Targets wurden bereits getroffen!");
        }
    }

    private void handleRampHit() {
        if (((Ramp) ramp).isOpen()) {
            System.out.println("Schuss Richtung Rampe!");
            ramp.hit();
        } else {
            System.out.println("Schuss Richtung Rampe, aber sie ist geschlossen.");
        }
    }

    private boolean isBallLost() {
        int lossRoll = random.nextInt(100);
        if (lossRoll < Constants.BALL_LOSS_CHANCE) {
            System.out.println("Ball verloren!");
            // Prüfen, ob ein Extra Ball vergeben werden soll
            if (ballIsSavedWithExtraBall()) return false;

            flipperMachine.loseBall();
            return true;
        }
        wait(800);
        System.out.println("\n-------nächster Schuss------");
        return false;
    }

    private boolean ballIsSavedWithExtraBall() {
        if (!extraBall.isGranted() && random.nextInt(100) < Constants.EXTRA_BALL_CHANCE) {
            extraBall.hit();
            flipperMachine.addBall();
            return true;
        }
        return false;
    }

    private void handleBallLoss() {
        System.out.println("------ Punkteaufteilung ------");
        PunkteVisitor punkteVisitor = new PunkteVisitor(targetGroup);

        bumper.accept(punkteVisitor);
        if (bumper instanceof Bumper) {
            ((Bumper) bumper).reset();
        }

        for (FlipperElement target : targets) {
            target.accept(punkteVisitor);
            if (target instanceof Target) {
                ((Target) target).resetRecentlyHit();
            }
        }
        ramp.accept(punkteVisitor);

        System.out.println("Punkte in dieser Runde: " + GameManager.getInstance().getScore());

        handleGameOver();
    }

    private void handleGameOver() {
        if (flipperMachine.getBallCount() == 0) {
            System.out.println(FactorySelector.getFactory().renderGameOver());
            System.out.println("Spiel beendet! Dein Highscore: " + GameManager.getInstance().getScore());
            System.out.println("-------");
            flipperMachine.setState(new EndState());
        }
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
