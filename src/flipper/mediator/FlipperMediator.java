package flipper.mediator;

import flipper.composite.FlipperGroup;
import flipper.element.Ramp;
import flipper.element.Target;

public class FlipperMediator implements Mediator{
    private Target[] targets;
    private Ramp ramp;

    public FlipperMediator(Target[] targets, Ramp ramp) {
        this.targets = targets;
        this.ramp = ramp;
    }
    @Override
    public void notify(Object sender, String event) {
        if (sender instanceof Target && event.equals("TargetHit")) {
            boolean allHit = true;
            for (Target target : targets) {
                if (!target.isHit()) {
                    allHit = false;
                    break;
                }
            }
            if (allHit) {
                System.out.println("Alle Targets wurden getroffen. Rampe wird geöffnet!");
                ramp.open();
            }
        }
    }

}
