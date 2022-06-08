import java.util.List;

/** Nav Bar Interface */
public interface INavBar {
    /** 
     * Configure the Nav Bar with Nav Bar options
     * @param commands List of INavBarCommand commands to be invoked on touch
     */
    void setUp(List<INavBarCommand> commands);

    /** 
     * Change the visibility of the nav bar
     * @param visibility state of the nav bar's visibility
     */
    void setVisibility(boolean visibility);

    /** 
     * Update the active nav bar option
     * @param index option index to be toggled to active
     */
    void setActiveOption(int index);
}
