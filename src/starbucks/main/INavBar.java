/** Nav Bar Interface */
public interface INavBar {
    /** 
     * Configure the Nav Bar with Nav Bar options
     * @param label String label
     * @param iconPath String path of the default icon
     * @param activeIcon String path of the icon on active
     * @param command INavBarCommand command to be invoked on touch
     */
    public void addNavBarOption(String label, String iconPath, String activeIconPath, INavBarCommand command);
}
