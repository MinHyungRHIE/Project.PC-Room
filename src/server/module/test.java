package server.module;

import server.database.table.UserInfo;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashSet;

public class test {
    public static void main(String[] args) throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/server/database/UserInfo"));
        HashSet<UserInfo> hs = (HashSet<UserInfo>)ois.readObject();
        System.out.println(hs.size());
        System.out.println(hs);
        ois.close();
    }
}
