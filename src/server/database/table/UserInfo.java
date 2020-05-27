package server.database.table;

import java.io.Serializable;

public class UserInfo implements Serializable {
    public String id;
    public String password;
    public String username;
    public String birth;
    public String gender;
    public String phoneNumber;

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", birth='" + birth + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
