package flipper.composite;

import flipper.element.FlipperElement;

import java.util.ArrayList;
import java.util.List;

public class FlipperGroup implements FlipperComponent {
    private List<FlipperComponent> components = new ArrayList<>();
    private boolean bonusActive = false;

    public void add(FlipperComponent component) {
        if (component == this || isCyclic(component)) {
            System.out.println("Zyklische Abhängigkeit erkannt. Hinzufügen abgelehnt.");
            return;
        }
        components.add(component);
    }

    public boolean allHit() {
        return components.stream()
                .filter(component -> component instanceof FlipperElement)
                .allMatch(component -> ((FlipperElement) component).isHit());
    }

    public List<FlipperComponent> getTargets() {
        return components;
    }

    private boolean isCyclic(FlipperComponent component) {
        if (component instanceof FlipperGroup) {
            for (FlipperComponent child : ((FlipperGroup) component).components) {
                if (child == this || isCyclic(child)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void hit() {
        System.out.println("Gruppe getroffen! Trigger für alle Elemente:");
        for (FlipperComponent component : components) {
            component.hit();
        }
    }

    public void checkAndActivateBonus() {
        if (allHit() && !bonusActive) {
            bonusActive = true;
            System.out.println("Bonusmodus für Bumper aktiviert!");
        }
    }

    public boolean isBonusActive() {
        return bonusActive;
    }

    public void setBonusActive(boolean bonusActive) {
        this.bonusActive = bonusActive;
    }


}