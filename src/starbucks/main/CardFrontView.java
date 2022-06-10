import processing.core.PApplet;
import processing.core.PImage;

public class CardFrontView implements IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private PImage background;
    private ITouchEventHandler nextHandler;

    public CardFrontView(PApplet starbucks) {
        this.starbucks = starbucks;
        background = starbucks.loadImage(Constants.CARD_FRONT);
    }

    @Override
    public void display() {
        // card
        starbucks.image(
            background,
            (starbucks.width - Constants.CARD_WIDTH)/2,
            Constants.NOTIF_BAR_HEIGHT + Constants.APP_BAR_HEIGHT + Constants.CARD_TOP_PADDING,
            Constants.CARD_WIDTH,
            Constants.CARD_HEIGHT
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
