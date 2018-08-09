package kr.ac.hallym.ubimovie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by yuhogeun on 2017. 12. 2..
 */

public class Loding extends Activity {
    Intent next;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            Thread.sleep(4000);
        }catch (InterruptedException e){

        }
        ClientTest client = new ClientTest("0");
        client.start();
    }

    class ClientTest  extends Thread{
        String send;
        String getMessage;

        public ClientTest(String send){
            this.send = send;

        }
        public void run(){

            try{

                int port = 8008;
                Socket socket = new Socket("server_ip",port);

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(send);
                out.flush();

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                getMessage = (String) in.readObject();

                //Log.i("test","server test : " + getMessage);

                socket.close();

            }catch(Exception e){
                e.printStackTrace();
                Log.i("taraw","dastq");
            }
            next =  new Intent(Loding.this,MainActivity.class);
            next.putExtra("key", getMessage);

            startActivity(next);
            finish();
        }

    }
}