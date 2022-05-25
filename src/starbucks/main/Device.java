import processing.core.PApplet;
import processing.core.PImage;

/**
 * Authentication Proxy for App Controller
 */
public class Device implements IApp  {
    private PApplet starbucks;
    private static Device theDevice;

    private boolean authenticated;
    private PImage notificationBar;
    private PinScreen pinScreen;
    private KeyPad keyPad;

    private Device(PApplet starbucks) {
        this.starbucks = starbucks;
        this.notificationBar = starbucks.loadImage(Constants.NOTIF_BAR_IMAGE_PATH);
        startUp();
    }

    public synchronized static Device getDevice(PApplet starbucks) {
        if(theDevice == null)
            return getNewDevice(starbucks);
        return theDevice;
    }

    public synchronized static Device getNewDevice(PApplet starbucks) {
        theDevice = new Device(starbucks);
        return theDevice;
    }

    /**
     * Device Starup Process.  
     * Starts Up with Default 4-Pin Option
     */
    private void startUp() {
        setUpPinScreen();
    }

    /**  
     * Sets up the Pin Screen
     */
    private void setUpPinScreen() {
        keyPad = new KeyPad(starbucks);
        pinScreen = new PinScreen(starbucks);

        pinScreen.addSubComponent(keyPad);
    }


    @Override
    public void touch(int x, int y) {
        
    }

    @Override
    public void release() {
        
    }

    @Override
    public void display() {
        starbucks.image(
            notificationBar,
            0,
            0,
            Constants.NOTIF_BAR_WIDTH,
            Constants.NOTIF_BAR_HEIGHT
        );
        screenDisplay();
    }

    private void screenDisplay() {
        if(authenticated) {
            // App Screen
        } else 
            pinScreen.display();
    }
    
}
