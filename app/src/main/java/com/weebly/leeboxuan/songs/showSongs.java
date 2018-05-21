package com.weebly.leeboxuan.songs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class showSongs extends AppCompatActivity {

    ArrayList<Song> al;
    ListView lv;
    Button btnShowFiveStar;
    ArrayAdapter aa;
    Spinner spinner;
    ArrayAdapter<String> spinneraa;
    ArrayList<String> spinnerlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_songs);
        DBHelper db = new DBHelper(showSongs.this);
        spinnerlist = new ArrayList<String>();

        spinner = (Spinner) findViewById(R.id.spinner);


        for (int a = 0; a < db.getAllSong().size(); a++) {
            spinnerlist.add("" + db.getAllSongs().get(a).getYear());

        }
        spinneraa = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerlist);

        spinner.setAdapter(spinneraa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DBHelper db = new DBHelper(showSongs.this);
                al.clear();
                al.addAll(db.getAllSongs(Integer.parseInt(spinner.getSelectedItem().toString())));
                lv.setAdapter(aa);
                db.close();


                Toast.makeText(showSongs.this, spinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();





            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        btnShowFiveStar = (Button) findViewById(R.id.btnShowFiveStar);
        lv = (ListView) findViewById(R.id.lv);
        al = new ArrayList<Song>();

        al.clear();
        al.addAll(db.getAllSongs());
        db.close();

        aa = new songArrayAdapter(showSongs.this, R.layout.row, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent moveToEditActivity = new Intent(showSongs.this, modifySongs.class);

                Song data = al.get(position);

                moveToEditActivity.putExtra("data", data);
                startActivityForResult(moveToEditActivity, 9);

            }
        });

        btnShowFiveStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(showSongs.this);
                al.clear();
                for (int i = 0; i < db.getAllSong().size(); i++){
                    int stars = db.getAllSongs().get(i).getStars();
                    if (stars == 5){
                        al.add(db.getAllSongs().get(i));
                    }
                }
                db.close();

                lv.setAdapter(aa);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            lv = (ListView)findViewById(R.id.lv);
            al = new ArrayList<Song>();
            DBHelper dbh = new DBHelper(showSongs.this);
            al.clear();
            al.addAll(dbh.getAllSongs());
            dbh.close();
            aa = new songArrayAdapter(this, R.layout.row, al);

            lv.setAdapter(aa);
        }
    }
}
