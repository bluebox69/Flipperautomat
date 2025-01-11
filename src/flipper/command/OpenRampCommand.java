package flipper.command;

public class OpenRampCommand implements Command{
    @Override
    public void execute() {
        System.out.println("Rampe geöffnet!");
        // Logik zum Öffnen der Rampe.
    }
}
