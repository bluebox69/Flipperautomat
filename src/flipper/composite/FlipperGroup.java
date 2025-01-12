package flipper.composite;

import java.util.ArrayList;
import java.util.List;

public class FlipperGroup implements FlipperComponent {
    private List<FlipperComponent> components = new ArrayList<>();

    public void add(FlipperComponent component) {
        if (component == this || isCyclic(component)) {
            System.out.println("Zyklische Abhängigkeit erkannt. Hinzufügen abgelehnt.");
            return;
        }
        components.add(component);
    }

    public void remove(FlipperComponent component) {
        components.remove(component);
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
}