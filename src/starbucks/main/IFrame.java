import java.util.List;

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
     * Initializes a Nav Bar and adds Nav Bar Options
     * @param commands List of INavBarCommand commands
     */
    void setupNavBar(List<INavBarCommand> commands);

    /** 
     * Change the visibility of the nav bar
     * @param visibility state of the nav bar's visibility
     */
    public void setNavBarVisibility(boolean visibility);

    /** Initializes and displays an Overlay */
    void showOverlay();

    /** Hides the overlay */
    void hideOverlay();

    /** Adds custom buttons to overlay
     * @param options list of buttons to be display on overlay
     */
    public void addOptionsToOverlay(List<Button> options);

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
