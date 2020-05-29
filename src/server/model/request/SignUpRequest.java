package server.model.request;

import java.io.Serializable;

public class SignUpRequest implements RequestModel, Serializable {
    private String id;
    private String password;
    private String username;
    private String birth;
    private String gender;
    private String phoneNumber;

    public String getId() {
        return id;
    }

    public SignUpRequest setId(String id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SignUpRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public SignUpRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getBirth() {
        return birth;
    }

    public SignUpRequest setBirth(String birth) {
        this.birth = birth;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public SignUpRequest setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SignUpRequest setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
