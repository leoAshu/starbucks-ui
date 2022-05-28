import processing.core.PApplet;

public class MyCardsPayTab extends Tab {

    public MyCardsPayTab(PApplet starbucks) {
        super(starbucks);
    }
    
    @Override
    public void display() {
        super.display();

        cardBackView();
    }

    @Override
    public void touch(int x, int y) {
        boolean overX = x > (starbucks.width/2 - 40) && x <(starbucks.width/2 + 40);
        boolean overY = y > (starbucks.height/2 - 20) && y <(starbucks.height/2 + 20);
        if(overX && overY) {
            tabManager.setTab(0);
        }
    }

    public void cardBackView() {
        // card
        starbucks.image(
            starbucks.loadImage(Constants.CARD_BACK),
            (starbucks.width - Constants.CARD_WIDTH)/2,
            Constants.NOTIF_BAR_HEIGHT + Constants.APP_BAR_HEIGHT + Constants.CARD_TOP_PADDING,
            Constants.CARD_WIDTH, 
            Constants.CARD_HEIGHT
        );

        // card number
        starbucks.textFont(starbucks.createFont(Constants.ROBOTO_MED_PATH, 18));
        
        starbucks.fill(0);
        starbucks.textSize(24);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.text(
            Card.getCard().cardNum(),
            220,
            Constants.NOTIF_BAR_HEIGHT + Constants.APP_BAR_HEIGHT + Constants.CARD_NUM_TOP_PADDING
        );
    }

}
