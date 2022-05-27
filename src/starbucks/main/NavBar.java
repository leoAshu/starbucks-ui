import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

class NavBar implements INavBar, IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private ITouchEventHandler chain;
    private List<IDisplayComponent> options;

    public NavBar(PApplet starbucks) {
        this.starbucks = starbucks;
        options = new ArrayList<IDisplayComponent>();
    }

    @Override
    public void addNavBarOption(String label, String iconPath, String activeIconPath, INavBarCommand command) {
        int x = options.size() * starbucks.width/5;
        NavBarOption option = new NavBarOption(
            starbucks,
            label,
            iconPath,
            activeIconPath, 
            x,
            starbucks.height - Constants.NAV_BAR_HEIGHT,
            starbucks.width/5
        );
        option.setCommand(command);
        addSubComponent(option);
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

    @Override
    public void touch(int x, int y) {
        if(chain != null)
            chain.touch(x, y);
    }

    @Override
    public void release() {
        
    }

    @Override
    public void setNext(ITouchEventHandler next) {
        
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