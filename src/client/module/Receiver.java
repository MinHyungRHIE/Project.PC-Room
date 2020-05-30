package client.module;

import server.model.response.ResponseModel;

import java.io.ObjectInputStream;

public class Receiver extends Thread{

    ObjectInputStream responseData;
    Connector connector;

    Receiver(ObjectInputStream responseData, Connector connector){
        this.responseData = responseData;
        this.connector = connector;
    }

    @Override
    public void run(){
        try{
            while(true){
                ResponseModel response = (ResponseModel)responseData.readObject();
                System.out.println("<서버 응답>");
                connector.responseSignal(response); }
        }catch (Exception e){
            System.out.println("Recevier Thread Errer-run():" + e);
            connector.destoryAll();
            e.printStackTrace();
        }
    }
}
