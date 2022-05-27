import processing.core.PApplet;
import processing.core.PImage;

/** Nav Bar Option */
public class NavBarOption implements INavBarInvoker, IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private String label;
    private String iconPath;
    private String activeIconPath;
    int x;
    int y;
    private int width;
    private boolean isActive;
    
    private INavBarCommand command;
    private ITouchEventHandler nextHandler;
    
    public NavBarOption(PApplet starbucks, String label, String iconPath, String activeIconPath, int x, int y, int width) {
        this.starbucks = starbucks;
        this.label = label;
        this.iconPath = iconPath;
        this.activeIconPath = activeIconPath;
        this.x = x;
        this.y = y;
        this.width = width;
        isActive = x==0;
        // this.navBar = navBar;
    }

    /**
     * Set Command for Nav Bar Option
     * @param command INavBarCommand command to be executed
     */
    @Override
    public void setCommand(INavBarCommand command) {
        this.command = command;
    }

    /** Invoke Nav Bar Option */
    @Override
    public void invoke() {
        command.execute();
    }

    @Override
    public void display() {
        if(isActive){
            setGradient(x, y, width, Constants.NAV_BAR_HEIGHT);
        }
        drawLabels();
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {

    }

    @Override
    public void touch(int x, int y) {
        if(isTouched(x, y)){
            if(!isActive)
                invoke();
            isActive = true;
        } else {
            isActive = false;
        }
        
        if(nextHandler != null)
            nextHandler.touch(x, y);
    }

    @Override
    public void release() {
        
    }

    @Override
    public void setNext(ITouchEventHandler next) {
        nextHandler = next;   
    }

    // linear gradient: vertical
    private void setGradient(int x, int y, int w, int h) {
        starbucks.noFill();
        for (int i = y; i <= y+h-30; i++) {
            float inter = PApplet.map(i, y, y+h-30, 0, 1);
            int c = starbucks.lerpColor(80, 64, inter);
            starbucks.stroke(c);
            starbucks.line(x, i, x+w, i);
        }
        for (int i = y+h-30; i <= y+h; i++) {
            float inter = PApplet.map(i, y+h-30, y+h, 0, 1);
            int c = starbucks.lerpColor(64, 47, inter);
            starbucks.stroke(c);
            starbucks.line(x, i, x+w, i);
        }
    }

    private void drawLabels() {
        // icon
        PImage icon = starbucks.loadImage(isActive? activeIconPath: iconPath);
        
        starbucks.tint(255, isActive? 255: 150);
        starbucks.image(
            icon,
            x + (width - icon.width)/2,
            546
        );

        // label
        starbucks.textFont(starbucks.createFont(Constants.ROBOTO_MED_PATH, 10));

        starbucks.fill(255, isActive? 255: 150);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.textSize(10);
        starbucks.text(
            label,
            x + width/2,
            585
        );
    }

    private boolean isTouched(int x, int y) {
        boolean overX = x > this.x && x < (this.x + width);
        boolean overY = y > this.y && y < (this.y + Constants.NAV_BAR_HEIGHT);

        return overX && overY;
    }
    
}