class PinView implements IDisplayComponent, ITouchEventHandler {
  private final int BLOCK_SIZE = 45;
  private final int PADDING = 16;
  private final int FONT_SIZE = 32;
  private final int Y = 130;
  
  private int pinOption;
  private int count;
  private int pinViewWidth;
  
  private ITouchEventHandler nextHandler;
  
  public PinView(int pinOption) {
    this.pinOption = pinOption;
    this.count = 0;
    pinViewWidth = (pinOption * BLOCK_SIZE) + (pinOption-1) * PADDING;
  }
  
  @Override
  void display() {
    int x = (width - pinViewWidth)/2;
    for(int i = 0; i < pinOption; i++) {
      if(i < count)
        pinBlock(x);
      else
        emptyBlock(x);
      x += BLOCK_SIZE + PADDING;
      
    }
  }
  
  @Override
  void addSubComponent(IDisplayComponent component) {
  }
  
  @Override
  void touch(int x, int y) {
    if(nextHandler != null)
      nextHandler.touch(x, y);
  }
  
  @Override
  void reset() {
  }
  
  @Override
  void setNext(ITouchEventHandler next) {
    nextHandler = next;
  }
  
  void emptyBlock(int x) {
    strokeWeight(2);
    stroke(194);
    fill(255);
    rect(x, Y, BLOCK_SIZE, BLOCK_SIZE);
  }
  
  void pinBlock(int x) {
    strokeWeight(2);
    stroke(194);
    fill(255);
    rect(x, Y, BLOCK_SIZE, BLOCK_SIZE);
    
    fill(0);
    textAlign(CENTER);
    textSize(FONT_SIZE);
    text('*', x + BLOCK_SIZE/2, Y + (BLOCK_SIZE + FONT_SIZE)/2);
  }
}
