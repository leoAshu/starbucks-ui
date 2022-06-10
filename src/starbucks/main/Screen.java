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
public class Screen implements IScreen, IDisplayComponent, ITouchEventHandler {
    protected PApplet starbucks;
    protected PImage background;
    private ITouchEventHandler chain;
    private ITouchEventHandler nextHandler;
    private List<IDisplayComponent> components;

    // private String backgroundPath;

    public Screen(PApplet starbucks, String bgPath) {
        this.starbucks = starbucks;
        if(bgPath != null)
            background = starbucks.loadImage(bgPath);

        this.components = new ArrayList<IDisplayComponent>();
    }

    @Override
    public String name() {
        return (this.getClass().getName()).split("\\.")[0];
    }

    @Override
    public void touch(int x, int y) {
        if(chain != null)
            chain.touch(x, y);
        if(nextHandler != null)
            nextHandler.touch(x, y);
    }

    @Override
    public void release() {
        if(chain != null)
            chain.release();
        if(nextHandler != null)
            nextHandler.release();
    }

    @Override
    public void display() {
        if(background != null){
            starbucks.image(
                background,
                0,
                Constants.NOTIF_BAR_HEIGHT + Constants.APP_BAR_HEIGHT
            );
        }

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
    public void setNext(ITouchEventHandler next) {
        nextHandler = next;
    }

    @Override
    public ITouchEventHandler getNext() {
        return nextHandler;
    }
    
}