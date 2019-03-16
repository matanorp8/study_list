package com.example.matan.firtst_proyecton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Second extends AppCompatActivity implements View.OnClickListener {

    Intent i;
    WebView wvSource;
    EditText multiText;
    Button btnBack;
    Button btnSite;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        i = getIntent();
        wvSource = findViewById(R.id.wvSource);
        multiText = findViewById(R.id.multiText);
        btnBack = findViewById(R.id.btnBack);
        btnSite = findViewById(R.id.btnSite);
        btnBack.setOnClickListener(this);
        btnSite.setOnClickListener(this);
        url=i.getStringExtra("link");
        wvSource.getSettings().setJavaScriptEnabled(true);
        wvSource.setWebViewClient(new WebViewClient());
        wvSource.loadUrl(url);

    }

    @Override
    public void onClick(View v) {
        if (v == btnBack) {
            finish();
        }
        if (v == btnSite) {
            Intent browserIntent = new Intent();
            browserIntent.setAction(Intent.ACTION_VIEW);
            browserIntent.addCategory(Intent.CATEGORY_BROWSABLE);
            browserIntent.setData(Uri.parse(url));
            startActivity(browserIntent);

        }
    }

}
