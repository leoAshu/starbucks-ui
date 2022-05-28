import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

public class Tab implements ITab, IDisplayComponent, ITouchEventHandler {
    protected PApplet starbucks;
    protected ITabManager tabManager;
    private ITouchEventHandler chain;
    private List<IDisplayComponent> components;

    public Tab(PApplet starbucks) {
        this.starbucks = starbucks;
        components = new ArrayList<IDisplayComponent>();
    }
    
    /**
     * Sets the tab manager for the tab
     * @param tabManagerView Tab Manager for the tab
    */
    @Override
    public void setTabManager(ITabManager tabManager) {
        this.tabManager = tabManager;
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
            chain = (ITouchEventHandler)component;
        else {
            ITouchEventHandler prev = (ITouchEventHandler) components.get(components.size()-2);
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
        if(chain != null)
            chain.release();
    }


    @Override
    public void setNext(ITouchEventHandler next) {
        
    }
    
}
