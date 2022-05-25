

class PinScreen {
  private PImage bg;
  private KeyPad keyPad;
  
  PinScreen() {
    bg = loadImage("../../assets/images/pinscreen-bg.png");
    keyPad = new KeyPad();
  }
  
  void display() {
    image(bg, 0, 31);
    keyPad.display();
  }
}
