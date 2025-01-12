package flipper.visitor;

import flipper.element.FlipperElement;

public class ResetVisitor implements Visitor {
    @Override
    public void visit(FlipperElement element) {
        System.out.println("Setze " + element.getName() + " zurück.");
        // Zusätzliche Logik für das Zurücksetzen (z. B. Targets hochfahren)
    }
}
