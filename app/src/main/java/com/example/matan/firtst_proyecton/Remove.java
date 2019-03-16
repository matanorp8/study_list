package com.example.matan.firtst_proyecton;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Remove extends AppCompatActivity implements View.OnClickListener {

    Button btnRemoveBack;
    ListView lvRemove;
    ArrayList<Source> temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        lvRemove = (ListView)findViewById(R.id.lvRemove);
        btnRemoveBack= (Button)findViewById(R.id.btnRemoveBack);
        btnRemoveBack.setOnClickListener(this);

        //Updating the ListView from the list in MainActivity
        Intent i3 = getIntent();
        Bundle b3 = i3.getBundleExtra("SourcesList");
        temp = (ArrayList<Source>) b3.getSerializable("SourcesList");
        String[] Names = new String[temp.size()];
        for (int i = 0; i < temp.size(); i++)
        {
            Names[i] = temp.get(i).getSourceName();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Names);
        lvRemove.setAdapter(adapter);

        lvRemove.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                creatingAlertDialog(position,view);

                }
        });
    }
    //Creating Alert Dialog to ask if the user want to delete or not
    public void creatingAlertDialog(final int position, final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove Article");
        builder.setMessage("Do you want to remove this article?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                temp.remove(position);
                Intent i3 = new Intent(Remove.this, MainActivity.class);
                Bundle b3 = new Bundle();
                b3.putSerializable("SourcesList", temp);
                i3.putExtra("SourcesList", b3);
                startActivity(i3);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(Remove.this, "Article wasn't removed ", Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar.make(view,"Article wasn't removed",Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    @Override
    public void onClick(View v) {
        if (v==btnRemoveBack)
        {
            finish();


        }
    }


}
