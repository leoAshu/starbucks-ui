import processing.core.PApplet;

public class PinEntryMachine implements IPinStateMachine, IKeyPadObserver {
    private PApplet starbucks;
    private String pin;
    private boolean authenticated;
    
    // pin machine states
    private NoPinState noPinState;
    private OnePinState onePinState;
    private TwoPinState twoPinState;
    private ThreePinState threePinState;
    private FourPinState fourPinState;
    private FivePinState fivePinState;
    private SixPinState sixPinState;

    // current state
    private IPinState currentState;

    // pin captured so far
    private String d1;
    private String d2;
    private String d3;
    private String d4;
    private String d5;
    private String d6;

    /**
     * Constructor - Set Up State Objects
     * and Set Initial State to "PIN 0"
     */
    public PinEntryMachine(PApplet starbucks) {
        this.starbucks = starbucks;
        noPinState = new NoPinState(this);
        onePinState = new OnePinState(this);
        twoPinState = new TwoPinState(this);
        threePinState = new ThreePinState(this);
        fourPinState = new FourPinState(this);
        fivePinState = new FivePinState(this);
        sixPinState = new SixPinState(this);

        d1 = "";
        d2 = "";
        d3 = "";
        d4 = "";
        d5 = "";
        d6 = "";

        currentState = noPinState;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public void number(String digit) {
        currentState.number(digit);
    }

    @Override
    public void backspace() {
        currentState.backspace();
    }

    @Override
    public void validPin() {
        currentState.validPin();
    }

    @Override
    public void invalidPin() {
        currentState.invalidPin();
    }

    @Override
    public void setStateNoPinDigits() {
        currentState = noPinState;
        d1 = "";
        d2 = "";
        d3 = "";
        d4 = "";
        d5 = "";
        d6 = "";
    }

    @Override
    public void setStateOnePinDigit(String digit) {
        currentState = onePinState;
        
        if(digit != null)
            d1 = digit;
        else {
            d2 = "";
            d3 = "";
            d4 = "";
            d5 = "";
            d6 = "";
        }
    }

    @Override
    public void setStateTwoPinDigits(String digit) {
        currentState = twoPinState;
        
        if(digit != null)
            d2 = digit;
        else {
            d3 = "";
            d4 = "";
            d5 = "";
            d6 = "";
        }
    }

    @Override
    public void setStateThreePinDigits(String digit) {
        currentState = threePinState;
        
        if(digit != null)
            d3 = digit;
        else {
            d4 = "";
            d5 = "";
            d6 = "";
        }
    }

    @Override
    public void setStateFourPinDigits(String digit) {
        currentState = fourPinState;
        
        if(digit != null)
            d4 = digit;
        else {
            d5 = "";
            d6 = "";
        }
        
        if(pin.length() == Constants.FOUR_PIN_OPTION)
            authenticate();
    }

    @Override
    public void setStateFivePinDigits(String digit) {
        currentState = fivePinState;

        if(digit != null)
            d5 = digit;
        else
            d6 = "";
    }

    @Override
    public void setStateSixPinDigits(String digit) {
        currentState = sixPinState;
        
        if(digit != null) {
            d6 = digit;
            authenticate();
        }
    }

    @Override
    public void keyEventUpdate(int keyCount, String key) {
        if(key.equalsIgnoreCase("X"))
            backspace();
        else
            number(key);
    }

    private void authenticate() {
        if(pin.equals(d1+d2+d3+d4+d5+d6))
            authenticated = true;
        else
            currentState.invalidPin();
    }
    
    public void display() {
        starbucks.textSize(14);
        starbucks.text("d1: "+d1, 30, 102);
        starbucks.text("d2: "+d2, 90, 102);
        starbucks.text("d3: "+d3, 150, 102);
        starbucks.text("d4: "+d4, 210, 102);
        starbucks.text("d5: "+d5, 270, 102);
        starbucks.text("d6: "+d6, 330, 102);
        starbucks.text("" + authenticated, starbucks.width/2, 120);
    }
}
