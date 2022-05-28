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

        RoundButton button = new RoundButton(
            starbucks,
            "Done!",
            null,
            Constants.ROUND_BUTTON
        );
        button.setCommand(command);
        addSubComponent(button);
    }

}
