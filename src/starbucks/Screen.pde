class Screen implements IScreen, IDisplayComponent {
  private List<IDisplayComponent> components;
  private ITouchEventHandler chain;
  
  public Screen() {
    components = new ArrayList<IDisplayComponent>();
  }
  
  /**
   * Send Touch Events to the Chain
   * @param x Touch X Coord.
   * @param y Touch Y Coord.
   */
  @Override
  public void touch(int x, int y) {
    //System.err.println("debug: Screen touch: x = " + x + " y = " + y);
    if(chain != null)
      chain.touch(x, y);
  }
    
  /** Previous Screen - Not Used */
  @Override
  public void prev() {
        // add code here
  }

  /** Next Screen - Not Used */
  @Override
  public void next() {
        // add code here
  }
    
  /**
   * Set Next Screen - Not Used 
   * @param s Next Screen Object
   * @param n Next Screen Label
   */
  @Override
  public void setNext(IScreen s, String n) {
        // add code here
  }
    
  /**
   * Send Previous Screen - Not Used
   * @param s Previous Screen Object
   * @param n Previous Screen Label
   */
  @Override
  public void setPrev(IScreen s, String n) {
        // add code here
  }

  /**
   * Add Display Component to Screen
   * @param c Display Component
   */
  @Override
  public void addSubComponent(IDisplayComponent c) {
    components.add(c);
  }
    
  /**
   * Display Contents
   */
  @Override
  public void display() { 
    for (IDisplayComponent c : components) {
      c.display();
    }
  }

  /**
   * Get Class Name of Current Screen
   * @return Class Name of Current Screen
   */
  @Override
  public String name() {
    return (this.getClass().getName()).split("\\.")[1]; 
  }

  /**
   * Returns true if screen supports landscape mode
   * @return boolean
   */
  @Override
  public boolean supportsLandscape() {
        return false;
  }
}
