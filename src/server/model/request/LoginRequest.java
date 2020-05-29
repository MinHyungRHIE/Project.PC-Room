package server.model.request;

import java.io.Serializable;

public class LoginRequest implements RequestModel, Serializable {
    private String id;
    private String password;

    public String getId() {
        return id;
    }

    public LoginRequest setId(String id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginRequest setPassword(String password) {
        this.password = password;
        return this;
    }

}
