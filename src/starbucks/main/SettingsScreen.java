import processing.core.PApplet;

public class SettingsScreen extends Screen {
    
    public SettingsScreen(PApplet starbucks) {
        super(starbucks, Constants.SCREEN_BG_LIGHT);
        addSubComponent(new AppBar(starbucks, name()));
    }

    @Override
    public void display() {
        super.display();
    }
    
    @Override
    public String name() {
        return "Settings";
    }
}
