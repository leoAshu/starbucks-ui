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
        addSubComponent(new AppBar(starbucks, name()));
        addSubComponent(new CardInputView(starbucks, card));
        addSubComponent(new KeyPad(starbucks));
    }
}
