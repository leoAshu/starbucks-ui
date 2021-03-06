import processing.core.PApplet;

public class StoresScreen extends Screen {
    
    public StoresScreen(PApplet starbucks) {
        super(starbucks, Constants.SCREEN_BG_LIGHT);
        addSubComponent(new AppBar(starbucks, name()));
    }

    @Override
    public void display() {
        super.display();
    }
    
    @Override
    public String name() {
        return "Find Stores";
    }
}
