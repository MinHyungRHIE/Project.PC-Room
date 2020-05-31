package practice;

import client.module.Connector;
import client.ui.LoginGUI;
import server.database.table.Letter;
import server.database.table.UserInfo;
import server.model.User;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class test {

    int num;
    String str;

    public test(String str){
        this.str = str;
    }

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


//        LinkedList test2 = new LinkedList();
//        test2.add("A");
//
//        System.out.println(test2);
//        test2.add("B");
//        System.out.println(test2);
//        System.out.println(test2.peek());
//        test2.pop();
//        System.out.println(test2);

//        LinkedList<JFrame> currentGui = new LinkedList<JFrame>();
//
//        Connector forTest = new Connector();
//        currentGui.add(new LoginGUI(forTest));
//        Thread.sleep(1000);
//        currentGui.add(new LoginGUI(forTest));
//        Thread.sleep(1000);
//        currentGui.add(new LoginGUI(forTest));
//        Thread.sleep(1000);
//        currentGui.add(new LoginGUI(forTest));
//        Thread.sleep(1000);
//        currentGui.add(new LoginGUI(forTest));
//        Thread.sleep(1000);
//
//        System.out.println(currentGui.size());
//
//        Thread.sleep(2000);
//
////        while(currentGui.size() > 1){
////            currentGui.peek().dispose();
////            currentGui.pop();
////            Thread.sleep(1000);
////        }
//
//        while(currentGui.size() != 0){
//            currentGui.peek().dispose();
//            currentGui.pop();
//        }
//
//        System.out.println(currentGui.size());


        LinkedList<User> test = new LinkedList<>();
        System.out.println(test.remove("dfd"));
    }

    @Override
    public String toString() {
        return "test : " + str;
    }
}
