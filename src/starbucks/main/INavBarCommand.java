/** Nav Bar Command Interface */
public interface INavBarCommand {
    /**required getters */
    String getLabel();
    String getIconPath();
    String getActiveIconPath();

    /** Execute the Command */
    void execute();

    /** 
     * Configure the Receiver for the Command
     * @param receiver Callback Receiver
     */
    void setReceiver(INavBarReceiver receiver);
}
