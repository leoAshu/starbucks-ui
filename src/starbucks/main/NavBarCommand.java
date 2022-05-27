/** Menu Command */
public class NavBarCommand implements INavBarCommand {
    String label;
    String iconPath;
    String activeIconPath;
    INavBarReceiver receiver;

    public NavBarCommand(String label, String iconPath, String activeIconPath) {
        this.label = label;
        this.iconPath = iconPath;
        this.activeIconPath = activeIconPath;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getIconPath() {
        return iconPath;
    }

    @Override
    public String getActiveIconPath() {
        return activeIconPath;
    }

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
