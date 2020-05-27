package practice;

import java.io.*;

public class FileSystemDatabase {
    public static void main(String[] args) {

        try {

            String filename = "testSample";

            SampleDataSet sds = new SampleDataSet("test", "1234");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(sds);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            SampleDataSet get = (SampleDataSet)ois.readObject();
            System.out.println(get);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
