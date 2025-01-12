package flipper.visitor;

import flipper.element.FlipperElement;
import flipper.element.Ramp;
import flipper.element.Target;

public class ResetVisitor implements Visitor {
    @Override
    public void visit(FlipperElement element) {
        System.out.println("Setze " + element.getName() + " zurück.");
        if (element instanceof Target) {
            ((Target) element).reset();
        } else if (element instanceof Ramp) {
            ((Ramp) element).close();
        }    }
}
