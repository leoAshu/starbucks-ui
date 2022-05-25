import java.util.List;
import java.util.ArrayList;

class KeyPad {
  List<KeyPadButton> buttons;
  
  KeyPad() {
    buttons = new ArrayList<KeyPadButton>();
    
    buttons.add(new KeyPadButton(0, 312, "1\n "));
    buttons.add(new KeyPadButton(125, 312, "2\nABC"));
    buttons.add(new KeyPadButton(250, 312, "3\nDEF"));
    
    buttons.add(new KeyPadButton(0, 382, "4\n "));
    buttons.add(new KeyPadButton(125, 382, "5\nGHI"));
    buttons.add(new KeyPadButton(250, 382, "6\nJKL"));
    
    buttons.add(new KeyPadButton(0, 452, "7\nMNO"));
    buttons.add(new KeyPadButton(125, 452, "8\nPQRS"));
    buttons.add(new KeyPadButton(250, 452, "9\nTUV"));
    
    buttons.add(new KeyPadButton(0, 522, ""));
    buttons.add(new KeyPadButton(125, 522, "0"));
    buttons.add(new KeyPadButton(250, 522, "X"));
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
  private String label;
  
  KeyPadButton(int x, int y, String label) {
    this.x = x;
    this.y = y;
    this.label = label;
  }
  
  void display() {
    strokeWeight(2);
    stroke(color(102, 102, 102)); //102
    rect(x, y, WIDTH, HEIGHT);
    setGradient(x+1, y+1, WIDTH-2, HEIGHT-2);
    
    fill(255);
    textAlign(CENTER);
    text(label, x + WIDTH/2, y + (HEIGHT/2));
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
