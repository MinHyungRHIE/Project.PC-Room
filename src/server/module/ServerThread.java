package server.module;

import server.config.ProtocolOption;
import server.model.ChattingRoom;
import server.model.request.BroadcastExitUserRequest;
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

                ResponseModel response = new ResponseModel(new BroadcastExitUserRequest());
                response.data.put("senderId",state.getSession().getId());
                response.data.put("senderUsername",state.getSession().getUsername());
                response.data.put("msg","님과 연결이 끊겼습니다.");

                try{
                    for(ServerState client : clientList){
                        if(client != this.state){
                            client.getObjectOutputStream().writeObject(response);
                        }
                    }
                }catch (Exception e2){
                    System.out.println("유저 에러 공지 실패 : " + e2);
                    e2.printStackTrace();
                }

                System.out.println("clientList["+clientList.size()+"] : "+clientList);
                for(ChattingRoom chattingRoom : state.getChattingRoomList()){
                    if(chattingRoom.getRoomType() == ChattingRoom.OPEN_CHATTING){
                        System.out.println("클라이언트 유저 오픈 채팅방 접속, 현재 오픈채팅방 유저 수 : " + chattingRoom.getParticipants().size());
                    }
                }
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
