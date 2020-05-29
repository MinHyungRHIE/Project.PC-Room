package server.module;

import server.database.table.Letter;
import server.database.table.UserInfo;
import server.model.request.SignUpRequest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;

public class ServerRepository {
    private final String dbUserInfo = "./src/server/database/UserInfo";
    private final String dbLetter = "./src/server/database/Letter";

    /**
     * 서버를 키기 전에 DB를 체크한다.
     * 만일 DB가 없다면 추가한다.
     */
    public void checkDatabaseState() throws Exception{
        System.out.println("=====데이터베이스 검사중=====");

        if(!checkDatabsePath(dbUserInfo)){
            createDatabaseTable(dbUserInfo);
        }

        if(!checkDatabsePath(dbLetter)){
            createDatabaseTable(dbLetter);
        }

    }

    private boolean checkDatabsePath(String databasePath){
        try{
            ObjectInputStream check = new ObjectInputStream(new FileInputStream(databasePath));
            System.out.println("check status - ok : " + databasePath);
            check.close();
            return true;
        }catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }

    private boolean createDatabaseTable(String databasePath){
        try {
            ObjectOutputStream create = new ObjectOutputStream(new FileOutputStream(databasePath));
            String databaseName = databasePath.split("/")[databasePath.split("/").length -1];

            if(databaseName.equals("UserInfo")){
                HashSet<UserInfo> tableData  =new HashSet<UserInfo>();
                create.writeObject(tableData);
                System.out.println("createDatbase - ok : " + databasePath);
                return true;
            }
            if(databaseName.equals("Letter")){
                HashSet<Letter> tableData  =new HashSet<Letter>();
                create.writeObject(tableData);
                System.out.println("createDatbase - ok : " + databasePath);
                return true;
            }

            return false;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    /**
     * 만일 true로 return하면 로그인 성공.
     * false로 return하면 로그인 실패.
     */
    public boolean findUserInfo(String id, String password) throws Exception{
        ObjectInputStream fromDatabase = new ObjectInputStream(new FileInputStream(dbUserInfo));
        HashSet<UserInfo> data = (HashSet<UserInfo>)fromDatabase.readObject();

        Iterator<UserInfo> itr = data.iterator();

        while(itr.hasNext()){
            UserInfo tmp = itr.next();
            if(tmp.id.equals(id) && tmp.password.equals(password)){
                return true;
            }
        }

        return false;
    }

    /**
     * 만일 true로 return하면 database에 해당 ID가 있는거고, 이때는 해당 ID를 쓸 수 없다.
     * false로 return하면 database에 해당 ID가 없다는 것이니, 써도된다.
     */
    public boolean findUserInfo(String id) throws Exception{
        ObjectInputStream fromDatabase = new ObjectInputStream(new FileInputStream(dbUserInfo));
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

    /**
     * 회원가입 요청을 보낸 유저의 정보를 DB에 저장한다.
     */
    public boolean updateUserInfo(SignUpRequest request) throws Exception{
        ObjectInputStream fromDatabase = new ObjectInputStream(new FileInputStream(dbUserInfo));
        HashSet<UserInfo> data = (HashSet<UserInfo>)fromDatabase.readObject();

        UserInfo toSave = new UserInfo();
        toSave.id = request.getId();
        toSave.password = request.getPassword();
        toSave.birth = request.getBirth();
        toSave.gender = request.getGender();
        toSave.phoneNumber = request.getPhoneNumber();
        toSave.username = request.getUsername();

        data.add(toSave);

        ObjectOutputStream toDatabase = new ObjectOutputStream(new FileOutputStream(dbUserInfo));
        toDatabase.writeObject(data);

        return true;
    }



}
