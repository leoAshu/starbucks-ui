import processing.core.PApplet;

public class CardBackView implements IDisplayComponent, ITouchEventHandler {
    PApplet starbucks;
    private ICard card;
    private ITouchEventHandler nextHandler;

    public CardBackView(PApplet starbucks, ICard card) {
        this.starbucks = starbucks;
        this.card = card;
    }

    @Override
    public void display() {
        // card
        starbucks.image(
            starbucks.loadImage(Constants.CARD_BACK),
            (starbucks.width - Constants.CARD_WIDTH)/2,
            Constants.NOTIF_BAR_HEIGHT + Constants.APP_BAR_HEIGHT + Constants.CARD_TOP_PADDING,
            Constants.CARD_WIDTH, 
            Constants.CARD_HEIGHT
        );

        // card details
        starbucks.textFont(starbucks.createFont(Constants.ROBOTO_MED_PATH, 18));
        starbucks.fill(0);
        starbucks.textSize(22);
        starbucks.textAlign(PApplet.CENTER);
        // card number
        starbucks.text(
            card.cardNum(),
            180,
            Constants.NOTIF_BAR_HEIGHT + Constants.APP_BAR_HEIGHT + Constants.CARD_NUM_TOP_PADDING
        );
        // card code
        starbucks.textSize(18);
        starbucks.text(
            card.cardCode(),
            310,
            Constants.NOTIF_BAR_HEIGHT + Constants.APP_BAR_HEIGHT + Constants.CARD_NUM_TOP_PADDING
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
    
}
