package server.module;

import server.database.table.Letter;
import server.database.table.UserInfo;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.HashSet;

public class test {
    public static void main(String[] args) throws Exception{
        String databasePath = "./src/server/database/Letter";
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(databasePath));
        System.out.println(((HashSet<Letter>)ois.readObject()).size());
    }
}
