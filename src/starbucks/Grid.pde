class Grid {
  private final int CELL_WIDTH = 125;
  private final int CELL_HEIGHT = 70;
  void display() {
    stroke(color(0, 255, 0));
    
    line(0, 31, 0, height);
    line(width-1, 31, width-1, height);
    
    line(0, 31, width, 31);
    line(0, height-1, width, height-1);
    
    stroke(color(255, 255, 255));
    
    for(int x=CELL_WIDTH; x<width; x+=CELL_WIDTH) {
      line(x, 31, x, height);
    }
    
    for(int y=CELL_HEIGHT+31; y<height-31; y+=CELL_HEIGHT) {
      line(0, y, width, y);
    }
  }
}
