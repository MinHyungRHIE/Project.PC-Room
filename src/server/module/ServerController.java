package server.module;

import client.model.ResponseModel;
import server.model.communication.ChattingRequest;
import server.model.communication.CommunicationModel;
import server.model.communication.LoginRequest;
import server.model.communication.SignUpRequest;

public class ServerController {

    private ServerService service;

    public ServerController(ServerRepository repository) {
        this.service = new ServerService(repository);
    }

    public ResponseModel choiceService(CommunicationModel request){

        try{
            if(request instanceof LoginRequest){

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
