import processing.core.PApplet;

/**
 * Main App Controller Class
 */
public class AppController implements IApp {
    private PApplet starbucks;
    private static AppController theController;

    private IScreen myCardsScreen;

    private AppController(PApplet starbucks) {
        this.starbucks = starbucks;
    }

    public synchronized static AppController getAppController(PApplet starbucks) {
        if(theController == null)
            return getNewAppController(starbucks);
        return theController;
    }

    public synchronized static AppController getNewAppController(PApplet starbucks) {
        theController = new AppController(starbucks);
        theController.init();
        return theController;
    }

    @Override
    public void touch(int x, int y) {
        myCardsScreen.touch(x, y);
    }

    @Override
    public void release() {
        myCardsScreen.release();
    }

    @Override
    public void display() {
        myCardsScreen.display();
    }

    private void init() {
        myCardsScreen = new MyCardsScreen(starbucks);
    }
    
}
