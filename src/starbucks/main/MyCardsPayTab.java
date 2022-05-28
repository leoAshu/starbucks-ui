import processing.core.PApplet;

public class MyCardsPayTab extends Tab {
    ICard card;

    public MyCardsPayTab(PApplet starbucks) {
        super(starbucks);
        card = new CardDecorator(Card.getCard());
        setUpTabContents();
    }

    @Override
    public void touch(int x, int y) {
        boolean overX = x > (starbucks.width/2 - 40) && x <(starbucks.width/2 + 40);
        boolean overY = y > (starbucks.height/2 - 20) && y <(starbucks.height/2 + 20);
        if(overX && overY) {
            tabManager.setTab(0);
        }
    }

    private void setUpTabContents() {
        addSubComponent(new CardBackView(starbucks, card));
    }

}
