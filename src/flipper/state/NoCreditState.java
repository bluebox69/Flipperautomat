package flipper.state;

public class NoCreditState implements FlipperState {
    @Override
    public void insertCoin(FlipperMachine machine) {
        System.out.println("Münze eingeworfen. Kredit hinzugefügt!");
        machine.addCredit();
        machine.setState(machine.getReadyState());
    }

    @Override
    public void pressStart(FlipperMachine machine) {
        System.out.println("Kein Kredit vorhanden. Bitte Münze einwerfen.");
    }

    @Override
    public void loseBall(FlipperMachine machine) {
        System.out.println("Kein Spiel aktiv. Keine Aktion möglich.");
    }
}
