import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

public class SettingsScreen extends Screen {
    
    public SettingsScreen(PApplet starbucks) {
        super(starbucks, Constants.SCREEN_BG_LIGHT);
        addSubComponent(new AppBar(starbucks, name()));
        setUpScreen();
    }

    @Override
    public void display() {
        super.display();
    }
    
    @Override
    public String name() {
        return "Settings";
    }

    private void setUpScreen() {
        List<ListItem> items = new ArrayList<ListItem>();

        ListItem item;
        ICommand command;
        
        command = new Command();
        command.setReceiver(new ICommandReceiver() {
            @Override
            public void onClick() {
                AppController.getAppController(starbucks)
                    .setScreen(AppController.SCREENS.ADD_CARD);
            }
        });
        item = new ListItem("Add Card", Constants.ICON_ARROW);
        item.setCommand(command);
        items.add(item);

        item = new ListItem("Delete Card", Constants.ICON_ARROW);
        items.add(item);

        item = new ListItem("Billings", Constants.ICON_ARROW);
        items.add(item);

        item = new ListItem("Passcode", Constants.ICON_ARROW);
        items.add(item);

        addSubComponent(new ListView(starbucks, 119, items));
    }
}
