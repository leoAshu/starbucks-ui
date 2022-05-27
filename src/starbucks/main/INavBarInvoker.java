/** Nav Bar Invoker Interface */
public interface INavBarInvoker {
    /**
     * Set Command for Invoker
     * @param command Command Object
     */
    void setCommand(INavBarCommand command);

    /** Invoke Nav Bar Function */
    void invoke();
}
