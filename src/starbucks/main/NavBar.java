import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

class NavBar implements INavBar, IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private int y;
    private ITouchEventHandler chain;
    private List<IDisplayComponent> options;
    private boolean isVisible;

    public NavBar(PApplet starbucks) {
        this.starbucks = starbucks;
        options = new ArrayList<IDisplayComponent>();
        y = starbucks.height - Constants.NAV_BAR_HEIGHT;
        isVisible = true;
    }

    @Override
    public void setUp(List<INavBarCommand> commands) {
        int x = 0;
        int optionWidth = Constants.NAV_BAR_WIDTH/commands.size();
        for(int i=0; i<commands.size(); i++) {
            NavBarOption option = new NavBarOption(
                starbucks,
                x,
                y,
                optionWidth
            );
            option.setCommand(commands.get(i));
            addSubComponent(option);
            x += optionWidth;
        }
    }

    @Override
    public void setVisibility(boolean visibility) {
        isVisible = visibility;
    }

    @Override
    public void setActiveOption(int index) {
        for(int i=0; i<options.size(); i++) {
            if(i == index)
                ((NavBarOption)options.get(i)).setActive(true);
            else
            ((NavBarOption)options.get(i)).setActive(false);
        }
    }

    @Override
    public void display() {
        if(!isVisible)
            return;
        Utility.setVerticalGradient(
            starbucks,
            0,
            starbucks.height - Constants.NAV_BAR_HEIGHT,
            Constants.NAV_BAR_WIDTH,
            Constants.NAV_BAR_HEIGHT,
            58,
            28
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
        if(isNavBarTouched(x, y) && chain != null && isVisible)
            chain.touch(x, y);
    }

    @Override
    public void release() {
        
    }

    @Override
    public void setNext(ITouchEventHandler next) {
        
    }

    @Override
    public ITouchEventHandler getNext() {
        return null;
    }

    private boolean isNavBarTouched(int x, int y) {
        return y > (starbucks.height - Constants.NAV_BAR_HEIGHT);
    }

}