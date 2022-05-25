IApp app;

void setup() {
  size(375, 593);
  app = (IApp) getDevice();
}

void draw() {
  background(255);
  app.display();
}
