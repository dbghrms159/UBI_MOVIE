package kr.ac.hallym.ubimovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ReviewWrite extends AppCompatActivity {

    private String title, body;
    private EditText titles, bodys;
    private String send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);
        Button button2 = (Button) findViewById(R.id.button2);
        titles = (EditText) findViewById(R.id.titleText);
        bodys = (EditText) findViewById(R.id.bodyText);

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                title = titles.getText().toString();
                body = bodys.getText().toString();

                send = title+"/"+body;
                Review review = (Review) Review.Review;
                review.finish();

                ClientTest client = new ClientTest(send);
                client.start();
                try {

                    Thread.sleep(100);
                }catch (Exception e){

                }

                client = new ClientTest("5");
                client.start();
                finish();
            }
        });


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
                Socket socket = new Socket("210.115.230.145",port);

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(send);
                out.flush();

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                getMessage = (String) in.readObject();

                //Log.i("test","server test : " + getMessage);

                socket.close();

            }catch(Exception e){

            }
            if(send.equals("5")) {

                Intent nextsin = new Intent(ReviewWrite.this, Review.class);
                nextsin.putExtra("review", getMessage);
                startActivity(nextsin);
            }

        }

    }

}
