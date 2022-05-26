/**
 * Key Pad Observer Interface
 */
public interface IKeyPadObserver {
    /**
     * Key Event to Notify Observers 
     * @param keyCount Number of Digits So Far
     * @param key     Key/Digit Pressed
     */
    void keyEventUpdate(int keyCount, String key);
}