import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

public class MyCardsMainTab extends Tab {
    private ICard card;

    public MyCardsMainTab(PApplet starbucks) {
        super(starbucks);
        card = new CardDecorator(Card.getCard());
        setUpTabContents();
    }

    private void setUpTabContents() {
        addSubComponent(new CardFrontView(starbucks));
        addSubComponent(payButton());
        addSubComponent(balanceButton());
    }

    @Override
    public void display() {
        super.display();

        if(tiro == null)
            tiro = starbucks.createFont(Constants.TIRO_REG_PATH, 36);
        
        // card balance
        starbucks.textFont(tiro);
        starbucks.fill(255);
        starbucks.textSize(36);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.text(
            card.balance(),
            (starbucks.width - Constants.BALANCE_BUTTON_WIDTH)/2 + Constants.BALANCE_BUTTON_WIDTH/2,
            Constants.BALANCE_BUTTON_Y + Constants.BALANCE_BUTTON_HEIGHT/2 + 10
        );
    }

    private Button payButton() {
        // setup pay button
        Button payButton = new Button(
            starbucks,
            Constants.PAY_BUTTON_X,
            Constants.PAY_BUTTON_Y,
            Constants.PAY_BUTTON_SIZE,
            Button.Shape.ROUND,
            "Pay",
            Constants.PAY_BUTTON_LABEL_LEFT_PADDING,
            Constants.PAY_BUTTON_LABEL_TOP_PADDING,
            Constants.ROBOTO_BOLD_PATH,
            16,
            0,
            Constants.ICON_DOLLAR,
            Constants.PAY_BUTTON_ICON_LEFT_PADDING,
            Constants.PAY_BUTTON_ICON_TOP_PADDING,
            Constants.PAY_BUTTON_BG
        );

        ICommand command = new Command();
        command.setReceiver(new ICommandReceiver() {
            public void onClick() {
                tabManager.setTab(1);
            }
        });
        payButton.setCommand(command);

        return payButton;
    }

    private Button balanceButton() {
        // setup balance button
        Button balanceButton = new Button(
            starbucks,
            (starbucks.width - Constants.BALANCE_BUTTON_WIDTH)/2,
            Constants.BALANCE_BUTTON_Y,
            Constants.BALANCE_BUTTON_WIDTH,
            Constants.BALANCE_BUTTON_HEIGHT,
            Button.Shape.BOX,
            Constants.BALANCE_BUTTON_BG,
            Constants.BALANCE_BUTTON_PRESSED_BG
        );

        ICommand command = new Command();
        command.setReceiver(new ICommandReceiver() {
            public void onClick() {
                setUpOverlayOptions();
                AppController.getAppController(starbucks).showOverlay();
            }
        });
        balanceButton.setCommand(command);

        return balanceButton;
    }

    private void setUpOverlayOptions() {
        List<Button> options = new ArrayList<Button>();

        options.add(overlayOption(Constants.OVERLAY_RELOAD_Y, "Reload Card"));
        options.add(overlayOption(Constants.OVERLAY_REFRESH_Y, "Reload Balance"));
        options.add(overlayOption(Constants.OVERLAY_MORE_Y, "More Options"));

        AppController.getAppController(starbucks).addOptions(options);
    }

    private Button overlayOption(int y, String label) {
        Button overlayOption = new Button(
            starbucks,
            (starbucks.width - Constants.OVERLAY_BUTTON_WIDTH)/2,
            y,
            Constants.OVERLAY_BUTTON_WIDTH,
            Constants.OVERLAY_BUTTON_HEIGHT,
            Button.Shape.BOX,
            label,
            Constants.OVERLAY_BUTTON_WIDTH/2,
            Constants.OVERLAY_BUTTON_LABEL_TOP_PADDING,
            Constants.ROBOTO_BOLD_PATH,
            20,
            0,
            Constants.OVERLAY_BUTTON_BG,
            Constants.OVERLAY_BUTTON_PRESSED_BG
        );

        ICommand command = new Command();
        command.setReceiver(new ICommandReceiver() {
            @Override
            public void onClick() {
                AppController.getAppController(starbucks).hideOverlay();
            }
        });
        overlayOption.setCommand(command);

        return overlayOption;
    }

}
