import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

/**
 * Frame Class -- Container for Screens
 */
public class Frame implements IFrame, IDisplayComponent {
    private PApplet starbucks;
    private AppBar appBar;
    private NavBar navBar;
    private IScreen currentScreen;
    private List<IDisplayComponent> components;

    public Frame(PApplet starbucks, IScreen initial) {
        this.starbucks = starbucks;
        currentScreen = initial;

        appBar = new AppBar(this.starbucks, currentScreen.name());
        navBar = new NavBar(starbucks);

        components = new ArrayList<IDisplayComponent>();
        addSubComponent(appBar);
        addSubComponent((IDisplayComponent)currentScreen);
        addSubComponent(navBar);
    }

    @Override
    public void setCurrentScreen(IScreen screen) {
        currentScreen = screen;
    }

    @Override
    public void touch(int x, int y) {
        // needs chain of responsibility
        // might have to modify screens
        // screens should implement ITouchEventHandler
        if(y < Constants.NOTIF_BAR_HEIGHT) {
            starbucks.text("Notification Bar Touched!", starbucks.width/2, starbucks.height/2);
        } else if( y < (Constants.NOTIF_BAR_HEIGHT + Constants.APP_BAR_HEIGHT)) {
            starbucks.text("App Bar Touched!", starbucks.width/2, starbucks.height/2);
        } else if(y < (starbucks.height - Constants.NAV_BAR_HEIGHT)) {
            starbucks.text("Screen Touched!", starbucks.width/2, starbucks.height/2);
        } else {
            starbucks.text("Nav Bar Touched!", starbucks.width/2, starbucks.height/2);
            navBar.touch(x, y);
        }
        // if(currentScreen != null)
        //     currentScreen.touch(x, y);
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

}

class AppBar implements IDisplayComponent {
    PApplet starbucks;
    private String screenName;

    public AppBar(PApplet starbucks, String screenName) {
        this.starbucks = starbucks;
        this.screenName = screenName;
    }

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
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {

    }
}

class NavBar implements IDisplayComponent {
    private PApplet starbucks;
    private ITouchEventHandler chain;
    private List<IDisplayComponent> options;

    public NavBar(PApplet starbucks) {
        this.starbucks = starbucks;
        options = new ArrayList<IDisplayComponent>();
        addNavBarOptions();
    }

    @Override
    public void display() {
        setGradient(
            0 ,
            starbucks.height - Constants.NAV_BAR_HEIGHT,
            Constants.NAV_BAR_WIDTH,
            Constants.NAV_BAR_HEIGHT
        );

        for(IDisplayComponent option: options)
            option.display();
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {
        options.add(component);
        if(options.size() == 1)
            chain = (ITouchEventHandler) component;
        else {
            ITouchEventHandler prev = (ITouchEventHandler) options.get(options.size()-2);
            prev.setNext((ITouchEventHandler) component);
        }
    }

    public void touch(int x, int y) {
        if(chain != null)
            chain.touch(x, y);
    }

    private void addNavBarOptions() {
        int x = 0;
        for(int i=0; i<5; i++) {
            addSubComponent(
                new NavBarOption(
                    starbucks, 
                    Constants.NAV_OPTION_LABELS[i], 
                    Constants.NAV_OPTION_ICONS[i],
                    Constants.ACTIVE_NAV_OPTION_ICONS[i],
                    x, 
                    starbucks.height - Constants.NAV_BAR_HEIGHT,
                    starbucks.width/5, 
                    Constants.NAV_BAR_HEIGHT
                )
            );
            x += starbucks.width/5;
        }
    }

    // linear gradient: vertical
    private void setGradient(int x, int y, int w, int h) {
        starbucks.noFill();
        for (int i = y; i <= y+h; i++) {
            float inter = PApplet.map(i, y, y+h, 0, 1);
            int c = starbucks.lerpColor(58, 28, inter);
            starbucks.stroke(c);
            starbucks.line(x, i, x+w, i);
        }
    }

}

class NavBarOption implements IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private String label;
    private String defaultIcon;
    private String activeIcon;
    int x;
    int y;
    private int width;
    private int height;
    private boolean isActive;

    private ITouchEventHandler nextHandler;

    public NavBarOption(PApplet starbucks, String label, String defaultIcon, String activeIcon, int x, int y, int width, int height) {
        this.starbucks = starbucks;
        this.label = label;
        this.defaultIcon = defaultIcon;
        this.activeIcon = activeIcon;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        isActive = x==0;
    }

    @Override
    public void display() {
        if(isActive){
            setGradient(x, y, width, height);
        }
        drawLabels();
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {
        
    }

    @Override
    public void touch(int x, int y) {
        boolean overX = x > this.x && x < (this.x + width);
        boolean overY = y > this.y && y < (this.y + height);
        
        if(overX && overY)
            toggleState(true);
        else
            toggleState(false);
        
            if(nextHandler != null)
            nextHandler.touch(x, y);
    }

    @Override
    public void release() {
        
    }

    @Override
    public void setNext(ITouchEventHandler next) {
        nextHandler = next;
    }

    // linear gradient: vertical
    private void setGradient(int x, int y, int w, int h) {
        starbucks.noFill();
        for (int i = y; i <= y+h-30; i++) {
            float inter = PApplet.map(i, y, y+h-30, 0, 1);
            int c = starbucks.lerpColor(80, 64, inter);
            starbucks.stroke(c);
            starbucks.line(x, i, x+w, i);
        }
        for (int i = y+h-30; i <= y+h; i++) {
            float inter = PApplet.map(i, y+h-30, y+h, 0, 1);
            int c = starbucks.lerpColor(64, 47, inter);
            starbucks.stroke(c);
            starbucks.line(x, i, x+w, i);
        }
    }

    private void drawLabels() {
        // icon
        PImage icon = starbucks.loadImage(isActive? activeIcon: defaultIcon);
        
        starbucks.tint(255, isActive? 255: 150);
        starbucks.image(
            icon,
            x + (width - icon.width)/2,
            546
        );

        // label
        starbucks.textFont(starbucks.createFont(Constants.ROBOTO_MED_PATH, 10));

        starbucks.fill(255, isActive? 255: 150);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.textSize(10);
        starbucks.text(
            label,
            x + width/2,
            // labelAlphabet.isEmpty()? y + (Constants.CELL_HEIGHT/2) + 6: y + (Constants.CELL_HEIGHT/2) + 2
            585
        );
    }

    public void toggleState(boolean state) {
        isActive = state;
        // if(!isSelected) {
        //     isSelected = true;
        //     // callback to frame to change screen
        // }
    }

}