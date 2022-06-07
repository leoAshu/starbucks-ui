import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

/** 
 * CardNumInputView Screen SubComponent
 * Displays the entered card number digits
 */
public class CardNumInputView implements IDisplayComponent, ITouchEventHandler, IFocusSubject, IFocusObserver {
    private PApplet starbucks;
    private boolean isFocused;
    private StringBuffer cardNum;

    private ITouchEventHandler nextHandler;
    private List<IFocusObserver> observers;

    public CardNumInputView(PApplet starbucks) {
        this.starbucks = starbucks;
        isFocused = true;
        cardNum = new StringBuffer("");

        observers = new ArrayList<IFocusObserver>();
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
        if(isTouched(x, y)) {
            notifyObservers();
            setFocus(true);
            return;
        }
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

    @Override
    public void setFocus(boolean isFocused) {
        this.isFocused = isFocused;
    }

    private boolean isTouched(int x, int y) {
        boolean overX, overY;
        overX = x > Constants.CARD_NUM_INPUT_X && x < Constants.CARD_NUM_INPUT_X + Constants.CARD_NUM_INPUT_WIDTH;
        overY = y > Constants.CARD_NUM_INPUT_Y && y < Constants.CARD_NUM_INPUT_Y + Constants.CARD_INPUT_HEIGHT;

        return overX && overY;
    }
    
}
