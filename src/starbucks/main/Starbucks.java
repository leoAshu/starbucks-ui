import processing.core.PApplet;

public class Starbucks extends PApplet {
    // private Grid grid;
    private IApp app;

    public void settings() {
        size(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        app = (IApp) Device.getDevice(this);
        // grid = new Grid(this);
    }

    public void draw() {
        app.display();
        // grid.display();
    }

    public void mousePressed() {
        app.touch(mouseX, mouseY);
    }

    public void mouseReleased() {
        app.release();
    }

    public static void main(String[] args) {
        String[] processingArgs = {"Starbucks"};
        Starbucks starbucks = new Starbucks();
        PApplet.runSketch(processingArgs, starbucks);
    }
}
