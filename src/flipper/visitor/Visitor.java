package flipper.visitor;

import flipper.element.FlipperElement;

public interface Visitor {
    void visit(FlipperElement element);
}