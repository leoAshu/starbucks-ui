import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class KeyPadButton implements IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private PFont roboto;
    private KeyPad keyPad;
    private int x;
    private int y;
    private String labelValue;
    private String labelAlphabet;
    private PImage icon;
    private boolean isClicked;
    private boolean isDisabled;

    private ITouchEventHandler nextButton;

    KeyPadButton(PApplet starbucks, KeyPad keyPad, int x, int y) {
        this.starbucks = starbucks;
        this.keyPad = keyPad;
        this.x = x;
        this.y = y;
        this.labelValue = "";
        this.labelAlphabet = "";
        this.icon = null;
        this.isDisabled = true;
      }
      
      KeyPadButton(PApplet starbucks, KeyPad keyPad, int x, int y, String labelValue) {
        this.starbucks = starbucks;
        this.keyPad = keyPad;
        this.x = x;
        this.y = y;
        this.labelValue = labelValue;
        this.labelAlphabet = "";
        this.icon = null;
        this.isDisabled = false;
      }
      
      KeyPadButton(PApplet starbucks, KeyPad keyPad, int x, int y, String labelValue, String labelAlphabet) {
        this.starbucks = starbucks;
        this.keyPad = keyPad;
        this.x = x;
        this.y = y;
        this.labelValue = labelValue;
        this.labelAlphabet = labelAlphabet;
        this.icon = null;
        this.isDisabled = false;
      }
      
      KeyPadButton(PApplet starbucks, KeyPad keyPad, int x, int y, PImage icon) {
        this.starbucks = starbucks;
        this.keyPad = keyPad;
        this.x = x;
        this.y = y;
        this.labelValue = "";
        this.labelAlphabet = "";
        this.icon = icon;
        this.isDisabled = false;
      }

    @Override
    public void display() {
        if(roboto == null)
            roboto = starbucks.createFont(Constants.ROBOTO_MED_PATH, 26);

        drawButtonBackground();
        drawButtonLabels();
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {

    }

    @Override
    public void touch(int x, int y) {
        boolean overX = x > this.x && x < (this.x + Constants.CELL_WIDTH);
        boolean overY = y > this.y && y < (this.y + Constants.CELL_HEIGHT);
        if(!isDisabled && overX && overY) {
            isClicked = true;
            return;
        }
        if(nextButton != null)
            nextButton.touch(x, y);
    }

    @Override
    public void release() {
        if(isClicked) {
            isClicked = false;
            callBack();
            return;
        }
        if(nextButton != null)
            nextButton.release();
    }

    @Override
    public void setNext(ITouchEventHandler next) {
        nextButton = next;
    }

    @Override
    public ITouchEventHandler getNext() {
        return nextButton;
    }

    private void drawButtonBackground() {
        if(isClicked)
            Utility.solidFill(
                starbucks,
                x+1,
                y+1,
                Constants.CELL_WIDTH-2,
                Constants.CELL_HEIGHT-2,
                102
            );
        else
            Utility.setVerticalGradient(
                starbucks,
                x+1,
                y+1,
                Constants.CELL_WIDTH-2,
                Constants.CELL_HEIGHT-2,
                y + Constants.CELL_HEIGHT - 30,
                102,
                60,
                4
            );
    }

    private void drawButtonLabels() {
        starbucks.textFont(roboto);

        // value label
        starbucks.fill(255);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.textSize(26);
        starbucks.text(
            labelValue,
            x + Constants.CELL_WIDTH/2,
            labelAlphabet.isEmpty()? y + (Constants.CELL_HEIGHT/2) + 6: y + (Constants.CELL_HEIGHT/2) + 2
        );
    
        // alphabet label
        starbucks.fill(255, 204);
        starbucks.textSize(14);
        starbucks.text(
            labelAlphabet,
            x + Constants.CELL_WIDTH/2,
            y + (Constants.CELL_HEIGHT/2) + 20
        );
        
        // icon
        // no labels
        if(labelValue.isEmpty() && labelAlphabet.isEmpty() && icon != null) {
            starbucks.image(
                icon,
                x + (Constants.CELL_WIDTH - icon.width)/2,
                y + (Constants.CELL_HEIGHT - icon.height)/2
            );
        }
    }

    private void callBack() {
        if(labelValue.isEmpty()) {
            if(icon != null)
                keyPad.callBack("X");
            else
                keyPad.callBack(null);
        } else {
            keyPad.callBack(labelValue);
        }
    }

}
