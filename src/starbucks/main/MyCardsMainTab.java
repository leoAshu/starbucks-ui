import processing.core.PApplet;

public class MyCardsMainTab extends Tab {
    ICard card;

    public MyCardsMainTab(PApplet starbucks) {
        super(starbucks);
        card = new CardDecorator(Card.getCard());
        setUpTabContents();
    }

    private void setUpTabContents() {
        addSubComponent(new CardFrontView(starbucks));

        // setup pay button
        ICommand command = new Command();
        command.setReceiver(new ICommandReceiver() {
            public void onClick() {
                tabManager.setTab(1);
            }
        });

        Button payButton = new Button(
            starbucks,
            Constants.PAY_BUTTON_X,
            Constants.PAY_BUTTON_Y,
            Constants.PAY_BUTTON_SIZE,
            Button.Shape.ROUND,
            "Pay",
            Constants.PAY_BUTTON_LABEL_LEFT_PADDING,
            Constants.PAY_BUTTON_LABEL_TOP_PADDING,
            Constants.ROBOTO_REG_PATH,
            16,
            0,
            Constants.ICON_DOLLAR,
            Constants.PAY_BUTTON_ICON_LEFT_PADDING,
            Constants.PAY_BUTTON_ICON_TOP_PADDING,
            Constants.PAY_BUTTON
        );
        payButton.setCommand(command);
        addSubComponent(payButton);

        // setup pay button
        command = new Command();
        command.setReceiver(new ICommandReceiver() {
            public void onClick() {
                
            }
        });

        Button balanceButton = new Button(
            starbucks,
            Constants.BALANCE_BUTTON_X,
            Constants.BALANCE_BUTTON_Y,
            Constants.BALANCE_BUTTON_WIDTH,
            Constants.BALANCE_BUTTON_HEIGHT,
            Button.Shape.BOX,
            card.balance(),
            // "$ 0.00",
            Constants.BALANCE_BUTTON_WIDTH/2,
            Constants.BALANCE_BUTTON_HEIGHT/2 + 10,
            Constants.ROBOTO_REG_PATH,
            32,
            255,
            Constants.BALANCE_BUTTON
        );
        payButton.setCommand(command);
        addSubComponent(balanceButton);
    }

}
