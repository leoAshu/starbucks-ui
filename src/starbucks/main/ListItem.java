import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class ListItem implements IDisplayComponent, ITouchEventHandler, ICommandInvoker {
    private PApplet starbucks;
    private PFont roboto;
    private PImage activeBackground, background, icon;
    private int y;
    private String label;
    private String iconPath;
    private boolean isTouched;

    private ICommand command;
    private ITouchEventHandler nextHandler;

    public ListItem(String label, String iconPath) {
        this.label = label;
        this.iconPath = iconPath;
        isTouched = false;
    }

    public void setItemProps(PApplet starbucks, int y, String backgroundPath, String activeBackgroundPath) {
        this.starbucks = starbucks;
        this.y = y;
        icon = starbucks.loadImage(iconPath);
        background = starbucks.loadImage(backgroundPath);
        activeBackground = starbucks.loadImage(activeBackgroundPath);
    }

    @Override
    public void display() {
        if(roboto == null)
            roboto = starbucks.createFont(Constants.ROBOTO_REG_PATH, 16);

        // reduce opacity if command not set
        if(command == null)
            starbucks.tint(255, 120);

        // background
        starbucks.image(
            isTouched? activeBackground: background,
            (starbucks.width - Constants.SETTINGS_ITEM_WIDTH)/2,
            y,
            Constants.SETTINGS_ITEM_WIDTH,
            Constants.SETTINGS_ITEM_HEIGHT
        );

        // label
        starbucks.textFont(roboto);
        starbucks.fill(0, command == null? 120: 255);
        starbucks.textSize(16);
        starbucks.textAlign(PApplet.LEFT);
        starbucks.text(
            label,
            (starbucks.width - Constants.SETTINGS_ITEM_WIDTH)/2 + Constants.LIST_ITEM_LABEL_LEFT_PADDING,
            y + Constants.LIST_ITEM_LABEL_TOP_PADDING
        );

        // icon
        starbucks.image(
            icon,
            (starbucks.width - Constants.SETTINGS_ITEM_WIDTH)/2 + Constants.LIST_ITEM_ICON_LEFT_PADDING,
            y + Constants.LIST_ITEM_ICON_TOP_PADDING
        );

        // reset tint
        starbucks.tint(255, 255);
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {
        
    }

    @Override
    public void touch(int x, int y) {
        if(isTouched(x, y) && command != null) {
            isTouched = true;
            return;
        }

        if(nextHandler != null)
            nextHandler.touch(x, y);
    }

    @Override
    public void release() {
        if(isTouched) {
            isTouched = false;
            invoke();
            return;
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

    @Override
    public void setCommand(ICommand command) {
        this.command = command;
    }

    @Override
    public void invoke() {
        command.execute();
    }

    private boolean isTouched(int x, int y) {
        return y > this.y && y < (this.y + Constants.SETTINGS_ITEM_HEIGHT);
    }
}
