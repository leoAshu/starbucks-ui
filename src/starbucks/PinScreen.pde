

class PinScreen {
  private PImage bg;
  private KeyPadButton button;
  
  PinScreen() {
    bg = loadImage("../../assets/images/pinscreen-bg.png");
    button = new KeyPadButton();
  }
  
  void display() {
    image(bg, 0, 31);
    button.display();
  }
}
