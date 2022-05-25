private Device theDevice;

synchronized Device getDevice() {
  if(theDevice == null) {
     theDevice = new Device();
   }
   return theDevice;
}

class Device implements IApp {
  
  private final int NOTIFICATION_BAR_HEIGHT = 31;
  private final int NOTIFICATION_BAR_WIDTH = 375;
  
  private PImage notificationBar;
  private boolean authenticated = false;
  
  private PinScreen pinScreen;
  private KeyPad keyPad;
  
  private Device() {
    notificationBar = loadImage("../../assets/images/notification-bar-1.5x.png");
    startUp();
  }
  
  void startUp() {
    pinScreen = new PinScreen();
    keyPad = new KeyPad();
    pinScreen.addSubComponent(keyPad);
  }
  
  void display() {
    fill(color(255, 0, 0));
    image(notificationBar, 0, 0, NOTIFICATION_BAR_WIDTH, NOTIFICATION_BAR_HEIGHT);
    screenDisplay();
  }
  
  void screenDisplay() {
    if(authenticated) {
      // App Screen
    } else {
      // Pin Screen
      pinScreen.display();
    }
  }
  
  void landscape() {
  }
  
  void portrait() {
  }
  
  void touch(int x, int y) {
    pinScreen.touch(x, y);
  }
  
  void reset() {
    pinScreen.reset();
  }
  
  void execute(String c) {
  }
  
  void prev() {
  }
  
  void next() {
  }
  
  String screen() {
    return "Device";
  }
}
