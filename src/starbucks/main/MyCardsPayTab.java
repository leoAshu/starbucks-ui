import processing.core.PApplet;

public class MyCardsPayTab extends Tab {
    ICard card;

    public MyCardsPayTab(PApplet starbucks) {
        super(starbucks);
        card = new CardDecorator(Card.getCard());
        setUpTabContents();
    }

    private void setUpTabContents() {
        addSubComponent(new CardBackView(starbucks, card));
        addSubComponent(doneButton());
        addSubComponent(scanNowButton());
    }

    private Button doneButton() {
        // setup done button
        Button doneButton = new Button(
            starbucks,
            Constants.PAY_BUTTON_X,
            Constants.PAY_BUTTON_Y,
            Constants.PAY_BUTTON_SIZE,
            Button.Shape.ROUND,
            "Done!",
            Constants.PAY_BUTTON_LABEL_LEFT_PADDING,
            Constants.DONE_BUTTON_LABEL_TOP_PADDING,
            Constants.ROBOTO_BOLD_PATH,
            16,
            0,
            Constants.PAY_BUTTON_BG
        );

        ICommand command = new Command();
        command.setReceiver(new ICommandReceiver() {
            public void onClick() {
                tabManager.setTab(0);
            }
        });
        doneButton.setCommand(command);

        return doneButton;
    }

    private Button scanNowButton() {
        // setup scan now button
        Button scanNowButton = new Button(
            starbucks,
            (starbucks.width - Constants.SCAN_BUTTON_SIZE)/2,
            Constants.SCAN_BUTTON_Y,
            Constants.SCAN_BUTTON_SIZE,
            Button.Shape.BOX,
            "Scan Now",
            Constants.SCAN_BUTTON_LABEL_LEFT_PADDING,
            Constants.SCAN_BUTTON_SIZE + Constants.SCAN_BUTTON_LABEL_TOP_PADDING,
            Constants.ROBOTO_MED_PATH,
            20,
            255,
            Constants.COFFEE_CUP_ICON_WHITE
        );

        ICommand command = new Command();
        command.setReceiver(new ICommandReceiver() {
            public void onClick() {
                // make payment
            }
        });
        scanNowButton.setCommand(command);

        return scanNowButton;
    }

}
