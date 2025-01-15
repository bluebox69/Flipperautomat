package flipper.command;

import flipper.element.Ramp;

public class OpenRampCommand implements Command {
    private final Ramp ramp;
    private boolean executed = false;

    public OpenRampCommand(Ramp ramp) {
        this.ramp = ramp;
    }

    @Override
    public void execute() {
        if (!executed) {
            ramp.open();
            executed = true;
            System.out.println("Rampe geöffnet!");
        } else {
            System.out.println("Rampe ist bereits geöffnet.");
        }
    }

    public boolean isExecuted() {
        return executed;
    }
}
