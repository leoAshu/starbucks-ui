import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

class Overlay implements IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private ITouchEventHandler chain;
    private List<IDisplayComponent> components;

    public Overlay(PApplet starbucks) {
        this.starbucks = starbucks;
        components = new ArrayList<IDisplayComponent>();
        addSubComponent(cancelButton());
    }

    @Override
    public void display() {
        background();
        
        for(IDisplayComponent component: components)
            component.display();
    }

    @Override
    public void addSubComponent(IDisplayComponent component) {
        components.add(component);
        if(components.size() == 1)
            chain = (ITouchEventHandler) component;
        else {
            ITouchEventHandler prev = (ITouchEventHandler) components.get(components.size()-2);
            prev.setNext((ITouchEventHandler) component);
        }
    }

    @Override
    public void touch(int x, int y) {
        boolean isTouched = y > (starbucks.height - Constants.OVERLAY_HEIGHT);
        if(isTouched && chain != null)
            chain.touch(x, y);

    }

    @Override
    public void release() {
        if(chain != null)
            chain.release();
    }

    @Override
    public void setNext(ITouchEventHandler next) {

    }

    public void addOptions(List<Button> options) {
        for(IDisplayComponent option: options)
            addSubComponent(option);
    }

    private void background() {
        // background
        starbucks.image(
            starbucks.loadImage(Constants.OVERLAY_BG),
            0,
            starbucks.height - Constants.OVERLAY_HEIGHT
        );
    }

    private Button cancelButton() {
        // cancel button
        Button cancelButton = new Button(
            starbucks,
            starbucks.width/2 - Constants.OVERLAY_BUTTON_WIDTH/2,
            Constants.OVERLAY_CANCEL_Y,
            Constants.OVERLAY_BUTTON_WIDTH,
            Constants.OVERLAY_BUTTON_HEIGHT,
            Button.Shape.BOX,
            "Cancel",
            Constants.OVERLAY_BUTTON_WIDTH/2,
            Constants.OVERLAY_BUTTON_LABEL_TOP_PADDING,
            Constants.ROBOTO_BOLD_PATH,
            20,
            255,
            Constants.OVERLAY_BUTTON_BG_DARK
        );

        ICommand command = new Command();
        command.setReceiver(new ICommandReceiver() {
            @Override
            public void onClick() {
                AppController.getAppController(starbucks).hideOverlay();
            }
        });

        cancelButton.setCommand(command);
        return cancelButton;
    }

}