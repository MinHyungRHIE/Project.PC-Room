package server.module;

import client.model.ResponseModel;
import server.model.communication.SignUpRequest;

public class ServerService {

    private ServerRepository repository;

    public ServerService(ServerRepository repository) {
        this.repository = repository;
    }

    // 회원가입 서비스
    public ResponseModel signUpService(SignUpRequest request) throws Exception{

        //서비스에서는 ID가 있는지 없는지 비교해주고


        ResponseModel response = new ResponseModel();
        if(repository.updateUserInfo(request)){
            response.data.put("result", "회원가입 성공!");
        }else{
            response.data.put("result", "회원가입 실패!");
        }

        return response;
    }
}
