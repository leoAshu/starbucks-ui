import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

public class MyCardsScreen extends Screen {

    public MyCardsScreen(PApplet starbucks) {
        super(starbucks, Constants.SCREEN_BG_DARK);
        setUpScreen();
    }

    @Override
    public void display() {
        super.display();
    }
    
    @Override
    public String name() {
        return "My Cards";
    }

    private void setUpScreen() {
        // app bar
        addSubComponent(new AppBar(starbucks, name()));

        // tab view
        List<Tab> tabs = new ArrayList<Tab>();
        tabs.add(new MyCardsMainTab(starbucks));
        tabs.add(new MyCardsPayTab(starbucks));
        addSubComponent((IDisplayComponent)new TabManagerView(starbucks, tabs, 0));
    }

}
