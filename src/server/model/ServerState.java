package server.model;

import server.module.ServerRepository;

import java.io.*;
import java.net.Socket;

public class ServerState {

    private Socket socket; // TCP 네트워킹 소켓
    private ServerRepository repository; // Database에 접근하는 객체
    private InputStream inputStream; // receive data stream from client
    private OutputStream outputStream; // send data stream to client
    private BufferedReader bufferedReader; // Client로부터 String을 받는 객체
    private PrintWriter printWriter; // Client에게 String를 보내는 객체
    private ObjectInputStream objectInputStream; // Client로부터 데이터를 객체 형태로 받는 객체
    private ObjectOutputStream objectOutputStream; // Client로 데이터를 객체 형태를 보내는 객체
    private boolean isInitialized = false;
    private boolean isDestroyed = false;

    // 통신용 객체들을 Socket을 기반으로 모두 초기화하기
    public void initialize(){
        try{
            if(!isInitialized){
                this.inputStream = socket.getInputStream();
                this.outputStream = socket.getOutputStream();
                this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                this.printWriter = new PrintWriter(new OutputStreamWriter((outputStream)));
                this.objectInputStream = new ObjectInputStream(inputStream);
                this.objectOutputStream = new ObjectOutputStream(outputStream);
                isInitialized = true;
            }else{
                System.out.println("이미 초기화가 되었습니다.");
            }
        }catch(Exception e){
            System.out.println("ServerState-initialize() Error:"+e);
        }
    }

    // 통신 객체들 모두 닫아주기
    public void destroy(){

        try {
            if(!isDestroyed){

                if(bufferedReader != null) { bufferedReader.close(); };
                if(printWriter != null) { printWriter.close();};
                if(objectInputStream != null) { objectInputStream.close(); };
                if(objectOutputStream != null) { objectOutputStream.close(); };
                if(inputStream != null) { inputStream.close();};
                if(outputStream != null) { outputStream.close(); };
                if(socket != null) { socket.close(); };
                isDestroyed = true;
                System.out.println("통신 객체들이 close되었습니다. -- 클라이언트 접속 해제");

            }else{
                System.out.println("이미 모든 통신 객체들이 close되었습니다.");
            }
        } catch (Exception e) {
            System.out.println("ServerState-destroy() Error:"+e);
        }
    }

    /* Getters and Setters */
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ServerRepository getRepository() {
        return repository;
    }

    public void setRepository(ServerRepository repository) {
        this.repository = repository;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }


    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }


    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

}
