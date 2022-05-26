import processing.core.PApplet;
import processing.core.PImage;

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

    private PImage background;

    public Screen(PApplet starbucks, String bgPath) {
        this.starbucks = starbucks;
        this.components = new ArrayList<IDisplayComponent>();

        background = starbucks.loadImage(bgPath);
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
        starbucks.image(background, 0, Constants.NOTIF_BAR_HEIGHT);
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

    @Override
    public String name() {
        return (this.getClass().getName()).split("\\.")[0];
    }
    
}