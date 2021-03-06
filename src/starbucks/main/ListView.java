import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class ListView implements IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private int y;
    private List<ListItem> items;
    private boolean isTouched;

    private ITouchEventHandler chain;
    private ITouchEventHandler nextHandler;
    private List<IDisplayComponent> components;

    public ListView(PApplet starbucks, int y, List<ListItem> items) {
        this.starbucks = starbucks;
        this.y = y;
        this.items = items;
        isTouched = false;

        components = new ArrayList<IDisplayComponent>();
        setUpListItems();
    }

    private void setUpListItems() {
        int itemY = y;

        // first list item
        items.get(0).setItemProps(starbucks, itemY, Constants.LIST_ITEM_FIRST_BG, Constants.LIST_ITEM_FIRST_PRESSED_BG);
        addSubComponent(items.get(0));

        // middle list items
        for(int i=1; i<items.size()-1; i++) {
            itemY += Constants.SETTINGS_ITEM_HEIGHT - 1;
            items.get(i).setItemProps(starbucks, itemY, Constants.LIST_ITEM_BG, Constants.LIST_ITEM_PRESSED_BG);
            addSubComponent(items.get(i));
        }

        // last list item
        itemY += Constants.SETTINGS_ITEM_HEIGHT - 1;
        items.get(items.size()-1).setItemProps(starbucks, itemY, Constants.LIST_ITEM_LAST_BG, Constants.LIST_ITEM_LAST_PRESSED_BG);
        addSubComponent(items.get(items.size()-1));

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
            ITouchEventHandler prev = (ITouchEventHandler)components.get(components.size()-2);
            prev.setNext((ITouchEventHandler)component);
        }
    }

    @Override
    public void touch(int x, int y) {
        if(isTouched(x, y)) {
            isTouched = true;
            if(chain != null)
                chain.touch(x, y);
            return;
        }
        
        if(nextHandler != null)
            nextHandler.touch(x, y);
    }

    @Override
    public void release() {
        if(isTouched) {
            if(chain != null)
                chain.release();
            isTouched = false;
            return;
        }
        
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

    private boolean isTouched(int x, int y) {
        int startX = (starbucks.width - Constants.SETTINGS_ITEM_WIDTH)/2;
        int endX = (starbucks.width - Constants.SETTINGS_ITEM_WIDTH)/2 + Constants.SETTINGS_ITEM_WIDTH;
        boolean overX = x > startX && x < endX;
        boolean overY = y > this.y && y < (this.y + Constants.SETTINGS_ITEM_HEIGHT * items.size());
        return overX && overY;
    }
    
}
