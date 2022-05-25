import processing.core.PApplet;;

public class Starbucks extends PApplet {
    private IApp app;

    public void settings() {
        size(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        app = (IApp) Device.getDevice(this);
    }

    public void draw() {
        background(255);
        app.display();
    }

    public void mousePressed() {

    }

    public void mouseReleased() {

    }

    public static void main(String[] args) {
        String[] processingArgs = {"Starbucks"};
        Starbucks starbucks = new Starbucks();
        PApplet.runSketch(processingArgs, starbucks);
    }
}
