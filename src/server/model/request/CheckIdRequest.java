package server.model.request;

import java.io.Serializable;

public class CheckIdRequest implements RequestModel, Serializable {
    private String id;

    public String getId() {
        return id;
    }

    public CheckIdRequest setId(String id) {
        this.id = id;
        return this;
    }
}
