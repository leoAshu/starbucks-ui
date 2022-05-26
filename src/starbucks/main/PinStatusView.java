import processing.core.PApplet;

public class PinStatusView implements IDisplayComponent, ITouchEventHandler, IPinAuthObserver, IKeyPadObserver {
    private PApplet starbucks;
    private ITouchEventHandler nextHandler;

    private int pinOption;
    private boolean isPinValid;
    private int pinDigitsCount;

    public PinStatusView(PApplet starbucks, int pinOption) {
        this.starbucks = starbucks;

        isPinValid = true;
        this.pinDigitsCount = 0;
        this.pinOption = pinOption;
    }

    @Override
    public void display() {
        starbucks.textFont(starbucks.createFont(Constants.ROBOTO_MED_PATH, 64));

        starbucks.textAlign(PApplet.CENTER);
        starbucks.textSize(16);

        starbucks.text(
            isPinValid? "Enter Passcode": "Invalid Passcode!",
            starbucks.width/2,
            100
        );
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {

    }

    @Override
    public void touch(int x, int y) {
        if (nextHandler != null)
            nextHandler.touch(x, y);
    }

    @Override
    public void release() {
        if (nextHandler != null)
            nextHandler.release();
    }

    @Override
    public void setNext(ITouchEventHandler next) {
        nextHandler = next;
    }

    @Override
    public void authEvent(boolean isAuthenticated) {
        isPinValid = isAuthenticated;
    }

    @Override
    public void keyEventUpdate(int keyCount, String key) {
        if(key.equalsIgnoreCase("X")) {
            if(pinDigitsCount > 0)
                pinDigitsCount += 1;
        } else {
            if(pinDigitsCount < pinOption) {
                pinDigitsCount += 1;
                
                if(pinDigitsCount == pinOption)
                    pinDigitsCount = 0;

                if(pinDigitsCount == 1)
                    isPinValid = true;
            }
        }
    }
}
