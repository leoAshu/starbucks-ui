import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class AddCardScreen extends Screen {
    private CardInputView cardInputView;
    private KeyPad keyPad;

    public AddCardScreen(PApplet starbucks) {
        super(starbucks, Constants.SCREEN_BG_DARK);
        cardInputView = new CardInputView(starbucks);
        keyPad = new KeyPad(starbucks);
        keyPad.attach((IKeyPadObserver)cardInputView);
        
        setUpAppBar();
        addSubComponent(cardInputView);
        addSubComponent(keyPad);

    }
    
    @Override
    public void display() {
        super.display();
    }

    @Override
    public String name() {
        return "Add Card";
    }

    private void setUpAppBar() {
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
        command = new Command();
        command.setReceiver(new ICommandReceiver() {
            @Override
            public void onClick() {
                back();   
            }
        });
        option.setCommand(command);
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
        command = new Command();
        command.setReceiver(new ICommandReceiver() {
            @Override
            public void onClick() {
                next();
            }
        });
        option.setCommand(command);
        options.add(option);

        addSubComponent(new AppBar(starbucks, name(), options));
    }

    private void back() {
        cardInputView.reset();
        AppController.getAppController(starbucks).setScreen(AppController.SCREENS.SETTINGS);
    }

    private void next() {
        if(cardInputView.validate())
            AppController.getAppController(starbucks).setScreen(AppController.SCREENS.MY_CARDS);
    }
}
