import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

/**
 * Main App Controller Class
 */
public class AppController implements IApp {
    private PApplet starbucks;
    private static AppController theController;

    private IScreen myCardsScreen;
    private IScreen paymentsScreen;
    private IScreen rewardsScreen;
    private IScreen storesScreen;
    private IScreen settingsScreen;

    private IFrame frame;
    

    private AppController(PApplet starbucks) {
        this.starbucks = starbucks;
    }

    public synchronized static AppController getAppController(PApplet starbucks) {
        if(theController == null)
            return getNewAppController(starbucks);
        return theController;
    }

    public synchronized static AppController getNewAppController(PApplet starbucks) {
        theController = new AppController(starbucks);
        theController.init();
        return theController;
    }

    @Override
    public void touch(int x, int y) {
        frame.touch(x, y);
    }

    @Override
    public void release() {
        frame.release();
    }

    @Override
    public void display() {
        frame.display();
    }

    private void init() {
        
        myCardsScreen = new MyCardsScreen(starbucks);
        paymentsScreen = new PaymentsScreen(starbucks);
        rewardsScreen = new RewardsScreen(starbucks);
        storesScreen = new StoresScreen(starbucks);
        settingsScreen = new SettingsScreen(starbucks);

        frame = new Frame(starbucks, myCardsScreen);

        // setup command pattern
        INavBarCommand command;
        List<INavBarCommand> commands = new ArrayList<INavBarCommand>();
        
        // Cards option
        command = new NavBarCommand(
            Constants.NAV_LABEL_CARDS,
            Constants.NAV_ICON_CARDS,
            Constants.NAV_ACTIVE_ICON_CARDS
        );
        // set receiver
        command.setReceiver(
            new INavBarReceiver() {
                /** Command Action */
                public void doAction() {
                    frame.setCurrentScreen(myCardsScreen);
                }
            }
        );
        // add command
        commands.add(command);

        // define command for Payments option
        command = new NavBarCommand(
            Constants.NAV_LABEL_PAYMENTS,
            Constants.NAV_ICON_PAYMENTS,
            Constants.NAV_ACTIVE_ICON_PAYMENTS
        );
        // set receiver
        command.setReceiver(
            new INavBarReceiver() {
                /** Command Action */
                public void doAction() {
                    frame.setCurrentScreen(paymentsScreen);
                }
            }
        );
        // add command
        commands.add(command);

        // Rewards option
        command = new NavBarCommand(
            Constants.NAV_LABEL_REWARDS,
            Constants.NAV_ICON_REWARDS,
            Constants.NAV_ACTIVE_ICON_REWARDS
        );
        // set receiver
        command.setReceiver(
            new INavBarReceiver() {
                /** Command Action */
                public void doAction() {
                    frame.setCurrentScreen(rewardsScreen);
                }
            }
        );
        // add command
        commands.add(command);

        // Stores option
        command = new NavBarCommand(
            Constants.NAV_LABEL_STORES,
            Constants.NAV_ICON_STORES,
            Constants.NAV_ACTIVE_ICON_STORES
        );
        // set receiver
        command.setReceiver(
            new INavBarReceiver() {
                /** Command Action */
                public void doAction() {
                    frame.setCurrentScreen(storesScreen);
                }
            }
        );
        // add command
        commands.add(command);

        // Settings option
        command = new NavBarCommand(
            Constants.NAV_LABEL_SETTINGS,
            Constants.NAV_ICON_SETTINGS,
            Constants.NAV_ACTIVE_ICON_SETTINGS
        );
        // set receiver
        command.setReceiver(
            new INavBarReceiver() {
                /** Command Action */
                public void doAction() {
                    frame.setCurrentScreen(settingsScreen);
                }
            }
        );
        // add command
        commands.add(command);
        
        frame.setupNavBar(commands);
    }

    public void showOverlay() {
        frame.showOverlay();
    }

    public void hideOverlay() {
        frame.hideOverlay();
    }

    public void addOptions(List<Button> options) {
        if(frame != null)
            frame.addOptionsToOverlay(options);
    }
    
}
