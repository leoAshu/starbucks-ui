

class PinScreen {
  private PImage bg;
  
  PinScreen() {
    bg = loadImage("../../assets/images/pinscreen-bg.png");
  }
  
  void display() {
    image(bg, 0, 31);
  }
}
