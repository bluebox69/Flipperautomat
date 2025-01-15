package flipper.visitor;

import flipper.composite.FlipperGroup;
import flipper.element.*;

public class ResetVisitor implements Visitor {

    private final FlipperGroup targetGroup;

    public ResetVisitor(FlipperGroup targetGroup) {
        this.targetGroup = targetGroup;
    }

    @Override
    public void visit(FlipperElement element) {
        System.out.println("Setze " + element.getName() + " zurück.");
        element.setHit(false); // Trefferstatus für alle Elemente zurücksetzen
        if (element instanceof Bumper) {
            ((Bumper) element).reset(); // HitCount zurücksetzen
        } else if (element instanceof flipper.element.Ramp) {
            ((flipper.element.Ramp) element).reset(); // Rampe schließen
        } else if (element instanceof ExtraBall) {
            ((ExtraBall) element).reset(); // Extra Ball zurücksetzen
            System.out.println("Extra Ball wurde zurückgesetzt.");

        } else if (targetGroup != null) {
            targetGroup.setBonusActive(false);
            System.out.println("Bonusmodus für Bumper wurde zurückgesetzt.");
        }
    }

}
