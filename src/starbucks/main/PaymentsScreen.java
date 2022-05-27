import processing.core.PApplet;

public class PaymentsScreen extends Screen {
    
    public PaymentsScreen(PApplet starbucks) {
        super(starbucks, Constants.SCREEN_BG_DARK);
        addSubComponent(new AppBar(starbucks, name()));
    }

    @Override
    public void display() {
        super.display();
    }
    
    @Override
    public String name() {
        return "Payments";
    }
}
