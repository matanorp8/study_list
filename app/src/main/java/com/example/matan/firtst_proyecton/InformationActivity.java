package com.example.matan.firtst_proyecton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBackInfo;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        btnBackInfo = findViewById(R.id.btnBackInfo);
        btnBackInfo.setOnClickListener(this);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        tvInfo.setText("Hello user, if you want to study for an exam do an assignment and you want to use information sources from the internet, this app is for you. This app will help you to store sources from the internet and organize them. If you press the “add” button, you will be able to write the name, link, language ,year, and the author name of the article you want to store. This will add it to a list where you can see all the sites you store. If you want to sort them you can press the “sort” button and select what kind of sort you want: the article name by abc, the author name by abc, the year (from newest to oldest) and by language. If you want to remove a site from your list, press the “remove” button and select the site you want to remove. After this the site will be removed. If you press the “make bibliography” button it will create a bibliography of all the sites from the list and sort them by abc of the writer. This will look like this: author name, article name, link, year, language. Hope you will enjoy the app.");
        tvInfo.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void onClick(View v) {
        if (v == btnBackInfo) {
            Intent intent = this.getIntent();
            if (intent != null) {
                String strdata = intent.getExtras().getString("Uniqid");
                if (strdata.equals("From_Activity_Enter")) {
                    Intent QuitHelp = new Intent(InformationActivity.this, EnterActivity.class);
                    startActivity(QuitHelp);
                }
                if (strdata.equals("From_Activity_Main")) {
                    Intent QuitHelp = new Intent(InformationActivity.this, MainActivity.class);
                    startActivity(QuitHelp);
                }
            }
        }
    }
}
