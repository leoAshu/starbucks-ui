import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

/** Key Pad */
public class KeyPad implements IDisplayComponent {
    private PApplet starbucks;
    private List<IDisplayComponent> buttons;

    public KeyPad(PApplet starbucks) {
        this.starbucks = starbucks;
        buttons = new ArrayList<IDisplayComponent>();

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
    }
    
    private void drawBorders() {
        // top border
        starbucks.stroke(0);
        starbucks.strokeWeight(1);
        starbucks.line(0, 312, starbucks.width, 312);

        // vertical borders
        starbucks.stroke(102);
        starbucks.strokeWeight(4);
        starbucks.line(125, 314, 125, starbucks.height);
        starbucks.line(250, 314, 250, starbucks.height);
    }
}
