package com.example.matan.firtst_proyecton;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;

import java.util.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnsort,btnadd,btnremove,btnHelpMain,btnCreateBibliography;
    ListView lvSources;
    Dialog d1, d2,d3;
    ArrayList<Source> SourcesList = new ArrayList<>();
    SourcesArray sa;
    Spinner spLanguages;
    ArrayList<String> allLanguages;
    String[] languages,LanArray;
    EditText etEditName, etEditWriter, etEditYear, etEditLink;
    int editingItem;
    int year = Calendar.getInstance().get(Calendar.YEAR);
    Gson g = new GsonBuilder().setPrettyPrinting().create();
    Button btnSortYear, btnSortABC, btnSortWriter, btnSortLanguage;
    EditText etAddName, etAddWriter, etAddYear, etAddLink;
    ImageButton btnHome;

    Button btnaddSource, btnEditSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnadd = findViewById(R.id.btnadd);
        btnremove = findViewById(R.id.btnremove);
        btnsort = findViewById(R.id.btnsort);
        btnHelpMain = findViewById(R.id.btnHelpMain);
        btnCreateBibliography=findViewById(R.id.btnCreateBibliography);
        btnCreateBibliography.setOnClickListener(this);
        btnHome = findViewById(R.id.btnHome);
        btnHelpMain.setOnClickListener(this);
        btnHome.setOnClickListener(this);
        lvSources = findViewById(R.id.lvSources);
        btnadd.setOnClickListener(this);
        btnremove.setOnClickListener(this);
        btnsort.setOnClickListener(this);
        lvSources.setLongClickable(true);
        lvSources.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                createEditDialog();
                editingItem=position;
                return true;
            }

        });
        Intent i10=getIntent();
        // Creating languages array
        allLanguages = new ArrayList<String>();
        languages = Locale.getISOLanguages();
        ReplaceLanguages(languages);
        LanArray = new String[languages.length];
        for (int i = 0; i < languages.length; i++) {
            Locale loc = new Locale(languages[i]);
            LanArray[i] = loc.getDisplayLanguage();
        }

        //Receiving an updated from another activity


        //Updating the list view with the list of sources
        SharedPreferences s = getPreferences(Context.MODE_PRIVATE);
        String save = s.getString("save","");
        TypeToken typeToken = new TypeToken<ArrayList<Source>>() {};

        ArrayList<Source> tmp = g.fromJson(save, typeToken.getType());
        if (tmp != null) {
            SourcesList = tmp;
        }
        Intent i2 = getIntent();
        Bundle b2 = i2.getBundleExtra("SourcesList");
        ArrayList<Source> temp;
        if (b2 != null) {
            temp = (ArrayList<Source>) b2.getSerializable("SourcesList");
            if (temp != null) {
                //if (temp.isEmpty() == false) {
                SourcesList = temp;

            }
        }
        SourcesArray sa = new SourcesArray(this, SourcesList);
        lvSources.setAdapter(sa);

        // Setting OnClick to the ListView sources which leads to the second Activity
        lvSources.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, Second.class);
                i.putExtra("link", SourcesList.get(position).getLink());
                startActivity(i);
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        String save = g.toJson(SourcesList);
        SharedPreferences s = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();
        editor.putString("save", save);
        editor.commit();

    }

    // Method which brings the most important languages to the top of the list
    public void ReplaceLanguages(String[] LanArray) {
        String replace1 = LanArray[137];
        String replace2 = LanArray[0];
        LanArray[0] = replace1;
        LanArray[137] = replace2;
        replace1 = LanArray[156];
        replace2 = LanArray[1];
        LanArray[1] = replace1;
        LanArray[156] = replace2;
        replace1 = LanArray[197];
        replace2 = LanArray[2];
        LanArray[2] = replace1;
        LanArray[197] = replace2;
        replace1 = LanArray[22];
        replace2 = LanArray[3];
        LanArray[3] = replace1;
        LanArray[22] = replace2;
        replace1 = LanArray[436];
        replace2 = LanArray[4];
        LanArray[4] = replace1;
        LanArray[436] = replace2;
    }



    //Method which create a dialog with sorting_dialog.xml
    public void createSortingDialog() {
        d1 = new Dialog(this);
        d1.setContentView(R.layout.sorting_dialog);
        d1.setTitle("Sort");
        d1.setCancelable(true);

        btnSortABC = d1.findViewById(R.id.btnSortABC);
        btnSortABC.setOnClickListener(this);
        btnSortYear = d1.findViewById(R.id.btnSortYear);
        btnSortYear.setOnClickListener(this);
        btnSortWriter = d1.findViewById(R.id.btnSortWriter);
        btnSortWriter.setOnClickListener(this);
        btnSortLanguage = d1.findViewById(R.id.btnSortLanguage);
        btnSortLanguage.setOnClickListener(this);
        d1.show();
    }

    //Method which create a dialog with add_dialog.xml
    public void createAddDialog() {
        d2 = new Dialog(this);
        d2.setContentView(R.layout.add_dialog);
        d2.setTitle("Add Article");
        d2.setCancelable(true);

        etAddName = d2.findViewById(R.id.etAddName);
        etAddWriter = d2.findViewById(R.id.etAddWriter);
        etAddYear = d2.findViewById(R.id.etAddYear);
        etAddLink = d2.findViewById(R.id.etAddLink);
        btnaddSource = d2.findViewById(R.id.btnAddSource);
        btnaddSource.setOnClickListener(this);


        spLanguages = d2.findViewById(R.id.spLanguages);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, LanArray);
        spLanguages.setAdapter(adapter);
        d2.show();
    }


    //Method which create a dialog edit add_dialog.xml
    public void createEditDialog() {
        d3 = new Dialog(this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(d3.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        d3.setContentView(R.layout.edit_dialog);
        d3.setTitle("Edit Article");
        d3.setCancelable(true);

        etEditName = d3.findViewById(R.id.etEditName);
        etEditWriter = d3.findViewById(R.id.etEditWriter);
        etEditYear = d3.findViewById(R.id.etEditYear);
        etEditLink = d3.findViewById(R.id.etEditLink);
        btnEditSource = d3.findViewById(R.id.btnEditSource);
        btnEditSource.setOnClickListener(this);


        spLanguages = d3.findViewById(R.id.spLanguagesEdit);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, LanArray);
        spLanguages.setAdapter(adapter);
        d3.show();
    }


    @Override
    public void onClick(View v) {
        if (v == btnsort) {
            createSortingDialog();
        }
        if (v == btnSortABC) {
            Collections.sort(SourcesList, Source.NameComp);

            sa = new SourcesArray(this, SourcesList);
            lvSources.setAdapter(sa);

            d1.dismiss();
        }
        if (v == btnSortWriter) {
            Collections.sort(SourcesList, Source.WriterComp);

            sa = new SourcesArray(this, SourcesList);
            lvSources.setAdapter(sa);

            d1.dismiss();
        }
        if (v == btnSortYear) {
            Collections.sort(SourcesList, Source.YearComp);

            sa = new SourcesArray(this, SourcesList);
            lvSources.setAdapter(sa);

            d1.dismiss();
        }
        if (v == btnSortLanguage) {
            Collections.sort(SourcesList, Source.LanguageComp);

            sa = new SourcesArray(this, SourcesList);
            lvSources.setAdapter(sa);

            d1.dismiss();
        }
        if (v == btnadd) {
            createAddDialog();

        }

        //Checking if all EditTexts are not empty and if the link is correct
        if (v == btnaddSource) {
            if (TextUtils.isDigitsOnly(etAddYear.getText().toString()) && etAddYear.getText().toString().length() > 0 && Integer.parseInt(etAddYear.getText().toString()) > 0
                    && etAddName.getText().toString().length() > 0 && etAddWriter.getText().toString().length() > 0 && spLanguages.getSelectedItem().toString().length() > 0 && etAddLink.getText().toString().length() > 0) {
                String st = etAddLink.getText().toString();
                if (st.matches("^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$")) {
                    {
                        if (Integer.parseInt(etAddYear.getText().toString()) <= year) {

                            SourcesList.add(new Source(etAddName.getText().toString(), Integer.parseInt(etAddYear.getText().toString()), etAddWriter.getText().toString(), spLanguages.getSelectedItem().toString(), etAddLink.getText().toString()));
                            sa = new SourcesArray(this, SourcesList);
                            lvSources.setAdapter(sa);
                            d2.dismiss();

                        } else
                            Toast.makeText(MainActivity.this, "Illegal Year", Toast.LENGTH_SHORT).show();
                    }
                    } else
                    Toast.makeText(MainActivity.this, "Illegal Link", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Illegal input", Toast.LENGTH_LONG).show();
        }
        if (v == btnremove) {
            Intent i3 = new Intent(MainActivity.this, Remove.class);
            Bundle b3 = new Bundle();
            b3.putSerializable("SourcesList", SourcesList);
            i3.putExtra("SourcesList", b3);
            startActivity(i3);
        }
        if (v == btnHome) {
            Intent Home = new Intent(MainActivity.this, EnterActivity.class);
            startActivity(Home);
        }
        if (v == btnHelpMain) {
            Intent infoMain = new Intent(MainActivity.this, InformationActivity.class);
            infoMain.putExtra("Uniqid", "From_Activity_Main");
            startActivity(infoMain);
        }
        if(v==btnCreateBibliography)
        {
            LinkedList<String> lL = new LinkedList<>();

            //LinkedList<String> linkedlist = new LinkedList<String>(SourcesList);

            Intent iBibliography = new Intent(MainActivity.this, BibliographyActivity.class);
            Bundle b4 = new Bundle();
            b4.putSerializable("LinkedListBibliography", SourcesList);
            iBibliography.putExtra("LinkedListBibliography", b4);
            startActivity(iBibliography);;

        }

        //Checking which of the EditTexts are not empty and if the link is correct
        if (v == btnEditSource) {
            boolean correctLinkandYear = true;
                if(etEditYear.getText().toString().length() > 0) {
                    if (Integer.parseInt(etEditYear.getText().toString()) <= year) {
                        SourcesList.get(editingItem).setYear(Integer.valueOf(etEditYear.getText().toString()));
                        correctLinkandYear = true;
                    } else {
                        Toast.makeText(MainActivity.this, "Illegal Year", Toast.LENGTH_SHORT).show();
                        correctLinkandYear = false;
                    }

                }
                if (etEditLink.getText().toString().length() > 0) {
                    String st = etEditLink.getText().toString();
                    if (st.matches("^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$")) {
                        SourcesList.get(editingItem).setLink(etEditLink.getText().toString());
                        correctLinkandYear = true;
                    } else {
                        Toast.makeText(MainActivity.this, "Illegal Link", Toast.LENGTH_SHORT).show();
                        correctLinkandYear = false;
                    }
                }
                if (etEditName.getText().toString().length() > 0) {
                    SourcesList.get(editingItem).setSourcename(etEditName.getText().toString());
                }
                if (etEditWriter.getText().toString().length() > 0) {
                    SourcesList.get(editingItem).setWritenBy(etEditWriter.getText().toString());
                }
                if (etEditYear.getText().toString().length() > 0) {
                    SourcesList.get(editingItem).setYear(Integer.valueOf(etEditYear.getText().toString()));
                }
                if (spLanguages.getSelectedItem().toString().length() > 0) {
                    SourcesList.get(editingItem).setLanguage(spLanguages.getSelectedItem().toString());
                }
                if (correctLinkandYear == true)
                    d3.dismiss();
            }
        }
        }



