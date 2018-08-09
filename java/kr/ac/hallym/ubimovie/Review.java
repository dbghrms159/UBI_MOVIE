package kr.ac.hallym.ubimovie;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Review extends AppCompatActivity  {

    private ListView list;
    private ArrayList<String> reviewList;
    private ArrayAdapter<String> adapter;
    private String review;

    public static Activity Review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Button button1 = (Button) findViewById(R.id.writebutton);
        Intent intent = getIntent();
        review = intent.getExtras().getString("review" );
        reviewList = new ArrayList<String>();

        list = (ListView) findViewById(R.id.ReviewList);
        for(int i = 0; i < review.split("/").length; i+=2 ){
            reviewList.add(review.split("/")[i]);
        }
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,reviewList);
        list.setAdapter(adapter);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Review.this, ReviewWrite.class);
                startActivity(intent);
            }
        });

        Review = Review.this;

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("review",review.split("/")[(position*2)+1] );
                AlertDialog.Builder dialog = new AlertDialog.Builder(Review.this);
                dialog.setTitle(review.split("/")[(position*2)]);
                dialog.setMessage(review.split("/")[(position*2)+1]).setCancelable(true);

                AlertDialog dialog1 = dialog.create();
                dialog.show();
            }
        });
     //   Intent intent = getIntent();
    }

}