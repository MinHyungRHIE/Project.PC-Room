package server.module;

import server.database.table.UserInfo;
import server.model.communication.SignUpRequest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;

public class ServerRepository {

    /**
     * 만일 true로 return하면 database에 해당 ID가 있는거고, 이때는 해당 ID를 쓸 수 없다.
     * false로 return하면 database에 해당 ID가 없다는 것이니, 써도된다.
     */
    public boolean findUserInfo(String id) throws Exception{
        ObjectInputStream fromDatabase = new ObjectInputStream(new FileInputStream("./src/server/database/UserInfo"));
        HashSet<UserInfo> data = (HashSet<UserInfo>)fromDatabase.readObject();

        Iterator<UserInfo> itr = data.iterator();

        while(itr.hasNext()){
            UserInfo tmp = itr.next();
            if(tmp.id.equals(id)){
                return true;
            }
        }

        return false;
    }

    public boolean updateUserInfo(SignUpRequest request) throws Exception{
        ObjectInputStream fromDatabase = new ObjectInputStream(new FileInputStream("./src/server/database/UserInfo"));
        HashSet<UserInfo> data = (HashSet<UserInfo>)fromDatabase.readObject();

        UserInfo toSave = new UserInfo();
        toSave.id = request.getId();
        toSave.password = request.getPassword();
        toSave.birth = request.getBirth();
        toSave.gender = request.getGender();
        toSave.phoneNumber = request.getPhoneNumber();
        toSave.username = request.getUsername();

        data.add(toSave);

        ObjectOutputStream toDatabase = new ObjectOutputStream(new FileOutputStream("./src/server/database/UserInfo"));
        toDatabase.writeObject(data);

        return true;
    }



}
