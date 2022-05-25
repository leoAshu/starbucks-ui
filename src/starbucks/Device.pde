private Device theDevice;

synchronized Device getDevice() {
  if(theDevice == null) {
     theDevice = new Device();
   }
   return theDevice;
}

class Device implements IApp {
  
  private PImage notificationBar;
  //private IApp app;
  private PinScreen pinScreen;
  private boolean authenticated = false;
  
  private Device() {
    notificationBar = loadImage("../../assets/images/notification-bar-1.5x.png");
    startUp();
  }
  
  void startUp() {
    //app = getNewAppController();
    pinScreen = new PinScreen();
  }
  
  void display() {
    fill(color(255, 0, 0));
    image(notificationBar, 0, 0, 375, 32);
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
  
  void touch() {
  }
  
  //void display();
  
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
