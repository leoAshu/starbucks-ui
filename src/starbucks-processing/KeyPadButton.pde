class KeyPadButton implements IDisplayComponent, ITouchEventHandler {
  private final int WIDTH = 125;
  private final int HEIGHT = 70;
  private final int COLOR_1 = color(102, 102, 102);
  private final int COLOR_2 = color(60, 60, 60);
  private final int COLOR_3 = color(4, 4, 4);
  private final PFont ROBOTO_MED = createFont("../../assets/fonts/Roboto-Medium.ttf", 64); 
  
  private int x;
  private int y;
  private String label1;
  private String label2;
  private PImage icon;
  private boolean isClicked;
  private boolean isDisabled;
  
  private KeyPad keyPad;
  private ITouchEventHandler nextButton;
  
  KeyPadButton(KeyPad keyPad, int x, int y) {
    this.keyPad = keyPad;
    this.x = x;
    this.y = y;
    this.label1 = "";
    this.label2 = "";
    this.icon = null;
    this.isDisabled = true;
  }
  
  KeyPadButton(KeyPad keyPad, int x, int y, String label1) {
    this.keyPad = keyPad;
    this.x = x;
    this.y = y;
    this.label1 = label1;
    this.label2 = "";
    this.icon = null;
    this.isDisabled = false;
  }
  
  KeyPadButton(KeyPad keyPad, int x, int y, String label1, String label2) {
    this.keyPad = keyPad;
    this.x = x;
    this.y = y;
    this.label1 = label1;
    this.label2 = label2;
    this.icon = null;
    this.isDisabled = false;
  }
  
  KeyPadButton(KeyPad keyPad, int x, int y, PImage icon) {
    this.keyPad = keyPad;
    this.x = x;
    this.y = y;
    this.label1 = "";
    this.label2 = "";
    this.icon = icon;
    this.isDisabled = false;
  }
  
  @Override
  void display() {    
    if(isClicked)
      solidFill(x+1, y+1, WIDTH-2, HEIGHT-2);
    else
      setGradient(x+1, y+1, WIDTH-2, HEIGHT-2);
    
    textFont(ROBOTO_MED);
    
    fill(255);
    textAlign(CENTER);
    textSize(26);
    text(label1, x + WIDTH/2, label2.isEmpty()? y + (HEIGHT/2) + 6: y + (HEIGHT/2) + 2);
    
    fill(255, 0.8*255);
    textSize(14);
    text(label2, x + WIDTH/2, y + (HEIGHT/2) + 20);
    
    if(label1.isEmpty() && label2.isEmpty() && icon!=null) {
      image(icon, x + (WIDTH - icon.width)/2, y + (HEIGHT - icon.height)/2);
    }
  }
  
  @Override
  void touch(int x, int y) {
    if(!isDisabled && x > this.x && x < (this.x + WIDTH) && y > this.y && y < (this.y + HEIGHT)) {
      isClicked = true;
      callBack();
    }
    if(nextButton != null)
      nextButton.touch(x, y);
  }
  
  @Override
  void reset() {
    isClicked = false;
    if(nextButton != null)
      nextButton.reset();
  }
  
  @Override
  void addSubComponent(IDisplayComponent component) {
  }
  
  @Override
  void setNext(ITouchEventHandler next) {
    nextButton = next;
  }
  
  private void callBack() {
    if(!label1.isEmpty())
      keyPad.callBack(label1);
    else{
      if(icon == null)
        keyPad.callBack(null);
       else
         keyPad.callBack("X");
    }
  }
  
  // linear gradient: vertical
  void solidFill(int x, int y, int w, int h) {
    noFill();
    for (int i = y; i <= y+h; i++) {
      float inter = map(i, y, y+h, 0, 1);
      color c = lerpColor(COLOR_1, COLOR_1, inter);
      stroke(c);
      line(x, i, x+w, i);
    }
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
