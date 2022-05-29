import processing.core.PApplet;

class Button implements ICommandInvoker, IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
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
    private String iconPath;
    private int iconLeftPadding;
    private int iconTopPadding;
    private String backgroundPath;

    private ICommand command;
    private ITouchEventHandler nextHandler;

    public static enum Shape {
        BOX,
        ROUND
    }

    // x = y
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
        this.iconPath = iconPath;
        this.iconLeftPadding = iconLeftPadding;
        this.iconTopPadding = iconTopPadding;
        this.backgroundPath = backgroundPath;
    }

    // x = y with no icon
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
        this.backgroundPath = backgroundPath;
    }

    // x != y
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
        this.iconPath = iconPath;
        this.iconLeftPadding = iconX;
        this.iconTopPadding = iconY;
        this.backgroundPath = backgroundPath;
    }

    // x != y with no icon
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
        this.backgroundPath = backgroundPath;
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
        if(shape == Shape.BOX)
            drawBoxButton();
        else
            drawRoundButton();
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

    private void drawBoxButton() {
        // background
        starbucks.image(
            starbucks.loadImage(backgroundPath),
            x,
            y,
            width,
            height
        );

        // icon
        if(iconPath != null)
            starbucks.image(
                starbucks.loadImage(iconPath),
                x + iconLeftPadding,
                y + iconTopPadding
            );

        // label
        starbucks.textFont(starbucks.createFont(labelFont, 16));
        starbucks.fill(labelFontColor);
        starbucks.textSize(labelFontSize);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.text(
            label,
            x + labelLeftPadding,
            y + labelTopPadding
        );
    }

    private void drawRoundButton() {
        // background
        starbucks.image(
            starbucks.loadImage(backgroundPath),
            x,
            y,
            width,
            height
        );

        // icon
        if(iconPath != null)
            starbucks.image(
                starbucks.loadImage(iconPath),
                x + iconLeftPadding,
                y + iconTopPadding
            );

        // label
        starbucks.textFont(starbucks.createFont(labelFont, 16));
        starbucks.fill(labelFontColor);
        starbucks.textSize(labelFontSize);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.text(
            label,
            x + labelLeftPadding,
            y + labelTopPadding
        );
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