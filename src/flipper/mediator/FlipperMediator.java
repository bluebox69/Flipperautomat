package flipper.mediator;

import flipper.command.OpenRampCommand;
import flipper.composite.FlipperGroup;
import flipper.element.Ramp;
import flipper.element.Target;

public class FlipperMediator implements Mediator {

    private FlipperGroup targetGroup;
    private OpenRampCommand openRampCommand;

    public FlipperMediator(FlipperGroup targetGroup, Ramp ramp) {
        this.targetGroup = targetGroup;
        this.openRampCommand = new OpenRampCommand(ramp);
    }

    @Override
    public void notify(Object sender, String event) {
        if (event.equals("TargetHit") && targetGroup.allHit() && !openRampCommand.isExecuted()) {
            System.out.println("Alle Targets in der Gruppe wurden getroffen. Rampe wird geöffnet!");
            openRampCommand.execute();
            targetGroup.setBonusActive(true);
            System.out.println("Bonusmodus für Bumper aktiviert!");

        }
    }

}
