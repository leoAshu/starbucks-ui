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

    /**
     * Initializes a Nav Bar and adds Nav Bar Optiins
     * @param label String label
     * @param iconPath String path of the default icon
     * @param activeIcon String path of the icon on active
     * @param command INavBarCommand command to be invoked on touch
     */
    void addNavBarOption(String label, String iconPath, String activeIconPath, INavBarCommand command);
}
