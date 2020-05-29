package server.model.response;

import server.config.ProtocolOption;

import java.io.Serializable;
import java.util.HashMap;

public class ResponseModel implements Serializable {
    public HashMap<String,Object> data = new HashMap<String, Object>();
    /**
     * single : one-to-one통신, 요청을 보낸 client에게만 응답 [default]
     * broadcast :  broadcasting, 모든 client들에게 응답
     * specific : 특정 client들에게만 응답
     */
    public int protocol = ProtocolOption.SINGLE;

}
