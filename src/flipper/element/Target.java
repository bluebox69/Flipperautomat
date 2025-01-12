package flipper.element;

import flipper.composite.FlipperComponent;

public class Target extends FlipperElement implements FlipperComponent {
    @Override
    public String getName() {
        return "Target";
    }

}
