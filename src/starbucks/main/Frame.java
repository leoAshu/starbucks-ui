import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

/**
 * Frame Class -- Container for Screens
 */
public class Frame implements IFrame, IDisplayComponent {
    private PApplet starbucks;
    private AppBar appBar;
    private IScreen currentScreen;
    private List<IDisplayComponent> components;

    public Frame(PApplet starbucks, IScreen initial) {
        this.starbucks = starbucks;
        currentScreen = initial;
        appBar = new AppBar(starbucks, "Screen Name");
        components = new ArrayList<IDisplayComponent>();
        addSubComponent((IDisplayComponent)currentScreen);
        addSubComponent(appBar);
    }

    @Override
    public void setCurrentScreen(IScreen screen) {
        currentScreen = screen;
    }

    @Override
    public void touch(int x, int y) {
        if(currentScreen != null)
            currentScreen.touch(x, y);
    }

    @Override
    public void release() {
        if(currentScreen != null)
            currentScreen.release();
    }

    @Override
    public void display() {
        // if(currentScreen != null)
        //     currentScreen.display();
        for(IDisplayComponent component: components)
            component.display();
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {
        components.add(component);
    }

}

class AppBar implements IDisplayComponent {
    PApplet starbucks;
    private String screenName;

    public AppBar(PApplet starbucks, String screenName) {
        this.starbucks = starbucks;
        this.screenName = screenName;
    }

    public void display() {
        starbucks.image(
            starbucks.loadImage(Constants.APP_BAR_BG_PATH),
            0,
            Constants.NOTIF_BAR_HEIGHT
        );
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {

    }
}
