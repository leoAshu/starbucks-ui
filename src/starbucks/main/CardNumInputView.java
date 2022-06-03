import processing.core.PApplet;

/** 
 * CardNumInputView Screen SubComponent
 * Displays the entered card number digits
 */
public class CardNumInputView implements IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private StringBuffer cardNum;

    private ITouchEventHandler nextHandler;

    public CardNumInputView(PApplet starbucks) {
        this.starbucks = starbucks;
        cardNum = new StringBuffer();
    }

    @Override
    public void display() {
        // text box
        starbucks.image(
            starbucks.loadImage(Constants.CARD_NUM_INPUT_BG),
            Constants.CARD_NUM_INPUT_X,
            Constants.CARD_NUM_INPUT_Y,
            Constants.CARD_NUM_INPUT_WIDTH,
            Constants.CARD_NUM_INPUT_HEIGHT
        );

        // card number
        boolean isEmpty = cardNum.toString().equals("");
        starbucks.textFont(starbucks.createFont(Constants.ROBOTO_REG_PATH, 18));
        starbucks.fill(0, isEmpty? 120: 255);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.textSize(18);
        starbucks.text(
            isEmpty? "Starbucks Card Number": cardNum.toString(),
            Constants.CARD_NUM_INPUT_X + Constants.CARD_NUM_INPUT_WIDTH/2,
            Constants.CARD_NUM_INPUT_Y + Constants.CARD_NUM_INPUT_TOP_PADDING
        );
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {
        
    }

    @Override
    public void touch(int x, int y) {
        
    }

    @Override
    public void release() {
        
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
