/** Three Pin Digit State */
public class FivePinDigitsState implements IPinState {
    IPinStateMachine machine;

    /**
     * Constructor
     * @param  m Reference to State Machine
     */
    public FivePinDigitsState(IPinStateMachine m) {
        this.machine = m;
    }

    /**
     * Number Event
     * @param digit Digit pressed
     */
    public void number(String digit) {
        machine.setStateSixPinDigits(digit);
    }

    /** Backspace Event */
    public void backspace() {
        machine.setStateFourPinDigits(null);
    }

    /** Valid Pin Event */
    public void validPin() {

    }

    /** Invlid Pin Event */
    public void invalidPin() {

    }

}