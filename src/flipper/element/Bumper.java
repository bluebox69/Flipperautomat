package flipper.element;

import flipper.composite.FlipperComponent;

public class Bumper extends FlipperElement implements FlipperComponent {
    private int hitCount = 0;

    @Override
    public void hit() {
        hitCount++;
        System.out.println("Bumper getroffen! Trefferanzahl: " + hitCount);
    }

    public int getHitCount() {
        return hitCount;
    }

    public void reset() {
        hitCount = 0;
    }

    @Override
    public String getName() {
        return "Bumper";
    }
}
