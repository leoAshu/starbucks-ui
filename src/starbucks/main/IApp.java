/**
 * App Interface -- Actions Users can Take.
 */
public interface IApp {
    /**
     * Send touch event to current screen
     * @param x Touch at X Coord
     * @param y Touch at Y Coord
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
