package com.vegantextrecognition;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class listNOTVEGAN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notvegan);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        ListView listNV = (ListView) findViewById(R.id.listNotVeganLV);
        String[] det = (String[]) getIntent().getSerializableExtra("details");
        ArrayAdapter<String> array = new ArrayAdapter<String>(listNOTVEGAN.this,R.layout.item,det);
        listNV.setAdapter(array);
    }

}
