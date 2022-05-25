

import processing.core.PApplet;

public class Grid {
    private PApplet starbucks;

    public Grid(PApplet starbucks) {
        this.starbucks = starbucks;
    }
    
    public void display() {
        drawBorders();
        drawGrid();
    }

    private void drawBorders() {
        starbucks.strokeWeight(2);
        starbucks.stroke(0, 255, 0);

        // screen borders
        // top border
        starbucks.line(
            0,
            Constants.NOTIF_BAR_HEIGHT,
            starbucks.width,
            Constants.NOTIF_BAR_HEIGHT
        );

        // bottom border
        starbucks.line(
            0,
            starbucks.height-2,
            starbucks.width,
            starbucks.height-2
        );

        // left border
        starbucks.line(
            0,
            Constants.NOTIF_BAR_HEIGHT,
            0,
            starbucks.height
        );

        // right border
        starbucks.line(
            starbucks.width-2,
            Constants.NOTIF_BAR_HEIGHT,
            starbucks.width-2,
            starbucks.height
        );
    }

    private void drawGrid() {
        starbucks.strokeWeight(1);
        starbucks.stroke(255);

        int x = Constants.CELL_WIDTH;
        int y = Constants.CELL_HEIGHT + Constants.NOTIF_BAR_HEIGHT;

        for(; x < starbucks.width; x += Constants.CELL_WIDTH) {
            starbucks.line(
                x,
                Constants.NOTIF_BAR_HEIGHT,
                x,
                starbucks.height
            );
          }
          
          for(; y < starbucks.height-31; y += Constants.CELL_HEIGHT) {
            starbucks.line(
                0,
                y,
                starbucks.width,
                y
            );
          }
    }
}
