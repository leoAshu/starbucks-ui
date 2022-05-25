import java.util.List;
import java.util.ArrayList;

class KeyPad implements IDisplayComponent, ITouchEventHandler {
  private List<IDisplayComponent> components;
  private ITouchEventHandler chain;
  private String pin = "";
  
  KeyPad() {
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
    
    text(pin, 125, 140);
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
  
  void callBack(String label1) {
    StringBuffer buffer = new StringBuffer(pin);
    if(!label1.isEmpty() && !label1.equalsIgnoreCase("X")) {
      buffer.append(label1);
    } else {
      if(label1.equalsIgnoreCase("X") && buffer.length()>0)
        buffer.deleteCharAt(buffer.length()-1);
    }
    pin = buffer.toString();
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
}
