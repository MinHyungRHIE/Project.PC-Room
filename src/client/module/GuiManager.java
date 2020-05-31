package client.module;

import client.ui.GUI;
import client.ui.LoginGUI;
import client.ui.MainGUI;
import client.ui.SignUpGUI;
import server.model.request.RequestModel;
import server.model.response.ResponseModel;

import javax.swing.*;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.LinkedList;

public class GuiManager {

    private LinkedList<JFrame> currentGui;

    public GuiManager() {
        this.currentGui = new LinkedList<JFrame>();
    }

    public void guiGenerator(GuiProfile profile, Connector connector) throws Exception{

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

    public void guiAccessor(ResponseModel response){

        for(JFrame frame : currentGui){
            GUI gui = (GUI)frame;

            if(gui.getGuiName().equals(response.responseTarget)){

                String type = response.requestType;
                HashMap<String,Object> data = response.data;

                if(type.equals(RequestModel.LOGIN)){
                    ((LoginGUI)gui).loginResult(data);
                }

                if(type.equals(RequestModel.CHECK_ID)){
                    ((SignUpGUI)gui).checkIdResult(data);

                }

                if(type.equals((RequestModel.SIGNUP))){
                    ((SignUpGUI)gui).signUpResult(data);
                }

                if(type.equals((RequestModel.OPEN_CHATTING))){
                    ((MainGUI)gui).openChattingResult(data);
                }

                if(type.equals((RequestModel.SHOW_MY_USERNAME))){
                    ((MainGUI)gui).showMyUsernameResult(data);
                }
            }
        }
    }

}
