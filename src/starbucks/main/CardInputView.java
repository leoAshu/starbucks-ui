import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

public class CardInputView implements IDisplayComponent, ITouchEventHandler, IKeyPadObserver {
    private PApplet starbucks;
    private PImage background;
    private Card card;
    private CardNumInputView numView;
    private CardCodeInputView codeView;

    private ITouchEventHandler chain;
    private ITouchEventHandler nextHandler;
    private List<IDisplayComponent> components;

    public CardInputView(PApplet starbucks) {
        this.starbucks = starbucks;
        numView = new CardNumInputView(starbucks);
        codeView = new CardCodeInputView(starbucks);
        numView.attach((IFocusObserver)codeView);
        codeView.attach((IFocusObserver)numView);
        background = starbucks.loadImage(Constants.CARD_BACK_INPUT);

        components = new ArrayList<IDisplayComponent>();
        
        addSubComponent(numView);
        addSubComponent(codeView);
    }

    @Override
    public void display() {
        // card
        starbucks.image(
            background,
            (starbucks.width - Constants.CARD_WIDTH)/2,
            Constants.NOTIF_BAR_HEIGHT + Constants.APP_BAR_HEIGHT + Constants.CARD_TOP_PADDING - 20,
            Constants.CARD_WIDTH, 
            Constants.CARD_HEIGHT
        );

        for(IDisplayComponent component: components)
            component.display();
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {
        components.add(component);
        if(components.size() == 1)
            chain = (ITouchEventHandler) component;
        else {
            ITouchEventHandler prev = (ITouchEventHandler) components.get(components.size()-2);
            prev.setNext((ITouchEventHandler) component);
        }
    }

    @Override
    public void touch(int x, int y) {
        if(chain != null)
            chain.touch(x, y);
        if(nextHandler != null)
            nextHandler.touch(x, y);
    }

    @Override
    public void release() {
        if(chain != null)
            chain.release();
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
    public void keyEventUpdate(int keyCount, String key) {
        numView.keyEventUpdate(keyCount, key);
        codeView.keyEventUpdate(keyCount, key);
    }
    
    public void reset() {
        numView.reset();
        codeView.reset();
    }

    public boolean validate() {
        String number = numView.getCardNumber();
        String code = codeView.getCardCode();
        if(validateCardNumber(number) && validateCardCode(code)) {
            card = Card.getCard();
            card.setCard(number, code);
            return true;
        }
        return false;
    }

    private boolean validateCardNumber(String number) {
        if(number == "000000000" || number.length() < Constants.CARD_NUM_MAX_LENGTH) {
            numView.reset();
            return false;
        }
        return true;
    }

    private boolean validateCardCode(String code) {
        if(code.length() < Constants.CARD_CODE_MAX_LENGTH) {
            codeView.reset();
            return false;
        }
        return true;
    }
}
