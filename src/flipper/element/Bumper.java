package flipper.element;

import flipper.composite.FlipperComponent;

public class Bumper extends  FlipperElement implements FlipperComponent {
    private int hitCount = 0;

    @Override
    public void hit() {
        hitCount++; // Erhöhe die Trefferanzahl
        System.out.println("Bumper getroffen! Trefferanzahl: " + hitCount);
    }

    public int getHitCount() {
        return hitCount; // Gibt die Trefferanzahl zurück
    }

    public void reset() {
        hitCount = 0; // Setzt die Trefferanzahl zurück
    }

    @Override
    public String getName() {
        return "Bumper";
    }
}
