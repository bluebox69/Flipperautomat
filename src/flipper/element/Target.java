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
        System.out.println("Target getroffen!");
        if (getCommand() != null) {
            getCommand().execute();
        }
        if (mediator != null) {
            mediator.notify(this, "TargetHit");
        }
    }
    @Override
    public String getName() {
        return "Target";
    }

}
