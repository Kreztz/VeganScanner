package com.vegantextrecognition;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class home extends AppCompatActivity {
    ImageButton scan;
    ImageButton toscan;
    ImageButton toabout;
    ImageButton tosearch;
    ImageButton tosuggest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loadMenu();

        scan = (ImageButton) findViewById(R.id.scan);
        scan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), scan.class);
                startActivity(i);
            }
        });


    }



    void loadMenu()
    {
        tosearch = (ImageButton) findViewById(R.id.toSearch);
        tosearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), search.class);
                startActivity(i);



            }
        });
        toabout= (ImageButton) findViewById(R.id.toAbout);
        toabout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), about.class);
                startActivity(i);

            }
        });

        tosuggest = (ImageButton) findViewById(R.id.toSuggest);
        tosuggest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), suggest.class);
                startActivity(i);

            }
        });
        toscan = (ImageButton) findViewById(R.id.toScan);
        toscan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), home.class);
                startActivity(i);

            }
        });
    }
}
