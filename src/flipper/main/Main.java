package flipper.main;

import flipper.composite.FlipperGroup;
import flipper.state.FlipperMachine;
import flipper.command.*;
import flipper.element.*;
import flipper.utils.GameManager;

public class Main {
    public static void main(String[] args) {
        // **1. Test: Einzelne Flipper-Elemente und Command-Pattern**
        System.out.println("=== Test: Einzelne Flipper-Elemente ===");
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

        // Punktestand prüfen
        System.out.println("Punktestand nach Einzeltests: " + GameManager.getInstance().getScore());

        // **2. Test: Composite-Pattern**
        System.out.println("\n=== Test: Gruppen von Flipper-Elementen ===");
        FlipperGroup group = new FlipperGroup();
        group.add(bumper);
        group.add(target);

        group.hit(); // Führt die Treffer aller Elemente in der Gruppe aus
        System.out.println("Punktestand nach Gruppentreffer: " + GameManager.getInstance().getScore());

        // **3. Test: State-Pattern**
        System.out.println("\n=== Test: Spielzustände ===");
        FlipperMachine machine = new FlipperMachine();

        machine.pressStart(); // Kein Kredit
        machine.insertCoin(); // Kredit hinzufügen
        machine.pressStart(); // Spiel starten
        machine.loseBall();   // Ball verlieren
        machine.loseBall();   // Ball verlieren
        machine.insertCoin(); // Kredit hinzufügen während des Spiels
        machine.loseBall();   // Ball verlieren
        machine.loseBall();   // Letzter Ball
        machine.loseBall();   // Letzter Ball
        machine.pressStart(); // Spiel beendet
        System.out.println("Endgültiger Punktestand: " + GameManager.getInstance().getScore());
        System.out.println("Verbleibender Kredit: " + machine.getCredit());
    }
}