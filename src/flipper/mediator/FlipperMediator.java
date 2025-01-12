package flipper.mediator;

import flipper.composite.FlipperGroup;
import flipper.element.Target;

public class FlipperMediator implements Mediator{
    @Override
    public void notify(Object sender, String event) {
        if (sender instanceof Target && event.equals("TargetHit")) {
            System.out.println("Mediator: Ein Target wurde getroffen.");
            // Hier könnte zusätzliche Logik implementiert werden.
        }
    }

    @Override
    public void resetGroup(FlipperGroup group) {
        System.out.println("Mediator: Gruppe wird zurückgesetzt.");
        group.hit(); // Optional: Kann alle Elemente in der Gruppe ansprechen.
    }
}
