import processing.core.PApplet;

public class MyCardsScreen extends Screen {

    public MyCardsScreen(PApplet starbucks) {
        super(starbucks, Constants.MAIN_SCREEN_BG_PATH);
    }

    @Override
    public void display() {
        super.display();
        starbucks.fill(0);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.text("MyCardsScreen", starbucks.width/2, starbucks.height/2);
    }
    
    @Override
    public String name() {
        return "My Cards";
    }
}
