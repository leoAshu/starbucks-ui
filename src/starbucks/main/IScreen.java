/** Screen Interface */
public interface IScreen {
    /**
     * Send touch events to screen
     * @param x Touch X
     * @param y Touch Y
     */
    void touch(int x, int y);
    
    /**
     * Send release event to current screen
     */
    void release();

    /**
     * Displays screen components
     */
    void display();

    /**
     * Returns name of screen
     * @return Screen Name
     */
    String name();
}
