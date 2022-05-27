import processing.core.PApplet;

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

    private INavBarCommand displayMyCards;
    private INavBarCommand displayPayments;
    private INavBarCommand displayRewards;
    private INavBarCommand displayStores;
    private INavBarCommand displaySettings;

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
        displayMyCards  = new NavBarCommand();
        displayPayments = new NavBarCommand();
        displayRewards  = new NavBarCommand();
        displayStores         = new NavBarCommand();
        displaySettings = new NavBarCommand();

        // set receiver
        displayMyCards.setReceiver(
            new INavBarReceiver() {
                /** Command Action */
                public void doAction() {
                    frame.setCurrentScreen(myCardsScreen);
                }
            }
        );
        displayPayments.setReceiver(
            new INavBarReceiver() {
                /** Command Action */
                public void doAction() {
                    frame.setCurrentScreen(paymentsScreen);
                }
            }
        );
        displayRewards.setReceiver(
            new INavBarReceiver() {
                /** Command Action */
                public void doAction() {
                    frame.setCurrentScreen(rewardsScreen);
                }
            }
        );
        displayStores.setReceiver(
            new INavBarReceiver() {
                /** Command Action */
                public void doAction() {
                    frame.setCurrentScreen(storesScreen);
                }
            }
        );
        displaySettings.setReceiver(
            new INavBarReceiver() {
                /** Command Action */
                public void doAction() {
                    frame.setCurrentScreen(settingsScreen);
                }
            }
        );

        // configure nav bar
        frame.addNavBarOption(
            Constants.NAV_LABEL_CARDS,
            Constants.NAV_ICON_CARDS,
            Constants.NAV_ACTIVE_ICON_CARDS,
            displayMyCards
        );
        frame.addNavBarOption(
            Constants.NAV_LABEL_PAYMENTS,
            Constants.NAV_ICON_PAYMENTS,
            Constants.NAV_ACTIVE_ICON_PAYMENTS,
            displayPayments
        );
        frame.addNavBarOption(
            Constants.NAV_LABEL_REWARDS,
            Constants.NAV_ICON_REWARDS,
            Constants.NAV_ACTIVE_ICON_REWARDS,
            displayRewards
        );
        frame.addNavBarOption(
            Constants.NAV_LABEL_STORES,
            Constants.NAV_ICON_STORES,
            Constants.NAV_ACTIVE_ICON_STORES,
            displayStores
        );
        frame.addNavBarOption(
            Constants.NAV_LABEL_SETTINGS,
            Constants.NAV_ICON_SETTINGS,
            Constants.NAV_ACTIVE_ICON_SETTINGS,
            displaySettings
        );
    }
    
}
