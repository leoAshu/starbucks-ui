interface IApp {
  void landscape();
  void portrait();
  void touch(int x, int y);
  void reset();
  void display();
  void execute(String c);
  void prev();
  void next();
  String screen();
}
