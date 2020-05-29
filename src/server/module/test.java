package server.module;

import client.module.Connector;
import client.ui.LoginGUI;
import server.database.table.Letter;
import server.database.table.UserInfo;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class test {
    public static void main(String[] args) throws Exception{
//        String databasePath = "./src/server/database/Letter";
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(databasePath));
//        System.out.println(((HashSet<Letter>)ois.readObject()).size());

//        HashMap<String, String> hm = new HashMap<>();
//        hm.put("result", "success");
//        hm.put("result", "blocked");
//        System.out.println(hm);

//        Connector connector = new Connector();
//        JFrame test =  new LoginGUI(connector);
//        while(test.isShowing()){
//            System.out.println(test.isShowing());
//            Thread.sleep(1000);
//            System.out.println("dispose()");
//            test.dispose();
//            System.out.println(test.isShowing());
//            System.out.println("마무리");
//        }


        LinkedList test2 = new LinkedList();
        test obj = new test();
        test2.add(obj);
        System.out.println(test2);
        test2.remove("test");
        System.out.println(test2);

    }

    @Override
    public String toString() {
        return "test";
    }
}
