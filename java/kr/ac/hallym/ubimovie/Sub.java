package kr.ac.hallym.ubimovie;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Sub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        ListView listview ;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.subItem);
        listview.setAdapter(adapter);
        Intent in = getIntent();
        String text = in.getExtras().getString("movie");

        for(int i = 0; i < 10; i++) {
            // 아이템 추가.
            adapter.addItem(text.split("- ")[(i*2)+1], text.split("- ")[(i*2)]);
        }
    }
}
