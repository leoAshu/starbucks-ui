import java.util.List;

public class TabManagerView implements ITabManager, IDisplayComponent, ITouchEventHandler {
    private List<Tab> tabs;
    private int currentTabIndex;

    public TabManagerView(List<Tab> tabs, int currentTabIndex) {
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

    @Override
    public ITouchEventHandler getNext() {
        return null;
    }
    
}
