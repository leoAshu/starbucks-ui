import processing.core.PApplet;

/** Pin Screen */
public class PinScreen extends Screen {
    
    PinScreen(PApplet starbucks) {
        super(starbucks, null);
    }

    @Override
    public void display() {
        starbucks.image(
            starbucks.loadImage(Constants.PIN_SCREEN_BG_PATH),
            0,
            Constants.NOTIF_BAR_HEIGHT
        );
        super.display();
    }
}
