package com.vegantextrecognition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class suggest extends Activity {
    ImageButton toscan;
    ImageButton toabout;
    ImageButton tosearch;
    ImageButton tosuggest;


    private EditText editTextSubject;
    private EditText editTextMessage;

    private Button buttonSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        loadMenu();
        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                try {
                    GMailSender sender = new GMailSender("veganscanner.botsender@gmail.com", "vspassword");
                    sender.sendMail(editTextSubject.getText().toString().trim(),
                            editTextMessage.getText().toString().trim(),
                            "veganscanner.botsender@gmail.com",
                            "veganscanner.fb@gmail.com");
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }

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
