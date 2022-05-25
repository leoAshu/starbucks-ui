import java.util.List;
import java.util.ArrayList;

class KeyPad implements IDisplayComponent, ITouchEventHandler, IKeyPadSubject {
  private int pinDigitsCount;
  private String lastKey;
  
  private ITouchEventHandler chain;
  private List<IKeyPadObserver> observers;
  private List<IDisplayComponent> components;
  
  KeyPad() {
    pinDigitsCount = 0;
    lastKey = "";
    
    observers = new ArrayList<IKeyPadObserver>();
    components = new ArrayList<IDisplayComponent>();
    
    addSubComponent(new KeyPadButton(this, 0, 312, "1"));
    addSubComponent(new KeyPadButton(this, 125, 312, "2", "A B C"));
    addSubComponent(new KeyPadButton(this, 250, 312, "3", "D E F"));
    
    addSubComponent(new KeyPadButton(this, 0, 382, "4", "G H I"));
    addSubComponent(new KeyPadButton(this, 125, 382, "5", "J K L"));
    addSubComponent(new KeyPadButton(this, 250, 382, "6", "M N O"));
    
    addSubComponent(new KeyPadButton(this, 0, 452, "7", "P Q R S"));
    addSubComponent(new KeyPadButton(this, 125, 452, "8", "T U V"));
    addSubComponent(new KeyPadButton(this, 250, 452, "9", "W X Y Z"));
    
    addSubComponent(new KeyPadButton(this, 0, 522));
    addSubComponent(new KeyPadButton(this, 125, 522, "0"));
    addSubComponent(new KeyPadButton(this, 250, 522, loadImage("../../assets/images/backspace.png")));
  }
  
  @Override
  void display() {
    for(IDisplayComponent component: components)
      component.display();
    
    // vertical borders
    stroke(102);
    strokeWeight(4);
    line(125, 314, 125, height);
    line(250, 314, 250, height);
    
    // top border
    stroke(0);
    strokeWeight(1);
    line(0, 312, width, 312);
  }
  
  @Override
  void addSubComponent(IDisplayComponent component) {
    components.add(component);
    if(components.size() == 1) {
      chain = (ITouchEventHandler) component;
    } else {
      ITouchEventHandler prev = (ITouchEventHandler) components.get(components.size()-2);
      prev.setNext((ITouchEventHandler) component);
    }
  }
  
  @Override
  void touch(int x, int y) {
    if(chain != null)
      chain.touch(x, y);
  }
  
  @Override
  void reset() {
    if(chain != null)
      chain.reset();
  }
  
  @Override
  void setNext(ITouchEventHandler next) {
  }
  
  @Override
  void attach(IKeyPadObserver observer) {
    observers.add(observer);
  }
  
  @Override
  void removeObserver(IKeyPadObserver observer) {
    int i = observers.indexOf(observer);
    if(i>=0)
      observers.remove(i);
  }
  
  @Override
  void notifyObservers() {
    for(IKeyPadObserver observer: observers)
      observer.keyEventUpdate(pinDigitsCount, lastKey);
  }
  
  void callBack(String value) {
    lastKey = value;
    if(value.equalsIgnoreCase("X")) {
      if(pinDigitsCount > 0)
        pinDigitsCount--;
    } else
      pinDigitsCount++;

    notifyObservers();
  }
}
