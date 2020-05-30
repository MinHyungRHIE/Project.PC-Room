package client;

import client.module.Connector;
import client.module.GuiManagerMode;
import client.module.GuiProfile;
import client.ui.GUI;

public class Client {

    public static void main(String[] args) {
        Connector connector = new Connector();
        connector.connectToServer();
        connector.createGuiManager();
        GuiProfile profile = new GuiProfile();
        profile.setGuiName(GUI.LOGIN).setMode(GuiManagerMode.NEW_WINDOW_AND_CLOSE_ALL_OLD_WINDOW);
        connector.guiManager(profile);
    }
}
