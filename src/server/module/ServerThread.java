package server.module;

import client.model.ResponseModel;
import server.model.ServerState;
import server.model.communication.CommunicationModel;

public class ServerThread extends Thread{

    private ServerState state;
    private ServerController controller;

    public ServerThread(ServerState state) {
        state.initialize();
        this.state = state;
        this.controller = new ServerController(state.getRepository());
    }

    @Override
    public void run(){
        while(true){
            try {
                ResponseModel response = controller.choiceService((CommunicationModel)state.getObjectInputStream().readObject());
                state.getObjectOutputStream().writeObject(response);
            } catch (Exception e) {
                state.destroy();
                return;
            }
        }
    }
}
