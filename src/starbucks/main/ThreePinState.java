/** Three Pin Digit State */
public class ThreePinState implements IPinState {
    IPinStateMachine machine;

    /**
     * Constructor
     * @param  m Reference to State Machine
     */
    public ThreePinState(IPinStateMachine m) {
        this.machine = m;
    }

    /**
     * Number Event
     * @param digit Digit pressed
     */
    public void number(String digit) {
        machine.setStateFourPinDigits(digit);
    }

    /** Backspace Event */
    public void backspace() {
        machine.setStateTwoPinDigits(null);
    }

    /** Valid Pin Event */
    public void validPin() {

    }

    /** Invlid Pin Event */
    public void invalidPin() {

    }

}