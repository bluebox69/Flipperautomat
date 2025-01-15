package flipper.element;

import flipper.command.Command;

public class ExtraBall extends FlipperElement {
    private boolean granted = false; // Status, ob der ExtraBall bereits vergeben wurde

    @Override
    public void hit() {
        if (!granted) {
            granted = true;
            System.out.println("-- EXTRABALL -- Der Ball wurde gerettet! Das Spiel läuft weiter! + 1 Ball");
        } else {
            System.out.println("Extra Ball wurde bereits genutzt.");
        }
    }

    @Override
    public String getName() {
        return "ExtraBall";
    }

    public void reset() {
        granted = false;
    }

    public boolean isGranted() {
        return granted;
    }
}
