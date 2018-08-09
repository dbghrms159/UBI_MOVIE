package kr.ac.hallym.ubimovie;

import android.graphics.drawable.Drawable;

/**
 * Created by yuhogeun on 2017. 12. 4..
 */

public class ListViewItem {
    private String drawable;
    private String title;

    public void setIcon(String drawable){
        this.drawable = drawable;
    }

    public void setTitle(String title){
        this.title = title;
    }


    public String getIcon(){
        return drawable ;
    }

    public String getTitle(){
        return title;
    }

}
