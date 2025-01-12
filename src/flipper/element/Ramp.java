package flipper.element;

public class Ramp extends FlipperElement {
    private boolean open = false; // Initialzustand: geschlossen

    public boolean isOpen() {
        return open; // Gibt zurück, ob die Rampe offen ist
    }

    public void open() {
        if (!open) {
            open = true;
            System.out.println("Rampe geöffnet!");
        }
    }

    public void close() {
        if (open) {
            open = false;
            System.out.println("Rampe geschlossen!");
        }
    }

    public void reset() {
        open = false; // Schließe die Rampe
        System.out.println("Rampe zurückgesetzt.");
    }


    @Override
    public void hit() {
        if (open) {
            System.out.println("Rampe getroffen! Jackpot!");
            if (getCommand() != null) {
                getCommand().execute();
            }
            close(); // Rampe schließt sich nach Treffer
        } else {
            System.out.println("Rampe ist geschlossen. Kein Treffer möglich.");
        }
    }

    @Override
    public String getName() {
        return "Ramp";
    }
}