/** To Pin Digits State */
public class TwoPinDigitsState implements IPinState {
    IPinStateMachine machine;

    /**
     * Constructor
     * @param  m Reference to State Machine
     */    
    public TwoPinDigitsState(IPinStateMachine m) {
        this.machine = m;
    }

    /**
     * Number Event
     * @param digit Digit pressed
     */
    public void number(String digit) {
        machine.setStateThreePinDigits(digit);
    }

    /** Backspace Event */
    public void backspace() {
        machine.setStateOnePinDigit(null);
    }

    /** Valid Pin Event */
    public void validPin() {

    }

    /** Invalid Pin Event */
    public void invalidPin() {

    }

}