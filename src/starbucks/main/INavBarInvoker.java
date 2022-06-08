/** Nav Bar Invoker Interface */
public interface INavBarInvoker {
    /**
     * Set active state for Invoker
     * @param isActive new active state
     */
    void setActive(boolean isActive);

    /**
     * Set Command for Invoker
     * @param command Command Object
     */
    void setCommand(INavBarCommand command);

    /** Invoke Nav Bar Function */
    void invoke();
}
