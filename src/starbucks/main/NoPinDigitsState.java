/** No Pin Digits State */
public class NoPinDigitsState implements IPinState {
    private IPinStateMachine machine;

    /**
     * Constructor
     * @param  m Reference to State Machine
     */
    public NoPinDigitsState(IPinStateMachine m) {
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