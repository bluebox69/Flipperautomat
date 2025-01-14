package flipper.element;

import flipper.composite.FlipperComponent;
import flipper.mediator.Mediator;

public class Target extends FlipperElement implements FlipperComponent {
    private Mediator mediator;



    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void hit() {
        if (!isHit()) { // Nur, wenn das Target nicht schon getroffen wurde
            setHit(true);
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

    public void reset() {
        setHit(false); // Trefferstatus zurücksetzen
        System.out.println("Target zurückgesetzt.");
    }


}
