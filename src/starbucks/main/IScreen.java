/** Screen Interface */
public interface IScreen {
    /**
     * Send touch events to screen
     * @param x Touch X
     * @param y Touch Y
     */
    void touch(int x, int y);              

    /**
     * Displays screen components
     * @return Return Screen Contents
     */
    String display(); 
}
