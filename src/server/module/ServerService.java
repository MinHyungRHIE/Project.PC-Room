package server.module;

import server.config.ProtocolOption;
import server.model.ServerState;
import server.model.User;
import server.model.request.CheckIdRequest;
import server.model.request.LoginRequest;
import server.model.request.RequestModel;
import server.model.request.SignUpRequest;
import server.model.response.ResponseModel;

import java.util.LinkedList;

public class ServerService {

    private ServerState state;
    private ServerRepository repository;
    private LinkedList<ServerState> clientList;

    public ServerService(ServerState state, LinkedList<ServerState> clientList) {
        this.state = state;
        this.repository = state.getRepository();
        this.clientList = clientList;
    }

    public ResponseModel loginService(LoginRequest request) throws Exception{
        boolean isLogined = false;
        ResponseModel response = new ResponseModel();

        if(repository.findUserInfo(request.getId(), request.getPassword())){
            for(ServerState client : clientList){
                if( client.getSession() != null && request.getId().equals(client.getSession().getId())){
                    isLogined = true;
                }
            }

            if(!isLogined){
                this.state.setSession(new User(repository.getUserInfo(request.getId())));
                response.data.put("result","success");
            }else{
                response.data.put("result","blocked");
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
