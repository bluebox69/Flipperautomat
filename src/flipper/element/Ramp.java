package flipper.element;

public class Ramp extends FlipperElement {
    private boolean open = false; // Initialzustand: geschlossen

    public boolean isOpen() {
        return open;
    }

    public void open() {
        if (!open) {
            open = true;
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
        if (isOpen() && !isHit()) {
            setHit(true);
            System.out.println("Rampe getroffen! Jackpot!");
        } else if (!isOpen()) {
            System.out.println("Rampe ist geschlossen.");
        } else {
            System.out.println("Rampe wurde bereits getroffen.");
        }
    }

    public void reset() {
        setHit(false);
        close();
        System.out.println("Ramp zurückgesetzt.");
    }

    @Override
    public String getName() {
        return "Ramp";
    }
}