import processing.core.PApplet;

public class MyCardsScreen extends Screen {

    public MyCardsScreen(PApplet starbucks) {
        super(starbucks, Constants.MAIN_SCREEN_BG_PATH);
    }

    @Override
    public void display() {
        super.display();
    }
    
    @Override
    public String name() {
        return "My Cards";
    }
}
