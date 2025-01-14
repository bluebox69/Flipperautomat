package flipper.visitor;

import flipper.element.Bumper;
import flipper.element.FlipperElement;
import flipper.element.Ramp;
import flipper.element.Target;

public class ResetVisitor implements Visitor {
    @Override
    public void visit(FlipperElement element) {
        System.out.println("Setze " + element.getName() + " zurück.");
        element.setHit(false); // Trefferstatus für alle Elemente zurücksetzen
        if (element instanceof Bumper) {
            ((Bumper) element).reset(); // HitCount zurücksetzen
        } else if (element instanceof flipper.element.Ramp) {
            ((flipper.element.Ramp) element).close(); // Rampe schließen
        }
    }
}
