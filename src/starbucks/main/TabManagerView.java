import processing.core.PApplet;

import java.util.List;

public class TabManagerView implements ITabManager, IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private List<Tab> tabs;
    private int currentTabIndex;

    public TabManagerView(PApplet starbucks, List<Tab> tabs, int currentTabIndex) {
        this.starbucks = starbucks;
        this.tabs = tabs;
        this.currentTabIndex = currentTabIndex;
        setUpTabs();
    }

    /** Assigns the tab manager for all tabs */
    @Override
    public void setUpTabs() {
        for(ITab tab: tabs)
            tab.setTabManager(this);
    }

    /** 
     * Changes the active tab
     * @param index Index of the new active tab
     */
    @Override
    public void setTab(int index) {
        currentTabIndex = index;
    }

    @Override
    public void display() {
        tabs.get(currentTabIndex).display();
        starbucks.fill(255);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.textSize(20);
        starbucks.text(""+currentTabIndex, starbucks.width/2, Constants.NOTIF_BAR_HEIGHT + Constants.APP_BAR_HEIGHT + 14);
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {
        
    }

    @Override
    public void touch(int x, int y) {
        tabs.get(currentTabIndex).touch(x, y);
    }

    @Override
    public void release() {
        tabs.get(currentTabIndex).release();
    }

    @Override
    public void setNext(ITouchEventHandler next) {
       
    }
    
}