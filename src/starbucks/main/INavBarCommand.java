/** Nav Bar Command Interface */
public interface INavBarCommand {
    /** Execute the Command */
    void execute();

    /** 
     * Configure the Receiver for the Command
     * @param receiver Callback Receiver
     */
    void setReceiver(INavBarReceiver receiver);
}
