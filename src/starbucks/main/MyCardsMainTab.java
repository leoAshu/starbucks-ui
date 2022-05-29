import processing.core.PApplet;

public class MyCardsMainTab extends Tab {

    public MyCardsMainTab(PApplet starbucks) {
        super(starbucks);
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
            Constants.ICON_DOLLAR,
            Constants.PAY_BUTTON_ICON_LEFT_PADDING,
            Constants.PAY_BUTTON_ICON_TOP_PADDING,
            Constants.PAY_BUTTON
        );
        payButton.setCommand(command);
        addSubComponent(payButton);
    }

}
