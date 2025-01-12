package flipper.element;

import flipper.command.Command;
import flipper.composite.FlipperComponent;
import flipper.visitor.Visitor;

public abstract class FlipperElement implements FlipperComponent {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void hit() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Kein Befehl zugewiesen.");
        }
    }

    public Command getCommand() {
        return command;
    }

    public abstract String getName();

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}