package server.model.request;

import client.ui.GUI;

import java.io.Serializable;

public class ShowMyUsernameRequest  implements RequestModel, Serializable {
    @Override
    public String getRequestType() {
        return RequestModel.SHOW_MY_USERNAME;
    }

    @Override
    public String getResponseTarget() {
        return GUI.MAIN;
    }
}
