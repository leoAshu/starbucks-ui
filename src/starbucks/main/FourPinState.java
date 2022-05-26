/** For Pin Digits State */
public class FourPinState implements IPinState {
    IPinStateMachine machine;

    /**
     * Constructor
     * @param  m Reference to State Machine
     */
    public FourPinState(IPinStateMachine m) {
        this.machine = m;
    }

    /**
     * Digit Entry Event
     * @param digit Digit Value
     */
    public void number(String digit) {
        machine.setStateFivePinDigits(digit);
    }

    /**
     * Backspace Event
     */
    public void backspace() {
        machine.setStateThreePinDigits(null);
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