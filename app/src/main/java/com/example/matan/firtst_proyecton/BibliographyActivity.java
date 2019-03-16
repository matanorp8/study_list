package com.example.matan.firtst_proyecton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class BibliographyActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Source> temp;
    TextView tvBibliography;
    Button btnBibliographyBack;
    int counter=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bibliography);
        tvBibliography=findViewById(R.id.tvBibliography);
        btnBibliographyBack=findViewById(R.id.btnBibliographyBack);
        btnBibliographyBack.setOnClickListener(this);
        tvBibliography.setMovementMethod(new ScrollingMovementMethod());
        tvBibliography.setTextIsSelectable(true);



        //Updating the ListView from the list in MainActivity
        Intent i4 = getIntent();
        Bundle b4 = i4.getBundleExtra("LinkedListBibliography");
        temp = (ArrayList<Source>) b4.getSerializable("LinkedListBibliography");
        Collections.sort(temp, Source.WriterComp);
        LinkedList<Source> linkedListBibliography = convertALtoLL(temp);
        for (int i = 0; i < linkedListBibliography.size(); i++)
        {
          tvBibliography.setText(tvBibliography.getText()+String.valueOf(counter)+". "+ linkedListBibliography.get(i).getWritenBy()+ ", " + linkedListBibliography.get(i).getSourceName() +", "+linkedListBibliography.get(i).getLink()+ ", "+String.valueOf(linkedListBibliography.get(i).getYear())+", " + linkedListBibliography.get(i).getLanguage()+"\n\n");
            counter++;
        }
        //for (int i=0;i<list)

    }

    public static <T> LinkedList<T> convertALtoLL(ArrayList<T> aL)
    {

        // Create an empty LinkedList
        LinkedList<T> lL = new LinkedList<>();

        // Iterate through the aL
        for (T t : aL) {

            // Add each element into the lL
            lL.add(t);
        }

        // Return the converted LinkedList
        return lL;
    }

    @Override
    public void onClick(View v) {
        if(v==btnBibliographyBack)
        {
            finish();
        }
    }
}
