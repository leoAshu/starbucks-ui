import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/** Nav Bar Option */
public class NavBarOption implements INavBarInvoker, IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private PFont roboto;
    private PImage icon, activeIcon;
    private int x;
    private int y;
    private int width;
    private boolean isActive;
    private boolean isTouched;
    
    private INavBarCommand command;
    private ITouchEventHandler nextHandler;
    
    public NavBarOption(PApplet starbucks, int x, int y, int width) {
        this.starbucks = starbucks;
        this.x = x;
        this.y = y;
        this.width = width;
        isActive = x==0;
        isTouched = false;
    }

    @Override
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Set Command for Nav Bar Option
     * @param command INavBarCommand command to be executed
     */
    @Override
    public void setCommand(INavBarCommand command) {
        this.command = command;
        icon = starbucks.loadImage(command.getIconPath());
        activeIcon = starbucks.loadImage(command.getActiveIconPath());
    }

    /** Invoke Nav Bar Option */
    @Override
    public void invoke() {
        command.execute();
    }

    @Override
    public void display() {
        if(roboto == null)
            roboto = starbucks.createFont(Constants.ROBOTO_MED_PATH, 10);

        if(isActive)
            Utility.setVerticalGradient(
                starbucks,
                x,
                y,
                width,
                Constants.NAV_BAR_HEIGHT,
                y + Constants.CELL_HEIGHT - 30,
                80,
                64,
                47
            );
        
        drawLabels();
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {

    }

    @Override
    public void touch(int x, int y) {
        if(isTouched(x, y))
            isTouched = true;
        else
            isTouched = false;
        
        if(nextHandler != null)
            nextHandler.touch(x, y);
    }

    @Override
    public void release() {
        if(isTouched) {
            if(!isActive)
                invoke();
            isActive = true;
        } else {
            isActive = false;
        }
        
        if(nextHandler != null)
            nextHandler.release();
    }

    @Override
    public void setNext(ITouchEventHandler next) {
        nextHandler = next;   
    }

    @Override
    public ITouchEventHandler getNext() {
        return nextHandler;
    }

    private void drawLabels() {        
        starbucks.tint(255, isActive? 255: 150);
        starbucks.image(
            isActive? activeIcon: icon,
            x + (width - icon.width)/2,
            546
        );

        // reset tint
        starbucks.tint(255, 255);

        // label
        starbucks.textFont(roboto);

        starbucks.fill(255, isActive? 255: 150);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.textSize(10);
        starbucks.text(
            command.getLabel(),
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