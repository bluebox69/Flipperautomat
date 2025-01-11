package flipper.element;

import flipper.command.Command;

public abstract class FlipperElement {
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

    public abstract String getName();
}