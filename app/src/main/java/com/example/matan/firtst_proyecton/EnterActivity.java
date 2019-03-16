package com.example.matan.firtst_proyecton;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class EnterActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnExit,btnHelp,btnNew;
    ConstraintLayout enterLayout;
    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        btnExit=findViewById(R.id.btnExit);
        btnHelp=findViewById(R.id.btnHelp);
        btnNew=findViewById(R.id.btnNew);
        btnExit.setOnClickListener(this);
        btnHelp.setOnClickListener(this);
        btnNew.setOnClickListener(this);

        enterLayout=findViewById(R.id.enterLayout);
        animationDrawable=(AnimationDrawable) enterLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();

    }

    @Override
    public void onClick(View v) {
        if(v==btnExit)
        {
            finish();
            moveTaskToBack(true);
        }
        if(v==btnNew)
        {
            Intent i10 = new Intent(EnterActivity.this, MainActivity.class);
            startActivity(i10);
        }
        if(v==btnHelp)
        {
            Intent infoEnter = new Intent(EnterActivity.this, InformationActivity.class);
            infoEnter.putExtra("Uniqid","From_Activity_Enter");
            startActivity(infoEnter);
        }
    }
}
