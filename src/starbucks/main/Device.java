import processing.core.PApplet;
import processing.core.PImage;

/**
 * Authentication Proxy for App Controller
 */
public class Device implements IApp, IPinAuthObserver  {
    private PApplet starbucks;
    private static Device theDevice;

    private IApp app;
    private KeyPad keyPad;
    private PinView pinView;
    private PinScreen pinScreen;
    private boolean authenticated;
    private PImage notificationBar;
    private SplashScreen splashScreen;
    private PinStatusView pinStatusView;
    private PinEntryMachine pinEntryMachine;

    private String pin;
    private boolean fourPin;
    private boolean sixPin;

    private Device(PApplet starbucks) {
        this.starbucks = starbucks;
        notificationBar = starbucks.loadImage(Constants.NOTIF_BAR_IMAGE);
    }

    /**
     * Get Singleton Instance
     * @return Reference to Current Device Config (Create if none exists)
     */
    public synchronized static Device getDevice(PApplet starbucks) {
        if(theDevice == null)
            return getNewDevice(starbucks, "1234");
        return theDevice;
    }

    public synchronized static Device getNewDevice(PApplet starbucks) {
        return getNewDevice(starbucks, "1234");
    }

    public synchronized static Device getNewDevice(PApplet starbucks, String pin) {
        theDevice = new Device(starbucks);
        theDevice.setPin(pin.trim());
        theDevice.startUp();
        return theDevice;
    }

    @Override
    public void touch(int x, int y) {
        if(authenticated) {
            app.touch(x, y);
        } else 
            pinScreen.touch(x, y);
    }

    @Override
    public void release() {
        if(authenticated) {
            app.release();
        } else 
            pinScreen.release();
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

    @Override
    public void authEvent(boolean isAuthenticated) {
        authenticated = isAuthenticated;
    }

    /**
     * Device Starup Process.  
     * Starts Up with Default 4-Pin Option
     */
    private void startUp() {
        if(theDevice.getPinOption() == 0)
            this.authenticated = true;
        else
            setUpPinScreen();
        
        splashScreen = new SplashScreen(starbucks);
        app = AppController.getNewAppController(starbucks);
    }

    /**  
     * Sets up the Pin Screen
     */
    private void setUpPinScreen() {
        pinStatusView = new PinStatusView(starbucks, getPinOption());
        pinView = new PinView(starbucks, getPinOption());
        keyPad = new KeyPad(starbucks);
        pinScreen = new PinScreen(starbucks);
        pinEntryMachine = new PinEntryMachine(starbucks);
        pinEntryMachine.setPin(pin);

        pinScreen.addSubComponent(pinStatusView);
        pinScreen.addSubComponent(pinView);
        pinScreen.addSubComponent(keyPad);

        ((IKeyPadSubject)keyPad).attach(pinStatusView);
        ((IKeyPadSubject)keyPad).attach(pinView);
        ((IKeyPadSubject)keyPad).attach(pinEntryMachine);

        ((IPinAuthSubject)pinEntryMachine).attach(this);
        ((IPinAuthSubject)pinEntryMachine).attach(pinStatusView);
    }

    /**
     * Return the current Pin Option:
     *  0 = User Chosed No Pin
     *  4 = User Chosed 4-digit Pin
     *  6 = User Chosed 6-digit Pin
     * @return Pin Option
     */
    private int getPinOption() {
        if (fourPin)
            return Constants.FOUR_PIN_OPTION;
        else if (sixPin)
            return Constants.SIX_PIN_OPTION;
        else
            return 0;
    }

    /**
     * Get Current Pin
     * @return Pin
     */
    // public String getPin() {
    //     return pin;
    // }

    /**
     * Set Pin
     * @param p New Pin
     */
    private void setPin(String p) {
        pin = p;
        fourPin = pin.length() == Constants.FOUR_PIN_OPTION;
        sixPin = pin.length() == Constants.SIX_PIN_OPTION;
    }

    private void screenDisplay() {
        if (starbucks.millis() < 2000)
            splashScreen.display();
        else {
            if(authenticated)
                app.display();
            else 
                pinScreen.display();
        }
    }
    
}
