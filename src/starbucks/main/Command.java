/** Command */
public class Command implements ICommand {
    private ICommandReceiver receiver;

    @Override
    public void execute() {
        receiver.onClick();
    }

    @Override
    public void setReceiver(ICommandReceiver receiver) {
        this.receiver = receiver;
    }
    
}
