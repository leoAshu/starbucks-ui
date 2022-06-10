import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

class AppBar implements IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private PFont roboto;
    private PImage background;
    private String screenName;
    private List<Button> options;
    
    private ITouchEventHandler chain;
    private ITouchEventHandler nextHandler;
    private List<IDisplayComponent> components;

    public AppBar(PApplet starbucks, String screenName) {
        this.starbucks = starbucks;
        this.screenName = screenName;
        background = starbucks.loadImage(Constants.APP_BAR_BG);

        components = new ArrayList<IDisplayComponent>();
    }

    public AppBar(PApplet starbucks, String screenName, List<Button> options) {
        this.starbucks = starbucks;
        this.screenName = screenName;
        this.options = options;
        background = starbucks.loadImage(Constants.APP_BAR_BG);

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
        if(roboto == null)
            roboto = starbucks.createFont(Constants.ROBOTO_MED_PATH, 22);

        // background
        starbucks.image(
            background,
            0,
            Constants.NOTIF_BAR_HEIGHT,
            Constants.APP_BAR_WIDTH,
            Constants.APP_BAR_HEIGHT
        );

        // screen name
        starbucks.textFont(roboto);

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
        if(isAppBarTouched(x, y) && chain != null){
            chain.touch(x, y);
            return;
        }

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

    private boolean isAppBarTouched(int x, int y) {
        return y > Constants.NAV_BAR_HEIGHT && y < (Constants.NAV_BAR_HEIGHT + Constants.APP_BAR_HEIGHT);
    }
}