import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

public class MyCardsMainTab extends Tab {
    ICard card;

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
            Constants.BALANCE_BUTTON_X,
            Constants.BALANCE_BUTTON_Y,
            Constants.BALANCE_BUTTON_WIDTH,
            Constants.BALANCE_BUTTON_HEIGHT,
            Button.Shape.BOX,
            card.balance(),
            Constants.BALANCE_BUTTON_WIDTH/2,
            Constants.BALANCE_BUTTON_HEIGHT/2 + 10,
            Constants.ROBOTO_REG_PATH,
            32,
            255,
            Constants.BALANCE_BUTTON_BG
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
            starbucks.width/2 - Constants.OVERLAY_BUTTON_WIDTH/2,
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
            Constants.OVERLAY_BUTTON_BG
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
