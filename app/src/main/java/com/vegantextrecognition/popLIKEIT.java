package com.vegantextrecognition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class popLIKEIT extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_likeit);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        Button yesbutton = (Button) findViewById(R.id.yesbutton);
        yesbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


               /* Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"veganscanner.fb@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                i.putExtra(Intent.EXTRA_TEXT   , "A user did like the app");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                }
                */
                Toast toast = Toast.makeText(popLIKEIT.this, "Thank you for your feedback", Toast.LENGTH_LONG);
                toast.show();

                finish();
            }
        });


        Button nobutton = (Button) findViewById(R.id.nobutton);
        nobutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               /* Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"veganscanner.fb@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                i.putExtra(Intent.EXTRA_TEXT   , "A user did not like the app");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                }*/
                Toast toast = Toast.makeText(popLIKEIT.this,"Thank you for your feedback",Toast.LENGTH_LONG);
                toast.show();

                finish();
            }
        });
    }

}
