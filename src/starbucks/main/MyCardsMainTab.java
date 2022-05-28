import processing.core.PApplet;

public class MyCardsMainTab extends Tab {

    public MyCardsMainTab(PApplet starbucks) {
        super(starbucks);
    }
    
    @Override
    public void display() {
        super.display();
        
        cardFrontView();
    }

    @Override
    public void touch(int x, int y) {
        boolean overX = x > (starbucks.width/2 - 40) && x <(starbucks.width/2 + 40);
        boolean overY = y > (starbucks.height/2 - 20) && y <(starbucks.height/2 + 20);
        if(overX && overY) {
            tabManager.setTab(1);
        }
    }

    public void cardFrontView() {
        // card
        starbucks.image(
            starbucks.loadImage(Constants.CARD_FRONT),
            (starbucks.width - Constants.CARD_WIDTH)/2,
            Constants.NOTIF_BAR_HEIGHT + Constants.APP_BAR_HEIGHT + Constants.CARD_TOP_PADDING,
            Constants.CARD_WIDTH, 
            Constants.CARD_HEIGHT
        );
    }

}
