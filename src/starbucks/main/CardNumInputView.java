import java.util.List;

import processing.core.PApplet;

/** 
 * CardNumInputView Screen SubComponent
 * Displays the entered card number digits
 */
public class CardNumInputView implements IDisplayComponent, ITouchEventHandler, IFocusSubject {
    private PApplet starbucks;
    private boolean isFocused;
    private StringBuffer cardNum;

    private ITouchEventHandler nextHandler;
    private List<IFocusObserver> observers;

    public CardNumInputView(PApplet starbucks) {
        this.starbucks = starbucks;
        isFocused = true;
        cardNum = new StringBuffer("");
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
            Constants.CARD_NUM_INPUT_X-2,
            Constants.CARD_NUM_INPUT_Y-2,
            Constants.CARD_NUM_INPUT_WIDTH+4,
            Constants.CARD_INPUT_HEIGHT+4,
            10
        );

        starbucks.image(
            starbucks.loadImage(Constants.CARD_NUM_INPUT_BG),
            Constants.CARD_NUM_INPUT_X,
            Constants.CARD_NUM_INPUT_Y,
            Constants.CARD_NUM_INPUT_WIDTH,
            Constants.CARD_INPUT_HEIGHT
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
            Constants.CARD_NUM_INPUT_Y + Constants.CARD_INPUT_TOP_PADDING
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

    @Override
    public void attach(IFocusObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IFocusObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(IFocusObserver observer: observers)
            observer.setFocus(false);
    }
    
}
