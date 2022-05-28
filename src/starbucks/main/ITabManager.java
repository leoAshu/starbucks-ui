/** Tab Manager Interface */
public interface ITabManager {
    /** Sets the context for all tabs */
    void setUpTabs();

    /** 
     * Changes the active tab 
     * @param index Index of the new active tab
    */
    void setTab(int index);
}
