package flipper.main;

import flipper.state.FlipperMachine;

public class Main {
    public static void main(String[] args) {
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
