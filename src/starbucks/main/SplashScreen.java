import processing.core.PApplet;
import processing.core.PImage;

public class SplashScreen extends Screen {
    private PImage logo;

    public SplashScreen(PApplet starbucks) {
        super(starbucks, null);
        background = starbucks.loadImage(Constants.SPLASH_SCREEN_BG);
        logo = starbucks.loadImage(Constants.STARBUCKS_LOGO_SOLID);
    }
    
    @Override
    public void display() {
        super.display();

        starbucks.image(
            background,
            0,
            Constants.NOTIF_BAR_HEIGHT
        );

        starbucks.image(
            logo,
            starbucks.width/2 - 120,
            starbucks.height/2 - 120,
            240,
            240
        );
    }
}
