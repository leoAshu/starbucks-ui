import processing.core.PApplet;
import processing.core.PImage;

/**
 * Authentication Proxy for App Controller
 */
public class Device implements IApp  {
    private static Device theDevice;
    private PApplet starbucks;
    private PImage notificationBar;

    private Device(PApplet starbucks) {
        this.starbucks = starbucks;
        this.notificationBar = starbucks.loadImage(Constants.NOTIF_BAR_IMAGE_PATH);
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
    }
    
}
