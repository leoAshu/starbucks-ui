import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class AddCardScreen extends Screen {
    private ICard card;

    public AddCardScreen(PApplet starbucks) {
        super(starbucks, Constants.SCREEN_BG_DARK);
        card = new CardDecorator(Card.getNewCard());
        setUpScreen();
    }
    
    @Override
    public void display() {
        super.display();

    }

    @Override
    public String name() {
        return "Add Card";
    }

    private void setUpScreen() {
        // app bar
        // app bar options
        Button option;
        ICommand command;
        List<Button> options = new ArrayList<Button>();
        
        // back option
        option = new Button(
            starbucks,
            8,
            42,
            Constants.APP_BAR_OPTION_WIDTH,
            Constants.APP_BAR_OPTION_HEIGHT,
            Button.Shape.BOX,
            "Back",
            Constants.APP_BAR_OPTION_WIDTH/2,
            Constants.APP_BAR_OPTION_TOP_PADDING,
            Constants.ROBOTO_MED_PATH,
            14,
            255,
            Constants.APP_BAR_BUTTON_BG
        );
        options.add(option);
        
        // next option
        option = new Button(
            starbucks,
            293,
            42,
            Constants.APP_BAR_OPTION_WIDTH,
            Constants.APP_BAR_OPTION_HEIGHT,
            Button.Shape.BOX,
            "Next",
            Constants.APP_BAR_OPTION_WIDTH/2,
            Constants.APP_BAR_OPTION_TOP_PADDING,
            Constants.ROBOTO_MED_PATH,
            14,
            255,
            Constants.APP_BAR_BUTTON_BG
        );
        options.add(option);

        addSubComponent(new AppBar(starbucks, name(), options));
        addSubComponent(new CardInputView(starbucks, card));
        addSubComponent(new KeyPad(starbucks));


    }
}
