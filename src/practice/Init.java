package practice;

import server.database.table.UserInfo;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.*;
import java.util.Date;
import java.util.HashSet;

public class Init {
    public static void main(String[] args) throws Exception{

//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/server/database/UserInfo"));
//        HashSet<UserInfo> hs = new HashSet<>();
//        UserInfo userInfo1 = new UserInfo();
//        userInfo1.id = "first";
//        userInfo1.password = "1234";
//        userInfo1.username = "one";
//        userInfo1.birth = "2020-05-28";
//        userInfo1.gender = "male";
//        userInfo1.phoneNumber = "010-1423-4035";
//        UserInfo userInfo2 = new UserInfo();
//        userInfo2.id = "second";
//        userInfo2.password = "1234";
//        userInfo2.username = "two";
//        userInfo2.birth = "2020-05-28";
//        userInfo2.gender = "male";
//        userInfo2.phoneNumber = "010-1423-4035";
//        UserInfo userInfo3 = new UserInfo();
//        userInfo3.id = "third";
//        userInfo3.password = "1234";
//        userInfo3.username = "three";
//        userInfo3.birth = "2020-05-28";
//        userInfo3.gender = "male";
//        userInfo3.phoneNumber = "010-1423-4035";
//        hs.add(userInfo1);
//        hs.add(userInfo2);
//        hs.add(userInfo3);
//        System.out.println(hs);
//        oos.writeObject(hs);
//        oos.close();

        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.of("Asia/Seoul"));
        System.out.println(ldt);
        System.out.println(ldt.toInstant(ZoneOffset.of("+09:00")).toEpochMilli());

        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.of("Asia/Seoul"));
        System.out.println(zdt);
        System.out.println(zdt.toInstant().toEpochMilli());

    }
}
