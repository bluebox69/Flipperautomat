package flipper.element;

import flipper.composite.FlipperComponent;
import flipper.mediator.Mediator;

public class Target extends FlipperElement implements FlipperComponent {
    private Mediator mediator;

    private boolean recentlyHit = false;


    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
    }

    @Override
    public void hit() {
        if (!isHit()) { // Nur, wenn das Target nicht schon getroffen wurde
            setHit(true);
            recentlyHit = true;
            System.out.println("Target getroffen!");
            if (mediator != null) {
                mediator.notify(this, "TargetHit");
            }
        } else {
            System.out.println("Target wurde bereits getroffen.");
        }
    }

    @Override
    public String getName() {
        return "Target";
    }

    public boolean isRecentlyHit() {
        return recentlyHit;
    }

    public void resetRecentlyHit() {
        recentlyHit = false;
    }


}
