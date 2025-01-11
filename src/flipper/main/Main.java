package flipper.main;

import flipper.state.FlipperMachine;
import flipper.command.*;
import flipper.element.*;
import flipper.utils.GameManager;

public class Main {
    public static void main(String[] args) {
        // Flipper-Elemente erstellen
        FlipperElement bumper = new Bumper();
        FlipperElement target = new Target();

        // Befehle zuweisen
        bumper.setCommand(new ScoreCommand(50));
        target.setCommand(new MacroCommand() {{
            addCommand(new ScoreCommand(100));
            addCommand(new OpenRampCommand());
        }});

        // Elemente treffen
        bumper.hit(); // Ausgabe: Punkte vergeben: 50
        target.hit(); // Ausgabe: Punkte vergeben: 100, Rampe geöffnet!

        // Punktestand ausgeben
        System.out.println("Gesamtpunktestand: " + GameManager.getInstance().getScore());


        FlipperMachine machine = new FlipperMachine();

        // Testablauf
        machine.pressStart(); // Kein Kredit
        machine.insertCoin(); // Kredit hinzufügen
        machine.pressStart(); // Spiel starten
        machine.loseBall();   // Ball verlieren
        machine.loseBall();   // Ball verlieren
        machine.insertCoin();
        machine.loseBall();   // Ball verlieren
        machine.loseBall();   // Ball verlieren
        machine.pressStart(); // Spiel beendet
        machine.pressStart(); // Kein Kredit
        System.out.println("Kredit: " + machine.getCredit());
    }
}
