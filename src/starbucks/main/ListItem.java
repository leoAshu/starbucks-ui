import processing.core.PApplet;

public class ListItem implements IDisplayComponent, ITouchEventHandler, ICommandInvoker {
    private PApplet starbucks;
    private int y;
    private String label;
    private String iconPath;
    private String backgroundPath;

    private ICommand command;
    private ITouchEventHandler nextHandler;

    public ListItem(String label, String iconPath) {
        this.label = label;
        this.iconPath = iconPath;
    }

    public void setItemProps(PApplet starbucks, int y, String backgroundPath) {
        this.starbucks = starbucks;
        this.y = y;
        this.backgroundPath = backgroundPath;
    }

    @Override
    public void display() {
        // reduce opacity if command not set
        if(command == null)
            starbucks.tint(255, 120);

        // background
        starbucks.image(
            starbucks.loadImage(backgroundPath),
            (starbucks.width - Constants.SETTINGS_ITEM_WIDTH)/2,
            y,
            Constants.SETTINGS_ITEM_WIDTH,
            Constants.SETTINGS_ITEM_HEIGHT
        );

        // label
        starbucks.textFont(starbucks.createFont(Constants.ROBOTO_REG_PATH, 18));
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
            starbucks.loadImage(iconPath),
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
        if(isTouched(x, y) && command != null){
            invoke();
            return;
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
