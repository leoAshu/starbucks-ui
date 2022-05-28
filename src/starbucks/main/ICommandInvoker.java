/** Command Invoker Interface */
public interface ICommandInvoker {
    /**
     * Set Command for Invoker
     * @param command Command Object
     */
    void setCommand(ICommand command);

    /** Invoke Nav Bar Function */
    void invoke();
}
