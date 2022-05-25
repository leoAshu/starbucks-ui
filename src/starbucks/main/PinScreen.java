import processing.core.PApplet;
import processing.core.PImage;

/** Pin Screen */
public class PinScreen extends Screen {
    private PImage background;

    PinScreen(PApplet starbucks) {
        super(starbucks);
        background = starbucks.loadImage(Constants.PIN_SCREEN_BG_PATH);
    }

    @Override
    public void display() {
        starbucks.image(background, 0, Constants.NOTIF_BAR_HEIGHT);
        super.display();
    }
}
