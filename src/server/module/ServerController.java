package server.module;

import server.model.response.ResponseModel;
import server.model.ServerState;
import server.model.request.*;

import java.util.LinkedList;

public class ServerController {

    private ServerService service;

    public ServerController(ServerState state, LinkedList<ServerState> clientList) {
        this.service = new ServerService(state, clientList);
    }

    public ResponseModel choiceService(RequestModel request){

        try{
            if(request instanceof Ping){
                return new ResponseModel(request);
            }else if(request instanceof LoginRequest){
                return service.loginService((LoginRequest)request);
            }else if(request instanceof CheckIdRequest){
                return service.checkIdService((CheckIdRequest)request);
            }else if(request instanceof SignUpRequest){
               return service.signUpService((SignUpRequest)request);
            }else if(request instanceof OpenChattingRequest){
                return service.openChattingService((OpenChattingRequest)request);
            }else if(request instanceof ShowMyUsernameRequest){
                return service.showMyUsernameService((ShowMyUsernameRequest)request);
            }

        }catch (Exception e){
            System.out.println("ServerController:choiceService() : " + e);
            e.printStackTrace();
        }

        return null;
    }

}
