/**
 * Frame Interface
 */
public interface IFrame {
    /**
     * Set Current Screen
     * @param s Reference to Screen
     */
    void setCurrentScreen(IScreen screen);

    /**
     * Send a Touch Event
     * @param x Screen X Coord
     * @param y Screen Y Coord
     */
    void touch(int x, int y);

    /**
     * Send release event to current screen
     */
    void release();

    /**
     * Display contents of current screen
     */
    void display();

}
