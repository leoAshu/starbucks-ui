import java.util.List;
import java.util.ArrayList;

class KeyPad {
  List<KeyPadButton> buttons;
  
  KeyPad() {
    buttons = new ArrayList<KeyPadButton>();
    
    buttons.add(new KeyPadButton(0, 312, "1", ""));
    buttons.add(new KeyPadButton(125, 312, "2", "A B C"));
    buttons.add(new KeyPadButton(250, 312, "3", "D E F"));
    
    buttons.add(new KeyPadButton(0, 382, "4", "G H I"));
    buttons.add(new KeyPadButton(125, 382, "5", "J K L"));
    buttons.add(new KeyPadButton(250, 382, "6", "M N O"));
    
    buttons.add(new KeyPadButton(0, 452, "7", "P Q R S"));
    buttons.add(new KeyPadButton(125, 452, "8", "T U V"));
    buttons.add(new KeyPadButton(250, 452, "9", "W X Y Z"));
    
    buttons.add(new KeyPadButton(0, 522, "", ""));
    buttons.add(new KeyPadButton(125, 522, "0", ""));
    buttons.add(new KeyPadButton(250, 522, "X", ""));
  }
  
  void display() {
    for(KeyPadButton button: buttons)
      button.display();
  }
}

class KeyPadButton {
  private final int WIDTH = 125;
  private final int HEIGHT = 70;
  private final int COLOR_1 = color(102, 102, 102);
  private final int COLOR_2 = color(60, 60, 60);
  private final int COLOR_3 = color(4, 4, 4);
  
  private int x;
  private int y;
  private String label1;
  private String label2;
  
  KeyPadButton(int x, int y, String label1, String label2) {
    this.x = x;
    this.y = y;
    this.label1 = label1;
    this.label2 = label2;
  }
  
  void display() {
    strokeWeight(2);
    stroke(color(102, 102, 102)); //102
    rect(x, y, WIDTH, HEIGHT);
    setGradient(x+1, y+1, WIDTH-2, HEIGHT-2);
    
    fill(255);
    textAlign(CENTER);
    textSize(26);
    text(label1, x + WIDTH/2, y + (HEIGHT/2) + 5);
    
    fill(255, 0.8*255);
    textSize(12);
    text(label2, x + WIDTH/2, y + (HEIGHT/2) + 22);
  }
  
  // linear gradient: vertical
  void setGradient(int x, int y, int w, int h) {
    noFill();
    for (int i = y; i <= y+h-30; i++) {
      float inter = map(i, y, y+h-30, 0, 1);
      color c = lerpColor(COLOR_1, COLOR_2, inter);
      stroke(c);
      line(x, i, x+w, i);
    }
    for (int i = y+h-30; i <= y+h; i++) {
      float inter = map(i, y+h-30, y+h, 0, 1);
      color c = lerpColor(COLOR_2, COLOR_3, inter);
      stroke(c);
      line(x, i, x+w, i);
    }
  }
}
