package server.module;

import server.model.response.ResponseModel;
import server.model.ServerState;
import server.model.request.*;

import java.util.LinkedList;

public class ServerController {

    private ServerService service;

    public ServerController(ServerRepository repository, LinkedList<ServerState> clientList) {
        this.service = new ServerService(repository, clientList);
    }

    public ResponseModel choiceService(RequestModel request){

        try{
            if(request instanceof LoginRequest){
                return service.loginService((LoginRequest)request);
            }else if(request instanceof CheckIdRequest){
                return service.checkIdService((CheckIdRequest)request);
            }else if(request instanceof SignUpRequest){
               return service.signUpService((SignUpRequest)request);
            }else if(request instanceof ChattingRequest){

            }

        }catch (Exception e){
            System.out.println(e);
        }

        return null;
    }

}
