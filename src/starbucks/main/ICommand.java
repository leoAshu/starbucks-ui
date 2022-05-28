/** Command Interface */
public interface ICommand {
    /** Execute the Command */
    void execute();

    /** 
     * Configure the Receiver for the Command
     * @param receiver Callback Receiver
     */
    void setReceiver(ICommandReceiver receiver);
}
