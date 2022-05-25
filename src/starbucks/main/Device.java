/**
 * Authentication Proxy for App Controller
 */
public class Device implements IApp  {
    private static Device theDevice;

    private Device() {
    }

    public synchronized static Device getDevice() {
        if(theDevice == null)
            theDevice = new Device();
        return theDevice;
    }

    public synchronized static Device getNewDevice() {
        theDevice = new Device();
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
        
    }
    
}
