package server.model.request;

import java.io.Serializable;

public interface RequestModel {
    public final static String PING = "PING";
    public final static String LOGIN = "LOGIN";
    public final static String CHECK_ID = "CHECK_ID";
    public final static String SIGNUP = "SIGNUP";
    public final static String SHOW_MY_USERNAME = "SHOW_MY_USERNAME";
    public final static String CHATTING = "CHATTING";


    public abstract String getRequestType();
    public abstract String getResponseTarget();
}
