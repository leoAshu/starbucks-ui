import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

/** 
 * CardCodeInputView Screen SubComponent
 * Displays the entered card code digits
 */
public class CardCodeInputView implements IDisplayComponent, ITouchEventHandler, IFocusSubject, IFocusObserver, IKeyPadObserver {
    private PApplet starbucks;
    private boolean isFocused;
    private int count;
    private StringBuffer cardCode;

    private ITouchEventHandler nextHandler;
    private List<IFocusObserver> observers;

    public CardCodeInputView(PApplet starbucks) {
        this.starbucks = starbucks;
        isFocused = false;
        count = 0;
        cardCode = new StringBuffer("");

        observers = new ArrayList<IFocusObserver>();
    }

    @Override
    public void display() {
        // text box
        starbucks.image(
            starbucks.loadImage(Constants.CARD_CODE_INPUT_BG),
            Constants.CARD_CODE_INPUT_X,
            Constants.CARD_CODE_INPUT_Y,
            Constants.CARD_CODE_INPUT_WIDTH,
            Constants.CARD_INPUT_HEIGHT
        );
        // border
        starbucks.fill(255, 0);
        if(isFocused) {
            starbucks.strokeWeight(3);
            starbucks.stroke(43, 143, 104);
        } else {
            starbucks.strokeWeight(1);
            starbucks.stroke(112);
        }
        starbucks.rect(
            Constants.CARD_CODE_INPUT_X-2,
            Constants.CARD_CODE_INPUT_Y-2,
            Constants.CARD_CODE_INPUT_WIDTH+3,
            Constants.CARD_INPUT_HEIGHT+3,
            15
        );

        // card number
        boolean isEmpty = cardCode.toString().equals("");
        starbucks.textFont(starbucks.createFont(Constants.ROBOTO_REG_PATH, 18));
        starbucks.fill(0, isEmpty? 120: 255);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.textSize(isEmpty? 18: 22);
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
 
    @Override
    public void keyEventUpdate(int keyCount, String key) {
        if(isFocused) {
            if(key.equals("X")) {
                if(count > 0) {
                    count -= 1;
                    cardCode.deleteCharAt(count);
                }
            } else {
                if(count < Constants.CARD_CODE_MAX_LENGTH) {
                    count += 1;
                    cardCode.append(key);
                }
            }
        }
    }

    private boolean isTouched(int x, int y) {
        boolean overX, overY;
        overX = x > Constants.CARD_CODE_INPUT_X && x < Constants.CARD_CODE_INPUT_X + Constants.CARD_CODE_INPUT_WIDTH;
        overY = y > Constants.CARD_CODE_INPUT_Y && y < Constants.CARD_CODE_INPUT_Y + Constants.CARD_INPUT_HEIGHT;

        return overX && overY;
    }
    
}
