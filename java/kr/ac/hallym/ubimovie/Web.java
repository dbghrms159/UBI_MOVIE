package kr.ac.hallym.ubimovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Scanner;

public class Web extends AppCompatActivity {
    private WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        String link =  intent.getExtras().getString("link");

        web = (WebView) findViewById(R.id.webview);
        web.setWebViewClient(new WebViewClient());

        WebSettings set = web.getSettings();
        set.setJavaScriptEnabled(true);

        web.loadUrl(link);
    }
}
