package flipper.state;

public class PlayingState implements FlipperState{
    @Override
    public void insertCoin(FlipperMachine machine) {
        System.out.println("Spiel läuft bereits. Kredit wird hinzugefügt.");
        machine.addCredit();
    }

    @Override
    public void pressStart(FlipperMachine machine) {
        System.out.println("Spiel läuft bereits.");
    }

    @Override
    public void loseBall(FlipperMachine machine) {
        machine.reduceBall();
        if (machine.getBallCount() > 0) {
            System.out.println("Ball verloren. Noch " + machine.getBallCount() + " Bälle übrig.");
        } else {
            System.out.println("Alle Bälle verloren. Spiel beendet!");
            machine.setState(machine.getEndState());
        }
    }
}
