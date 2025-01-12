package flipper.element;

import flipper.composite.FlipperComponent;

public class Bumper extends  FlipperElement implements FlipperComponent {
    @Override
    public String getName() {
        return "Bumper";
    }
}
