import processing.core.PApplet;

public class MyCardsMainTab extends Tab {

    public MyCardsMainTab(PApplet starbucks) {
        super(starbucks);
        setUpTabContents();
    }

    @Override
    public void touch(int x, int y) {
        boolean overX = x > (starbucks.width/2 - 40) && x <(starbucks.width/2 + 40);
        boolean overY = y > (starbucks.height/2 - 20) && y <(starbucks.height/2 + 20);
        if(overX && overY) {
            tabManager.setTab(1);
        }
    }

    private void setUpTabContents() {
        addSubComponent(new CardFrontView(starbucks));
    }

}
