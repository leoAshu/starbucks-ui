import processing.core.PApplet;

/** 
 * CardCodeInputView Screen SubComponent
 * Displays the entered card code digits
 */
public class CardCodeInputView implements IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private boolean isFocused;
    private StringBuffer cardCode;

    private ITouchEventHandler nextHandler;

    public CardCodeInputView(PApplet starbucks) {
        this.starbucks = starbucks;
        isFocused = false;
        cardCode = new StringBuffer("");
    }

    @Override
    public void display() {
        // text box
        starbucks.strokeWeight(2);
        if(isFocused) {
            starbucks.stroke(43, 143, 104);
        } else {
            starbucks.stroke(112);
        }
        starbucks.rect(
            Constants.CARD_CODE_INPUT_X-2,
            Constants.CARD_CODE_INPUT_Y-2,
            Constants.CARD_CODE_INPUT_WIDTH+3,
            Constants.CARD_INPUT_HEIGHT+3,
            14
        );

        starbucks.image(
            starbucks.loadImage(Constants.CARD_CODE_INPUT_BG),
            Constants.CARD_CODE_INPUT_X,
            Constants.CARD_CODE_INPUT_Y,
            Constants.CARD_CODE_INPUT_WIDTH,
            Constants.CARD_INPUT_HEIGHT
        );

        // card number
        boolean isEmpty = cardCode.toString().equals("");
        starbucks.textFont(starbucks.createFont(Constants.ROBOTO_REG_PATH, 18));
        starbucks.fill(0, isEmpty? 120: 255);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.textSize(18);
        starbucks.text(
            isEmpty? "Code": cardCode.toString(),
            Constants.CARD_CODE_INPUT_X + Constants.CARD_CODE_INPUT_WIDTH/2,
            Constants.CARD_CODE_INPUT_Y + Constants.CARD_INPUT_TOP_PADDING
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
