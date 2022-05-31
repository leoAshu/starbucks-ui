import processing.core.PApplet;

class AppBar implements IDisplayComponent, ITouchEventHandler {
    PApplet starbucks;
    private String screenName;
    private ITouchEventHandler nextHandler;

    public AppBar(PApplet starbucks, String screenName) {
        this.starbucks = starbucks;
        this.screenName = screenName;
    }

    public void setScreenName(String name) {
        screenName = name;
    }

    public void display() {
        // background
        starbucks.image(
            starbucks.loadImage(Constants.APP_BAR_BG),
            0,
            Constants.NOTIF_BAR_HEIGHT,
            Constants.APP_BAR_WIDTH,
            Constants.APP_BAR_HEIGHT
        );

        // screen name
        starbucks.textFont(starbucks.createFont(Constants.ROBOTO_MED_PATH, 64));

        starbucks.fill(255);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.textSize(22);
        starbucks.text(
            screenName,
            starbucks.width/2,
            Constants.NOTIF_BAR_HEIGHT + 34
        );
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {

    }

    @Override
    public void touch(int x, int y) {
        if(nextHandler != null)
            nextHandler.touch(x, y);
    }

    @Override
    public void release() {
        if(nextHandler != null)
            nextHandler.release();
    }

    @Override
    public void setNext(ITouchEventHandler next) {
        nextHandler = next;
    }

    @Override
    public ITouchEventHandler getNext() {
        return nextHandler;
    }
}