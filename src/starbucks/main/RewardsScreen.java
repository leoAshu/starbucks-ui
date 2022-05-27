import processing.core.PApplet;

public class RewardsScreen extends Screen {
    
    public RewardsScreen(PApplet starbucks) {
        super(starbucks, Constants.SCREEN_BG_LIGHT);
        addSubComponent(new AppBar(starbucks, name()));
    }

    @Override
    public void display() {
        super.display();
    }
    
    @Override
    public String name() {
        return "My Rewards";
    }
}
