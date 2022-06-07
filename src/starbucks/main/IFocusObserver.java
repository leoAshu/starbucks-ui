/**
 * Focus Observer Interface
 */
public interface IFocusObserver
{
    /**
     * setFocus Event to Notify Observers 
     * @param status boolean if observer is focused or not
     */
    void setFocus(boolean isFocused);
}
