import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

/**
 * Frame Class -- Container for Screens
 */
public class Frame implements IFrame, IDisplayComponent {
    private PApplet starbucks;
    private NavBar navBar;
    private IScreen currentScreen;
    private ITouchEventHandler chain;
    private List<IDisplayComponent> components;

    public Frame(PApplet starbucks, IScreen initial) {
        this.starbucks = starbucks;
        currentScreen = initial;

        components = new ArrayList<IDisplayComponent>();
        addSubComponent((IDisplayComponent)currentScreen);
    }

    @Override
    public void setCurrentScreen(IScreen screen) {
        currentScreen = screen;
        components.set(0, (IDisplayComponent)currentScreen);
    }

    @Override
    public void setupNavBar(List<INavBarCommand> commands) {
        if(navBar == null) {
            navBar = new NavBar(starbucks);
            addSubComponent(navBar);
        }
        navBar.setUp(commands);
    }

    @Override
    public void touch(int x, int y) {
        if(chain != null)
            chain.touch(x, y);
    }

    @Override
    public void release() {
        if(currentScreen != null)
            currentScreen.release();
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