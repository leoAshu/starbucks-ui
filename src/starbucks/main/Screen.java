import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

/**
 * Base Class for Screens.
 * 
 * Provides Common Functionality
 * For Setting Up the Composite and 
 * Chain of Responsibility Patterns.
 * 
 */
public class Screen implements IScreen, IDisplayComponent {
    protected PApplet starbucks;
    private ITouchEventHandler chain;
    private List<IDisplayComponent> components;

    public Screen(PApplet starbucks) {
        this.starbucks = starbucks;
        this.components = new ArrayList<IDisplayComponent>();
    }

    @Override
    public void touch(int x, int y) {
        if(chain != null)
            chain.touch(x, y);
    }

    @Override
    public void release() {
        if(chain != null)
            chain.release();
    }

    @Override
    public void display() {
       for(IDisplayComponent component: components)
            component.display();
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {
        components.add(component);
        if(components.size() == 1)
            chain = (ITouchEventHandler) component;
        else {
            ITouchEventHandler prev = (ITouchEventHandler) components.get(components.size()-2);
            prev.setNext((ITouchEventHandler) component);
        }
    }
    
}
