package client.module;

import javax.swing.*;
import java.lang.reflect.Constructor;
import java.util.LinkedList;

public class GuiManager {

    private LinkedList<JFrame> currentGui;

    public GuiManager() {
        this.currentGui = new LinkedList<JFrame>();
    }

    public void GuiGenerator(GuiProfile profile, Connector connector) throws Exception{

        connector.ping();

        int mode = profile.getMode();

        if(mode == GuiManagerMode.NEW_WINDOW_AND_CLOSE_ALL_OLD_WINDOW){
            while(!currentGui.isEmpty()){
                currentGui.peek().dispose();
                currentGui.pop();
            }
            generate(profile,connector);
        }else if(mode == GuiManagerMode.NEW_WINDOW_AND_STAY_ALL_OLD_WINDOW){
            generate(profile,connector);
        }else if(mode == GuiManagerMode.CLOSE_THIS_WINDOW){
            profile.getTarget().dispose();
            currentGui.remove(profile.getTarget());
        }
    }

    private void generate(GuiProfile profile, Connector connector) throws Exception{
        Class<?> gui = Class.forName(profile.getGuiName());
        Constructor<?> createGui = gui.getConstructor(Connector.class);
        currentGui.add((JFrame)createGui.newInstance(connector));
    }

}
