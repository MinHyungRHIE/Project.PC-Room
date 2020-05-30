package server.model.request;

import server.model.response.ResponseModel;

import java.io.Serializable;

public class ChattingRequest implements RequestModel, Serializable {
    @Override
    public String getRequestType() {
        return RequestModel.CHATTING;
    }

    @Override
    public String getResponseTarget() {
        return null;
    }

    private String msg;

}
