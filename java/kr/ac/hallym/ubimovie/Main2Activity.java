package kr.ac.hallym.ubimovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ListView listview ;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.genre);
        listview.setAdapter(adapter);
        Intent in = getIntent();
        String text = in.getExtras().getString("list");

        for(int i = 0; i < 10; i++) {
            // 아이템 추가.
            adapter.addItem(text.split("- ")[(i*2)+1], text.split("- ")[(i*2)]);
        }

    }
}
