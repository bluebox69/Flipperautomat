package flipper.main;

import flipper.command.MacroCommand;
import flipper.command.OpenRampCommand;
import flipper.command.ScoreCommand;
import flipper.composite.FlipperGroup;
import flipper.element.Bumper;
import flipper.element.FlipperElement;
import flipper.element.Target;
import flipper.factory.FactorySelector;
import flipper.factory.PoisonFactory;
import flipper.mediator.FlipperMediator;
import flipper.mediator.Mediator;
import flipper.state.FlipperMachine;
import flipper.utils.GameManager;
import flipper.visitor.PunkteVisitor;
import flipper.visitor.ResetVisitor;


public class Main {
    public static void main(String[] args) {
        // 1. **Factory Setup**: Poison-Stil für ASCII-Ausgaben
        FactorySelector.setFactory(new PoisonFactory());
        System.out.println("=== Poison-Stil: Press Start ===");
        System.out.println(FactorySelector.getFactory().renderPressStart());

        // 2. **State-Pattern Test**
        System.out.println("\n=== Test: Spielzustände ===");
        FlipperMachine machine = new FlipperMachine();
        machine.pressStart(); // Kein Kredit
        machine.insertCoin(); // Kredit hinzufügen
        machine.pressStart(); // Spiel starten
        machine.loseBall();   // Ball verlieren
        machine.loseBall();   // Ball verlieren
        machine.loseBall();   // Letzter Ball
        machine.pressStart(); // Spiel beendet

        // 3. **Flipper-Elemente und Composite-Pattern**
        System.out.println("\n=== Test: Flipper-Elemente ===");
        FlipperElement bumper = new Bumper();
        FlipperElement target1 = new Target();
        FlipperElement target2 = new Target();
        bumper.setCommand(new ScoreCommand(50));
        target1.setCommand(new ScoreCommand(100));
        target2.setCommand(new MacroCommand() {{
            addCommand(new ScoreCommand(150));
            addCommand(new OpenRampCommand());
        }});

        FlipperGroup group = new FlipperGroup();
        group.add(bumper);
        group.add(target1);
        group.add(target2);

        System.out.println("Treffer auf Gruppe:");
        group.hit(); // Teste Treffer auf die gesamte Gruppe

        // 4. **Mediator-Pattern Test**
        System.out.println("\n=== Test: Mediator ===");
        Mediator mediator = new FlipperMediator();
        ((Target) target1).setMediator(mediator);
        ((Target) target2).setMediator(mediator);
        target1.hit(); // Notify Mediator
        target2.hit(); // Notify Mediator

        // 5. **Visitor-Pattern Test**
        System.out.println("\n=== Test: PunkteVisitor ===");
        PunkteVisitor punkteVisitor = new PunkteVisitor();
        bumper.accept(punkteVisitor);
        target1.accept(punkteVisitor);
        target2.accept(punkteVisitor);
        System.out.println("Gesamtpunkte durch PunkteVisitor: " + punkteVisitor.getTotalPoints());

        System.out.println("\n=== Test: ResetVisitor ===");
        ResetVisitor resetVisitor = new ResetVisitor();
        bumper.accept(resetVisitor);
        target1.accept(resetVisitor);
        target2.accept(resetVisitor);

        // 6. **Endgültige Punktestand**
        System.out.println("\nEndgültiger Punktestand: " + GameManager.getInstance().getScore());
    }
}