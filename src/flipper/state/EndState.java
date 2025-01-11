package flipper.state;

public class EndState implements FlipperState {
    @Override
    public void insertCoin(FlipperMachine machine) {
        System.out.println("Münze eingeworfen. Kredit erhöht!");
        machine.addCredit();
        machine.setState(machine.getReadyState());
    }

    @Override
    public void pressStart(FlipperMachine machine) {
        System.out.println("Spiel beendet. Bitte starten Sie ein neues Spiel.");
    }

    @Override
    public void loseBall(FlipperMachine machine) {
        System.out.println("Spiel beendet. Keine Aktion möglich.");
    }
}
