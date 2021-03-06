import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

class Overlay implements IDisplayComponent, ITouchEventHandler {
    private PApplet starbucks;
    private PImage background;
    private boolean isTouched;

    private ITouchEventHandler chain;
    private List<IDisplayComponent> components;

    public Overlay(PApplet starbucks) {
        this.starbucks = starbucks;
        background = starbucks.loadImage(Constants.OVERLAY_BG);

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
        if(isTouched(y) && chain != null) {
            isTouched = true;
            chain.touch(x, y);
        }

    }

    @Override
    public void release() {
        if(isTouched && chain != null) {
            chain.release();
            isTouched = false;
        }
    }

    @Override
    public void setNext(ITouchEventHandler next) {

    }

    @Override
    public ITouchEventHandler getNext() {
        return null;
    }

    public void addOptions(List<Button> options) {
        components.clear();

        for(IDisplayComponent option: options)
            addSubComponent(option);
        
        addSubComponent(cancelButton());
    }

    private void background() {
        // background
        starbucks.image(
            background,
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
            Constants.OVERLAY_BUTTON_BG_DARK,
            Constants.OVERLAY_BUTTON_PRESSED_BG_DARK
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

    private boolean isTouched(int y) {
        return y > (starbucks.height - Constants.OVERLAY_HEIGHT);
    }

}