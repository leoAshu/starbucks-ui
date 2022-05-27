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
    public void touch(int x, int y) {
        // needs chain of responsibility
        // might have to modify screens
        // screens should implement ITouchEventHandler
        if(y < Constants.NOTIF_BAR_HEIGHT) {
            // starbucks.text("Notification Bar Touched!", starbucks.width/2, starbucks.height/2);
        } else if( y < (Constants.NOTIF_BAR_HEIGHT + Constants.APP_BAR_HEIGHT)) {
            // starbucks.text("App Bar Touched!", starbucks.width/2, starbucks.height/2);
        } else if(y < (starbucks.height - Constants.NAV_BAR_HEIGHT)) {
            // starbucks.text("Screen Touched!", starbucks.width/2, starbucks.height/2);
        } else {
            // starbucks.text("Nav Bar Touched!", starbucks.width/2, starbucks.height/2);
            navBar.touch(x, y);
        }
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
    }

    @Override
    public void setupNavBar(List<INavBarCommand> commands) {
        if(navBar == null) {
            navBar = new NavBar(starbucks);
            addSubComponent(navBar);
        }
        navBar.setUp(commands);
    }

}