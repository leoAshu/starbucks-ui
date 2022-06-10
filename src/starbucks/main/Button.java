import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

class Button implements ICommandInvoker, IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private PFont font;
    private PImage icon, background;
    private int x;
    private int y;
    private int width;
    private int height;
    private Shape shape;
    private String label;
    private int labelLeftPadding;
    private int labelTopPadding;
    private String labelFont;
    private int labelFontSize;
    private int labelFontColor;
    // private String iconPath;
    private int iconLeftPadding;
    private int iconTopPadding;
    // private String backgroundPath;

    private ICommand command;
    private ITouchEventHandler nextHandler;

    public static enum Shape {
        BOX,
        ROUND
    }

    // height = width
    public Button(PApplet starbucks, int x, int y, int size, Shape shape, String label, int labelLeftPadding, int labelTopPadding,  String labelFont, int labelFontSize, int labelFontColor, String iconPath, int iconLeftPadding, int iconTopPadding, String backgroundPath) {
        this.starbucks = starbucks;
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
        this.shape = shape;
        this.label = label;
        this.labelLeftPadding = labelLeftPadding;
        this.labelTopPadding = labelTopPadding;
        this.labelFont = labelFont;
        this.labelFontSize = labelFontSize;
        this.labelFontColor = labelFontColor;
        // this.iconPath = iconPath;
        icon = starbucks.loadImage(iconPath);
        this.iconLeftPadding = iconLeftPadding;
        this.iconTopPadding = iconTopPadding;
        // this.backgroundPath = backgroundPath;
        this.background = starbucks.loadImage(backgroundPath);   
    }

    // height = width with no icon
    public Button(PApplet starbucks, int x, int y, int size, Shape shape, String label, int labelLeftPadding, int labelTopPadding, String labelFont, int labelFontSize, int labelFontColor, String backgroundPath) {
        this.starbucks = starbucks;
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
        this.shape = shape;
        this.label = label;
        this.labelLeftPadding = labelLeftPadding;
        this.labelTopPadding = labelTopPadding;
        this.labelFont = labelFont;
        this.labelFontSize = labelFontSize;
        this.labelFontColor = labelFontColor;
        // this.backgroundPath = backgroundPath;
        this.background = starbucks.loadImage(backgroundPath);
    }

    // height != width
    public Button(PApplet starbucks, int x, int y, int width, int height, Shape shape, String label, int labelLeftPadding, int labelTopPadding, String labelFont, int labelFontSize, int labelFontColor, String iconPath, int iconX, int iconY, String backgroundPath) {
        this.starbucks = starbucks;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.shape = shape;
        this.label = label;
        this.labelLeftPadding = labelLeftPadding;
        this.labelTopPadding = labelTopPadding;
        this.labelFont = labelFont;
        this.labelFontSize = labelFontSize;
        this.labelFontColor = labelFontColor;
        // this.iconPath = iconPath;
        this.icon = starbucks.loadImage(iconPath);
        this.iconLeftPadding = iconX;
        this.iconTopPadding = iconY;
        // this.backgroundPath = backgroundPath;
        this.background = starbucks.loadImage(backgroundPath);

    }

    // height != width with no icon
    public Button(PApplet starbucks, int x, int y, int width, int height, Shape shape, String label, int labelLeftPadding, int labelTopPadding, String labelFont, int labelFontSize, int labelFontColor, String backgroundPath) {
        this.starbucks = starbucks;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.shape = shape;
        this.label = label;
        this.labelLeftPadding = labelLeftPadding;
        this.labelTopPadding = labelTopPadding;
        this.labelFont = labelFont;
        this.labelFontSize = labelFontSize;
        this.labelFontColor = labelFontColor;
        // this.backgroundPath = backgroundPath;
        this.background = starbucks.loadImage(backgroundPath);
    }

    // height != width with no label & icon
    public Button(PApplet starbucks, int x, int y, int width, int height, Shape shape, String backgroundPath) {
        this.starbucks = starbucks;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.shape = shape;
        // this.backgroundPath = backgroundPath;
        this.background = starbucks.loadImage(backgroundPath);
    }

    @Override
    public void setCommand(ICommand command) {
        this.command = command;
    }

    @Override
    public void invoke() {
        command.execute();
    }

    @Override
    public void display() {
        if(font == null && label != null)
            font = starbucks.createFont(labelFont, labelFontSize);

        // reduce opacity if command not set
        if(command == null)
            starbucks.tint(255, 120);
        
        if(shape == Shape.BOX)
            drawBoxButton();
        else
            drawRoundButton();

        // reset tint
        starbucks.tint(255, 255);
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {
        
    }

    @Override
    public void touch(int x, int y) {
        if(isTouched(x, y)) {
            invoke();
            return;
        }

        if(nextHandler != null)
            nextHandler.touch(x, y);
    }

    @Override
    public void release() {
        if(nextHandler != null) {
            nextHandler.release();
        }
    }

    @Override
    public void setNext(ITouchEventHandler next) {
        nextHandler = next;
    }

    @Override
    public ITouchEventHandler getNext() {
        return nextHandler;
    }

    private void drawBoxButton() {
        // background
        starbucks.image(
            background,
            x,
            y,
            width,
            height
        );

        // icon
        if(icon != null)
            starbucks.image(
                icon,
                x + iconLeftPadding,
                y + iconTopPadding
            );

        // label
        if(label != null) {
            starbucks.textFont(font);
            starbucks.fill(labelFontColor, command == null? 120: 255);
            starbucks.textSize(labelFontSize);
            starbucks.textAlign(PApplet.CENTER);
            starbucks.text(
                label,
                x + labelLeftPadding,
                y + labelTopPadding
            );
        }
    }

    private void drawRoundButton() {
        // background
        starbucks.image(
            background,
            x,
            y,
            width,
            height
        );

        // icon
        if(icon != null)
            starbucks.image(
                icon,
                x + iconLeftPadding,
                y + iconTopPadding
            );

        // label
        if(label != null) {
            starbucks.textFont(font);
            starbucks.fill(labelFontColor, command == null? 120: 255);
            starbucks.textSize(labelFontSize);
            starbucks.textAlign(PApplet.CENTER);
            starbucks.text(
                label,
                x + labelLeftPadding,
                y + labelTopPadding
            );
        }
    }

    private boolean isTouched(int x, int y) {
        if(shape == Shape.BOX) {
            boolean overX = x > this.x && x < this.x + width;
            boolean overY = y > this.y && y < this.y + height;
            return overX && overY;
        }
        else {
            int distance = (int)
            (
                Math.sqrt(
                    Math.pow((Constants.PAY_BUTTON_X + 35 - x), 2) + 
                    Math.pow((Constants.PAY_BUTTON_Y + 35 - y), 2)
                )
            );
            return distance <= Constants.PAY_BUTTON_SIZE/2;
        }
    }

}
