import processing.core.PApplet;

public class CardInputView implements IDisplayComponent, ITouchEventHandler {
    PApplet starbucks;
    private ITouchEventHandler nextHandler;

    public CardInputView(PApplet starbucks) {
        this.starbucks = starbucks;
    }

    @Override
    public void display() {
        // card
        starbucks.image(
            starbucks.loadImage(Constants.CARD_BACK_INPUT),
            (starbucks.width - Constants.CARD_WIDTH)/2,
            Constants.NOTIF_BAR_HEIGHT + Constants.APP_BAR_HEIGHT + Constants.CARD_TOP_PADDING - 20,
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
