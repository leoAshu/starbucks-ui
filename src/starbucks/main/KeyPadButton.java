import processing.core.PApplet;
import processing.core.PImage;

public class KeyPadButton implements IDisplayComponent {
    private PApplet starbucks;
    private KeyPad keyPad;
    private int x;
    private int y;
    private String labelValue;
    private String labelAlphabet;
    private PImage icon;
    private boolean isClicked;
    private boolean isDisabled;

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
        drawButtonBackground();
        drawButtonLabels();
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {

    }

    private void drawButtonBackground() {
        if(isClicked)
            solidFill(x+1, y+1, Constants.CELL_WIDTH-2, Constants.CELL_HEIGHT-2);
        else
            setGradient(x+1, y+1, Constants.CELL_WIDTH-2, Constants.CELL_HEIGHT-2);
    }

    private void drawButtonLabels() {
        starbucks.textFont(starbucks.createFont(Constants.ROBOTO_MED_PATH, 64));

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

    // solid color
    private void solidFill(int x, int y, int w, int h) {
        starbucks.noFill();
        for (int i = y; i <= y+h; i++) {
            float inter = PApplet.map(i, y, y+h, 0, 1);
            int c = starbucks.lerpColor(102, 102, inter);
            starbucks.stroke(c);
            starbucks.line(x, i, x+w, i);
        }
    }
  
    // linear gradient: vertical
    private void setGradient(int x, int y, int w, int h) {
        starbucks.noFill();
        for (int i = y; i <= y+h-30; i++) {
            float inter = PApplet.map(i, y, y+h-30, 0, 1);
            int c = starbucks.lerpColor(102, 60, inter);
            starbucks.stroke(c);
            starbucks.line(x, i, x+w, i);
        }
        for (int i = y+h-30; i <= y+h; i++) {
            float inter = PApplet.map(i, y+h-30, y+h, 0, 1);
            int c = starbucks.lerpColor(60, 4, inter);
            starbucks.stroke(c);
            starbucks.line(x, i, x+w, i);
        }
  }
}
