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

    @Override
    public void hit() {
        if (isOpen() && !isHit()) { // Treffer nur, wenn Rampe offen und nicht getroffen
            setHit(true);
            System.out.println("Rampe getroffen! Jackpot!");
            close(); // Rampe schließt sich nach Treffer
        } else if (!isOpen()) {
            System.out.println("Rampe ist geschlossen.");
        } else {
            System.out.println("Rampe wurde bereits getroffen.");
        }
    }
    public void reset() {
        setHit(false); // Trefferstatus zurücksetzen
        close();       // Rampe schließen
        System.out.println("Ramp zurückgesetzt.");
    }

    @Override
    public String getName() {
        return "Ramp";
    }
}