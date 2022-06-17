import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

/** Key Pad */
public class KeyPad implements IDisplayComponent, ITouchEventHandler, IKeyPadSubject {
    private PApplet starbucks;
    private ITouchEventHandler chain;
    private List<IDisplayComponent> buttons;
    private List<IKeyPadObserver> observers;

    private int keyCount;
    private String lastKey;

    public KeyPad(PApplet starbucks) {
        keyCount = 0;
        lastKey = " ";

        this.starbucks = starbucks;
        buttons = new ArrayList<IDisplayComponent>();
        observers = new ArrayList<IKeyPadObserver>();

        addSubComponent(new KeyPadButton(starbucks, this, 0, 312, "1"));
        addSubComponent(new KeyPadButton(starbucks, this, 125, 312, "2", "A B C"));
        addSubComponent(new KeyPadButton(starbucks, this, 250, 312, "3", "D E F"));
    
        addSubComponent(new KeyPadButton(starbucks, this, 0, 382, "4", "G H I"));
        addSubComponent(new KeyPadButton(starbucks, this, 125, 382, "5", "J K L"));
        addSubComponent(new KeyPadButton(starbucks, this, 250, 382, "6", "M N O"));
    
        addSubComponent(new KeyPadButton(starbucks, this, 0, 452, "7", "P Q R S"));
        addSubComponent(new KeyPadButton(starbucks, this, 125, 452, "8", "T U V"));
        addSubComponent(new KeyPadButton(starbucks, this, 250, 452, "9", "W X Y Z"));
    
        addSubComponent(new KeyPadButton(starbucks, this, 0, 522));
        addSubComponent(new KeyPadButton(starbucks, this, 125, 522, "0"));
        addSubComponent(new KeyPadButton(starbucks, this, 250, 522, starbucks.loadImage("../../assets/images/backspace.png")));
    }

    @Override
    public void display() {
        for(IDisplayComponent component: buttons)
            component.display();

        drawBorders();
    }

    @Override
    public void addSubComponent(IDisplayComponent button) {
        buttons.add(button);
        if(buttons.size() == 1) {
            chain = (ITouchEventHandler) button;
        } else {
            ITouchEventHandler prev = (ITouchEventHandler) buttons.get(buttons.size()-2);
            prev.setNext((ITouchEventHandler) button);
        }
    }

    @Override
    public void touch(int x, int y) {
        if(chain != null)
            chain.touch(x, y);
    }

    @Override
    public void release() {
        if(chain != null)
            chain.release();
    }

    @Override
    public void setNext(ITouchEventHandler next) {   
    }

    @Override
    public ITouchEventHandler getNext() {
        return null;
    }

    @Override
    public void attach(IKeyPadObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IKeyPadObserver observer) {
        int i = observers.indexOf(observer);
        if(i>=0)
            observers.remove(i);
    }

    @Override
    public void notifyObservers() {
        for(IKeyPadObserver observer: observers)
            observer.keyEventUpdate(keyCount, lastKey);
    }
    
    private void drawBorders() {
        // top border
        starbucks.stroke(0);
        starbucks.strokeWeight(1);
        starbucks.line(0, 312, starbucks.width, 312);

        // vertical borders
        starbucks.stroke(starbucks.color(68, 75, 87));
        starbucks.strokeWeight(4);
        starbucks.line(125, 314, 125, starbucks.height);
        starbucks.line(250, 314, 250, starbucks.height);
    }

    public void callBack(String key) {
        lastKey = key;
        if(key.equalsIgnoreCase("X")) {
            if(keyCount > 0)
                keyCount -= 1;
        } else 
            keyCount += 1;

        notifyObservers();
    }

}
