/** One Pig Digit State */
public class OnePinState implements IPinState {
    private IPinStateMachine machine;

    /**
     * Constructor
     * @param  m Reference to State Machine
     */
    public OnePinState(IPinStateMachine m) {
        this.machine = m;
    }

    /**
     * Number Event
     * @param digit Digit pressed
     */    
    public void number(String digit) {
        machine.setStateTwoPinDigits(digit);
    }

    /** Backspace Event */
    public void backspace() {
        machine.setStateNoPinDigits();
    }

    /** Valid Pin Event */
    public void validPin() {

    }

    /** Invalid Pin Event */
    public void invalidPin() {

    }
}