import processing.core.PApplet;

/** Pin Screen */
public class PinScreen extends Screen {
    
    PinScreen(PApplet starbucks) {
        super(starbucks, null);
        background = starbucks.loadImage(Constants.PIN_SCREEN_BG);
    }

    @Override
    public void display() {
        starbucks.image(
            background,
            0,
            Constants.NOTIF_BAR_HEIGHT
        );
        super.display();
    }
}
