package flipper.state;

public class ReadyState implements FlipperState{
    @Override
    public void insertCoin(FlipperMachine machine) {
        System.out.println("Münze eingeworfen. Kredit erhöht!");
        machine.addCredit();
    }

    @Override
    public void pressStart(FlipperMachine machine) {
        //validation
        if (machine.getCredit() > 0) {
            System.out.println("Spiel startet. Viel Spaß!");
            machine.reduceCredit();
            machine.setState(machine.getPlayingState());
        } else {
            System.out.println("Kein Kredit vorhanden. Bitte Münze einwerfen.");
        }
    }

    @Override
    public void loseBall(FlipperMachine machine) {
        System.out.println("Kein Spiel aktiv. Keine Aktion möglich.");
    }
}
