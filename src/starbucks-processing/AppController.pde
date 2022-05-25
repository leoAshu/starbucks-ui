private AppController theController;

synchronized AppController getNewAppController() {
  theController = new AppController();
  theController.startUp();
  return theController;
}

synchronized AppController getAppController() {
  if (theController == null) {
    theController = new AppController();
    theController.startUp();
  }
  return theController;
}



class AppController implements IApp {
  
  private AppController() {
  }
  
  void startUp() {
  }
  
  void landscape() {
  }
  
  void portrait() {
  }
  
  void touch(int x, int y) {
  }
  
  void reset() {
  }
  
  void display() {
  }
  
  void execute(String c) {
  }
  
  void prev() {
  }
  
  void next() {
  }
  
  String screen() {
    return "";
  }
}
