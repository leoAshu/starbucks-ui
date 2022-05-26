/** For Pin Digits State */
public class SixPinDigitsState implements IPinState {
    IPinStateMachine machine;

    /**
     * Constructor
     * @param  m Reference to State Machine
     */
    public SixPinDigitsState(IPinStateMachine m) {
        this.machine = m;
    }

    /**
     * Digit Entry Event
     * @param digit Digit Value
     */
    public void number(String digit) {
        return;
    }

    /**
     * Backspace Event
     */
    public void backspace() {
        machine.setStateFivePinDigits(null);
    }

    /**
     * Valid Pin Event
     */
    public void validPin() {
        return;
    }

    /**
     * Invalid Pin Event
     */
    public void invalidPin() {
        machine.setStateNoPinDigits();
    }

}