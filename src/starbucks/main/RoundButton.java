import processing.core.PApplet;

class RoundButton implements ICommandInvoker, IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private String label;
    private String iconPath;
    private String backgroundPath;

    private ICommand command;
    private ITouchEventHandler nextHandler;

    public RoundButton(PApplet starbucks, String label, String iconPath, String backgroundPath) {
        this.starbucks = starbucks;
        this.label = label;
        this.iconPath = iconPath;
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
        starbucks.tint(255, 255);
        starbucks.image(
            starbucks.loadImage(backgroundPath),
            Constants.ROUND_BUTTON_X,
            Constants.ROUND_BUTTON_Y,
            Constants.ROUND_BUTTON_SIZE,
            Constants.ROUND_BUTTON_SIZE
        );

        if(iconPath != null)
            starbucks.image(
                starbucks.loadImage(iconPath),
                310,
                290
            );

        starbucks.textFont(starbucks.createFont(Constants.ROBOTO_BOLD_PATH, 16));
        starbucks.fill(0);
        starbucks.textSize(16);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.text(
            label,
            316,
            iconPath != null? 330: 318
        );
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

    private boolean isTouched(int x, int y) {
        int distance = (int)
        (
            Math.sqrt(
                Math.pow((Constants.ROUND_BUTTON_X + 35 - x), 2) + 
                Math.pow((Constants.ROUND_BUTTON_Y + 35 - y), 2)
            )
        );
        return distance <= Constants.ROUND_BUTTON_SIZE/2;
    }

}
