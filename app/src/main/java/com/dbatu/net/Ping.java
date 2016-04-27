package com.dbatu.net;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class Ping extends AppCompatActivity {

    public static EditText ping;
    public static Button ping_it;
    public static WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        webView = (WebView) findViewById(R.id.webView);


        ping_it= (Button) findViewById(R.id.ping_it);

        ping_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url = "http://192.168.0.100:80/test.php";
                        HttpClient client = new DefaultHttpClient();

                        try {
                            client.execute(new HttpGet(url));

                        } catch(IOException e) {

                        }

                    }
                });
                thread.start();
                String url = "http://192.168.43.166:80/test.php";

                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                webView.loadUrl(url);
            }
        });


    }

}
