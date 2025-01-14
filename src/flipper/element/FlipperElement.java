package flipper.element;

import flipper.command.Command;
import flipper.composite.FlipperComponent;
import flipper.visitor.Visitor;

public abstract class FlipperElement implements FlipperComponent {
    private boolean hit = false;
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public abstract void hit();

    public boolean isHit() {
        return hit;
    }
    public void setHit(boolean hit) {
        this.hit = hit;
    }


    public Command getCommand() {
        return command;
    }

    public abstract String getName();

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}