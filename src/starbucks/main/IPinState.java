/**
 * Pin State Interfaces
 */
public interface IPinState {
    /**
     * Number Event
     * @param digit Digit/Key Pressed
     */
    void number(String digit);

    /** Backspace Event */
    void backspace();

    /** Valid Pin Event */
    void validPin();

    /** Invalid Pin Event */
    void invalidPin();
}
