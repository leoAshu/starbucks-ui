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

        // setup pay button
        ICommand command = new Command();
        command.setReceiver(new ICommandReceiver() {
            public void onClick() {
                tabManager.setTab(0);
            }
        });

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
            Constants.PAY_BUTTON
        );
        doneButton.setCommand(command);
        addSubComponent(doneButton);


        // setup scan button
        command = new Command();
        command.setReceiver(new ICommandReceiver() {
            public void onClick() {
                // make payment
            }
        });

        Button scanButton = new Button(
            starbucks,
            Constants.SCAN_BUTTON_X,
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
        scanButton.setCommand(command);
        addSubComponent(scanButton);
    }

}
