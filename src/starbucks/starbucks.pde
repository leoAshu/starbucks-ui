IApp app;
Grid grid;

void setup() {
  size(375, 593);
  app = (IApp) getDevice();
  grid = new Grid();
}

void draw() {
  background(255);
  app.display();
  grid.display();
}
