package flipper.mediator;

import flipper.composite.FlipperGroup;

public interface Mediator {
    void notify(Object sender, String event);
}
