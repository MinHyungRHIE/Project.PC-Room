package server.module;

import server.model.response.ResponseModel;
import server.model.ServerState;
import server.model.request.CheckIdRequest;
import server.model.request.LoginRequest;
import server.model.request.SignUpRequest;

import java.util.LinkedList;

public class ServerService {

    private ServerRepository repository;
    private LinkedList<ServerState> clientList;

    public ServerService(ServerRepository repository, LinkedList<ServerState> clientList) {
        this.repository = repository;
        this.clientList = clientList;
    }

    public ResponseModel loginService(LoginRequest request) throws Exception{
        ResponseModel response = new ResponseModel();

        if(repository.findUserInfo(request.getId(), request.getPassword())){
            response.data.put("result","success");

            for(ServerState client : clientList){
                if(request.getId().equals(client.getSession().getId())){
                    response.data.put("result","blocked");
                }
            }
        }else{
            response.data.put("result","fail");
        }

        return response;
    }

    // 아이디 중복 확인 서비스
    public ResponseModel checkIdService(CheckIdRequest request) throws Exception{
        ResponseModel response = new ResponseModel();
        if(repository.findUserInfo(request.getId())){
            response.data.put("result",new Boolean(true));
        }else{
            response.data.put("result",new Boolean(false));
        }
        return response;
    }

    // 회원가입 서비스
    public ResponseModel signUpService(SignUpRequest request) throws Exception{
        ResponseModel response = new ResponseModel();
        if(repository.updateUserInfo(request)){
            response.data.put("result", "회원가입 성공!");
        }else{
            response.data.put("result", "회원가입 실패!");
        }
        return response;
    }
}
