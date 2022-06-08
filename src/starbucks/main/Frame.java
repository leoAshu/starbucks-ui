import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

/**
 * Frame Class -- Container for Screens
 */
public class Frame implements IFrame, IDisplayComponent {
    private PApplet starbucks;
    private NavBar navBar;
    private Overlay overlay;
    private boolean showOverlay;
    private IScreen currentScreen;
    private ITouchEventHandler chain;
    private List<IDisplayComponent> components;

    public Frame(PApplet starbucks, IScreen initial) {
        this.starbucks = starbucks;
        currentScreen = initial;
        overlay = new Overlay(starbucks);

        components = new ArrayList<IDisplayComponent>();
        addSubComponent((IDisplayComponent)currentScreen);
    }

    @Override
    public void setCurrentScreen(IScreen screen) {
        currentScreen = screen;

        ITouchEventHandler newChain = (ITouchEventHandler)currentScreen;
        newChain.setNext(chain.getNext());
        chain = (ITouchEventHandler)currentScreen;

        components.set(0, (IDisplayComponent)currentScreen);
        updateNavBar();
    }

    @Override
    public void setupNavBar(List<INavBarCommand> commands) {
        if(navBar == null) {
            navBar = new NavBar(starbucks);
            addSubComponent(navBar);
        }
        navBar.setUp(commands);
    }

    private void updateNavBar() {
        if(currentScreen.getClass().getName().contains("MyCardsScreen"))
            navBar.setActiveOption(0);
    }

    @Override
    public void setNavBarVisibility(boolean visibility) {
        navBar.setVisibility(visibility);
    }

    @Override
    public void touch(int x, int y) {
        if(showOverlay) {
            if(isOverlayTouched(y))
                overlay.touch(x, y);
            else
                hideOverlay();
                
            return;
        }

        if(chain != null)
            chain.touch(x, y);
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
        
        if(showOverlay)
            overlay.display();
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {
        components.add(component);
        if(components.size() == 1)
            chain = (ITouchEventHandler) component;
        else {
            ITouchEventHandler prev = (ITouchEventHandler) components.get(components.size()-2);
            prev.setNext((ITouchEventHandler) component);
        } 
    }

    public void showOverlay() {
        showOverlay = true;
    }

    public void hideOverlay() {
        showOverlay = false;
    }

    public void addOptionsToOverlay(List<Button> options) {
        overlay.addOptions(options);
    }

    private boolean isOverlayTouched(int y) {
        return y > (starbucks.height - Constants.OVERLAY_HEIGHT);
    }

}