/** Menu Command */
public class NavBarCommand implements INavBarCommand {
    INavBarReceiver receiver;

    /** Execute Command */
    @Override
    public void execute() {
        receiver.doAction();
    }

    /**
     * Set Receiver of Command
     * @param receiver Target Receiver
     */
    @Override
    public void setReceiver(INavBarReceiver receiver) {
        this.receiver = receiver;
    }
    
}
