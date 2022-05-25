private Device theDevice;

synchronized Device getDevice() {
  if(theDevice == null) {
     theDevice = new Device();
   }
   return theDevice;
}

class Device implements IApp {
  //private static Device theDevice;
  
  PImage notificationBar;
  
  private Device() {
    notificationBar = loadImage("../../assets/images/notification-bar-1.5x.png");
  }
  
  void display() {
    fill(color(255, 0, 0));
    image(notificationBar, 0, 0, 375, 32);
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
