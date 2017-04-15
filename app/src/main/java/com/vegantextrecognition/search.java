package com.vegantextrecognition;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.InputStream;

public class search extends Activity {
    TextView textView;
    TextView vegan;
    TextView notvegan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        textView = (TextView) findViewById(R.id.textView2);
        vegan = (TextView) findViewById(R.id.vegan);
        notvegan = (TextView) findViewById(R.id.notvegan);

        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                InputStream is = null;
                String text = "";
                try {
                    is = getAssets().open("recognizedTEXT.txt");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    text = new String(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String[] arr = text.split(",");
                String ss = "";
                int i = 0, isNotVegan = 0;
                while (i < arr.length && isNotVegan == 0) {
                    ss = arr[i];
                    if (ss == textView.getText().toString()) {
                        vegan.setBackgroundColor(Color.parseColor("#7b7b7b"));
                        notvegan.setBackgroundColor(Color.parseColor("#ff0000"));
                        isNotVegan = 1;
                    } else {
                        vegan.setBackgroundColor(Color.parseColor("#00FA75"));
                        notvegan.setBackgroundColor(Color.parseColor("#7b7b7b"));
                    }
                    i++;


                }
            }


        });


    }
}
