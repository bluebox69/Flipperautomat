package flipper.mediator;

import flipper.composite.FlipperGroup;

public interface Mediator {
    void notify(Object sender, String event);
    void resetGroup(FlipperGroup group); // Methode zum Zurücksetzen einer Gruppe
}
