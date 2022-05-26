/** No Pin Digits State */
public class NoPinState implements IPinState {
    private IPinStateMachine machine;

    /**
     * Constructor
     * @param  m Reference to State Machine
     */
    public NoPinState(IPinStateMachine m) {
        this.machine = m;
    }

    /**
     * Number Event
     * @param digit Digit pressed
     */
    public void number(String digit) {
        machine.setStateOnePinDigit(digit);
    }

    /** Backspace Event */
    public void backspace() {

    }

    /** Valid Pin Event */
    public void validPin() {

    }

    /** Invalid Pin Event */
    public void invalidPin() {
        
    }

}