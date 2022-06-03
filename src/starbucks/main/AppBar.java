import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

class AppBar implements IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private String screenName;
    private List<Button> options;
    
    private ITouchEventHandler chain;
    private ITouchEventHandler nextHandler;
    private List<IDisplayComponent> components;

    public AppBar(PApplet starbucks, String screenName) {
        this.starbucks = starbucks;
        this.screenName = screenName;

        components = new ArrayList<IDisplayComponent>();
    }

    public AppBar(PApplet starbucks, String screenName, List<Button> options) {
        this.starbucks = starbucks;
        this.screenName = screenName;
        this.options = options;

        components = new ArrayList<IDisplayComponent>();

        if(options != null)
            setUpAppBarOptions();
    }

    public void setScreenName(String name) {
        screenName = name;
    }

    private void setUpAppBarOptions() {
        for(IDisplayComponent component: options)
            addSubComponent(component);
    }

    @Override
    public void display() {
        // background
        starbucks.image(
            starbucks.loadImage(Constants.APP_BAR_BG),
            0,
            Constants.NOTIF_BAR_HEIGHT,
            Constants.APP_BAR_WIDTH,
            Constants.APP_BAR_HEIGHT
        );

        // screen name
        starbucks.textFont(starbucks.createFont(Constants.ROBOTO_MED_PATH, 64));

        starbucks.fill(255);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.textSize(22);
        starbucks.text(
            screenName,
            starbucks.width/2,
            Constants.NOTIF_BAR_HEIGHT + 34
        );

        for(IDisplayComponent component: components)
            component.display();
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {
        components.add(component);
        if(components.size() == 1)
            chain = (ITouchEventHandler)component;
        else {
            ITouchEventHandler prev = (ITouchEventHandler)components.get(components.size()-2);
            prev.setNext((ITouchEventHandler)component);
        }
    }

    @Override
    public void touch(int x, int y) {
        if(nextHandler != null)
            nextHandler.touch(x, y);
    }

    @Override
    public void release() {
        if(nextHandler != null)
            nextHandler.release();
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