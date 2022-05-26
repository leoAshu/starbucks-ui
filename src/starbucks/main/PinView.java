import processing.core.PApplet;

public class PinView implements IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private int pinOption;
    private int pinDigitsCount;
    private int pinViewWidth;

    private ITouchEventHandler nextHandler;

    public PinView(PApplet starbucks, int pinOption) {
        this.starbucks = starbucks;
        this.pinOption = pinOption;
        pinDigitsCount = 0;
        pinViewWidth = (pinOption * Constants.PIN_BLOCK_SIZE) + (pinOption-1) * Constants.PIN_BLOCK_PADDING;
    }

    @Override
    public void display() {
        int x = (starbucks.width - pinViewWidth)/2;
        for(int i=0; i<pinOption; i++) {
            if(i < pinDigitsCount)
                pinBlock(x);
            else
                emptyBlock(x);
            x += Constants.PIN_BLOCK_SIZE + Constants.PIN_BLOCK_PADDING;
        }
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {

    }

    @Override
    public void touch(int x, int y) {
        if(nextHandler != null)
            nextHandler.touch(x, y);
    }

    @Override
    public void release() {
        if(nextHandler != null)
            nextHandler.release();
    }

    @Override
    public void setNext(ITouchEventHandler next) {
        nextHandler = next;
    }

    void emptyBlock(int x) {
        starbucks.strokeWeight(2);
        starbucks.stroke(194);
        starbucks.fill(255);
        starbucks.rect(
            x,
            Constants.PIN_BLOCK_Y,
            Constants.PIN_BLOCK_SIZE,
            Constants.PIN_BLOCK_SIZE
        );
    }
      
      void pinBlock(int x) {
        starbucks.strokeWeight(2);
        starbucks.stroke(194);
        starbucks.fill(255);
        starbucks.rect(
            x,
            Constants.PIN_BLOCK_Y,
            Constants.PIN_BLOCK_SIZE,
            Constants.PIN_BLOCK_SIZE
        );
        
        starbucks.fill(0);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.textSize(Constants.PIN_FONT_SIZE);
        starbucks.text(
            '*',
            x + Constants.PIN_BLOCK_SIZE/2,
            Constants.PIN_BLOCK_Y + (Constants.PIN_BLOCK_SIZE + Constants.PIN_FONT_SIZE)/2
        );
    }
    
}
