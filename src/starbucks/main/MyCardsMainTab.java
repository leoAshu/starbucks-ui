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

        RoundButton button = new RoundButton(
            starbucks,
            "Pay",
            Constants.ICON_DOLLAR,
            Constants.ROUND_BUTTON
        );
        button.setCommand(command);
        addSubComponent(button);
    }

}
