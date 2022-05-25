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
  private boolean authenticated = false;
  
  private Device() {
    notificationBar = loadImage("../../assets/images/notification-bar-1.5x.png");
  }
  
  void startUp() {
    //app = getNewAppController();
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
