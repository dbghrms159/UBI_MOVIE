package kr.ac.hallym.ubimovie;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by yuhogeun on 2017. 12. 4..
 */

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> list = new ArrayList<ListViewItem>();
    private ImageView iconImageView;
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public synchronized View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        iconImageView = (ImageView) convertView.findViewById(R.id.imageView4) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView3) ;


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final ListViewItem listViewItem = list.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        Thread thread = new Thread() {
            public synchronized void run(){

                try {
                    URL url;
                    URLConnection con;
                    BufferedInputStream in;



                        url = new URL(listViewItem.getIcon());
                        con = url.openConnection();
                        con.connect();
                        in = new BufferedInputStream(con.getInputStream());
                        iconImageView.setImageBitmap(BitmapFactory.decodeStream(in));
                        in.close();



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        if(!listViewItem.getIcon().equals("x"))
            thread.start();

        try {
            Thread.sleep(300);
        }catch (Exception e){}
        //iconImageView.setImageDrawable(listViewItem.getIcon());
        titleTextView.setText(listViewItem.getTitle());


        return convertView;

    }
    public void addItem(String icon, String title) {
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setTitle(title);

        list.add(item);
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
