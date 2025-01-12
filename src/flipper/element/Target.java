package flipper.element;

import flipper.composite.FlipperComponent;
import flipper.mediator.Mediator;

public class Target extends FlipperElement implements FlipperComponent {
    private Mediator mediator;
    private boolean hit = false; // Initialzustand: nicht getroffen


    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void hit() {
        if (!hit) { // Nur, wenn das Target nicht schon getroffen wurde
            hit = true; // Trefferstatus setzen
            System.out.println("Target getroffen!");
            if (getCommand() != null) {
                getCommand().execute();
            }
            if (mediator != null) {
                mediator.notify(this, "TargetHit");
            }
        } else {
            System.out.println("Target wurde bereits getroffen.");
        }
    }
    public boolean isHit() {
        return hit; // Gibt zurück, ob das Target bereits getroffen wurde
    }
    @Override
    public String getName() {
        return "Target";
    }

    public void reset() {
        hit = false; // Trefferstatus zurücksetzen
        System.out.println("Target zurückgesetzt.");
    }


}
