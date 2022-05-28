import processing.core.PApplet;

public class MyCardsPayTab extends Tab {

    public MyCardsPayTab(PApplet starbucks) {
        super(starbucks);
    }
    
    @Override
    public void display() {
        super.display();
        
        starbucks.fill(194);
        starbucks.stroke(2);
        starbucks.rectMode(PApplet.CENTER);
        starbucks.rect(starbucks.width/2, starbucks.height/2, 80, 40);

        starbucks.fill(0);
        starbucks.textSize(16);
        starbucks.textAlign(PApplet.CENTER);
        starbucks.text("Pay Tab", starbucks.width/2, starbucks.height/2+4);
    }

    @Override
    public void touch(int x, int y) {
        boolean overX = x > (starbucks.width/2 - 40) && x <(starbucks.width/2 + 40);
        boolean overY = y > (starbucks.height/2 - 20) && y <(starbucks.height/2 + 20);
        if(overX && overY) {
            tabManager.setTab(0);
        }
    }

}
