package flipper.main;

import flipper.command.ScoreCommand;
import flipper.element.Bumper;
import flipper.element.FlipperElement;
import flipper.element.Ramp;
import flipper.element.Target;
import flipper.mediator.FlipperMediator;
import flipper.state.FlipperMachine;
import flipper.state.ReadyState;
import flipper.utils.Constants;
import flipper.utils.GameManager;
import flipper.factory.FactorySelector;
import flipper.factory.PoisonFactory;
import flipper.visitor.ResetVisitor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Setup
        FlipperMachine machine = new FlipperMachine();
        GameManager gameManager = GameManager.getInstance();
        FactorySelector.setFactory(new PoisonFactory());

        Target target1 = new Target();
        Target target2 = new Target();
        Target target3 = new Target();
        Target[] targets = {target1, target2, target3};

        Ramp ramp = new Ramp();
        FlipperMediator mediator = new FlipperMediator(targets, ramp);

        for (Target target : targets) {
            target.setMediator(mediator);
        }

        Bumper bumper = new Bumper();
        bumper.setCommand(new ScoreCommand(Constants.BUMPER_POINTS));
        for (Target target : targets) {
            target.setCommand(new ScoreCommand(Constants.TARGET_POINTS));
        }
        ramp.setCommand(new ScoreCommand(Constants.RAMP_POINTS));

        FlipperGame game = new FlipperGame(machine, bumper, targets, ramp);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println(FactorySelector.getFactory().renderWelcome());
        while (running) {
            System.out.println("\n=== Hauptmenü ===");
            switch (machine.getCurrentStateName()) {
                case "NoCreditState":
                    System.out.println("1. Münze einwerfen");
                    System.out.println("2. Status anzeigen");
                    System.out.println("3. Beenden");
                    break;
                case "ReadyState":
                    System.out.println(FactorySelector.getFactory().renderPressStart());
                    System.out.println("1. Spiel starten");
                    System.out.println("2. Status anzeigen");
                    System.out.println("3. Beenden");
                    break;
                case "PlayingState":
                    System.out.println("1. Kugel schießen");
                    System.out.println("2. Status anzeigen");
                    System.out.println("3. Beenden");
                    break;
                case "EndState":
                    System.out.println("1. Neues Spiel starten (Münze einwerfen)");
                    System.out.println("2. Status anzeigen");
                    System.out.println("3. Beenden");
                    break;
                default:
                    System.out.println("Ungültiger Zustand.");
            }
            System.out.print("Wähle eine Option: ");

            String input = scanner.nextLine();
            switch (machine.getCurrentStateName()) {
                case "NoCreditState":

                    if (input.equals("1")) machine.insertCoin();
                    else if (input.equals("2")) showStatus(machine, gameManager);
                    else if (input.equals("3")) running = false;
                    break;
                case "ReadyState":

                    if (input.equals("1")) machine.pressStart();
                    else if (input.equals("2")) showStatus(machine, gameManager);
                    else if (input.equals("3")) running = false;
                    break;
                case "PlayingState":
                    if (input.equals("1")) game.simulateBallMovement();
                    else if (input.equals("2")) showStatus(machine, gameManager);
                    else if (input.equals("3")) running = false;
                    break;
                case "EndState":
                    if (input.equals("1")) {
                        machine.insertCoin();
                        ResetVisitor resetVisitor = new ResetVisitor();

                        // Alle Elemente zurücksetzen
                        bumper.accept(resetVisitor);
                        for (FlipperElement target : targets) {
                            target.accept(resetVisitor);
                        }
                        ramp.accept(resetVisitor);

                        GameManager.getInstance().resetScore(); // Punkte zurücksetzen
                        machine.resetBallCount(); // Bälle wieder auf 3 zurücksetzen
                        System.out.println("Neues Spiel bereit! Drücke 'Spiel starten'.");
                        machine.setState(new ReadyState());
                    } else if (input.equals("2")) {
                        showStatus(machine, gameManager);
                    } else if (input.equals("3")) {
                        running = false;
                    }
                    break;
            }
        }

        scanner.close();
    }

    private static void showStatus(FlipperMachine machine, GameManager gameManager) {
        System.out.println("\n=== Aktueller Status ===");
        System.out.println("Zustand: " + machine.getCurrentStateName());
        System.out.println("Kredit: " + machine.getCredit());
        System.out.println("Punktestand: " + gameManager.getScore());
    }
}