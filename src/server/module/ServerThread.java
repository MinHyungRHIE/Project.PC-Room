package server.module;

import server.config.ProtocolOption;
import server.model.response.ResponseModel;
import server.model.ServerState;
import server.model.request.RequestModel;

import java.io.IOException;
import java.util.LinkedList;

public class ServerThread extends Thread{

    private ServerState state;
    private ServerController controller;
    private LinkedList<ServerState> clientList;

    public ServerThread(ServerState state, LinkedList<ServerState> clientList) {
        state.initialize();
        this.state = state;
        this.controller = new ServerController(state, clientList);
        this.clientList = clientList;
        clientList.add(this.state);
    }

    @Override
    public void run(){
        while(true){
            try {
                followProtocol(controller.choiceService((RequestModel)state.getObjectInputStream().readObject()));
            } catch (Exception e) {
                System.out.println("ServerThread:run() : " + e);
                e.printStackTrace();
                state.destroy();
                clientList.remove(this.state);
                System.out.println("clientList["+clientList.size()+"] : "+clientList);
                return;
            }
        }
    }

    public void followProtocol(ResponseModel response) throws IOException {
        int protocol = response.protocol;

        if(protocol == ProtocolOption.SINGLE){
            state.getObjectOutputStream().writeObject(response);
            return;
        }

        synchronized (clientList){
            if(protocol == ProtocolOption.BROADCAST){
                for(ServerState client : clientList){
                    client.getObjectOutputStream().writeObject(response);
                }
                return;
            }

            if(protocol == ProtocolOption.SPECIFIC){

                return;
            }
        }
    }

}
