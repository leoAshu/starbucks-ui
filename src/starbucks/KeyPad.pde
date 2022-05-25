

class KeyPadButton {
  private final int WIDTH = 125;
  private final int HEIGHT = 70;
  
  private int x = 125;
  private int y = 312;
  
  void display() {
    setGradient(x, y, WIDTH, HEIGHT, color(102,102,102), color(60,60,60));
    
    fill(255);
    textAlign(CENTER);
    text("2\nABC", x + WIDTH/2, y + (HEIGHT/2));
  }
  
  // linear gradient: vertical
  void setGradient(int x, int y, int w, int h, color c1, color c2) {
    noFill();
    for (int i = y; i <= y+h-30; i++) {
      float inter = map(i, y, y+h-30, 0, 1);
      color c = lerpColor(c1, c2, inter);
      stroke(c);
      line(x, i, x+w, i);
    }
    for (int i = y+h-30; i <= y+h; i++) {
      float inter = map(i, y+h-30, y+h, 0, 1);
      color c = lerpColor(c2, color(4,4,4), inter);
      stroke(c);
      line(x, i, x+w, i);
    }
  }
}
