package com.vegantextrecognition;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class scan extends AppCompatActivity {

    List<String> thisIsWhy;
    SurfaceView cameraView;
    TextView textView;
    TextView textView2;
    ImageButton likeitornot;
    ImageButton stopscan;
    Button details;
    CameraSource cameraSource;
    final int RequestCameraPermissionID = 1001;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode)
        {
            case RequestCameraPermissionID:
            {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    {
                        return;
                    }
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisIsWhy=new ArrayList<String>();

        setContentView(R.layout.activity_scan);
        details = (Button) findViewById(R.id.details);
        details.setVisibility(View.INVISIBLE);
        details.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        listNOTVEGAN.class);
                String[] listdiali = thisIsWhy.toArray(new String[thisIsWhy.size()]);
                i.putExtra("details",listdiali);
                startActivity(i);

            }
        });
        likeitornot = (ImageButton) findViewById(R.id.likeitornot);
        likeitornot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), popLIKEIT.class);
                startActivity(i);

            }
        });




        cameraView = (SurfaceView) findViewById(R.id.surface_view);
        textView = (TextView) findViewById(R.id.textView3);
        textView2 = (TextView) findViewById(R.id.textView4);
        textView.setBackgroundColor(Color.parseColor("#7b7b7b"));
        textView2.setBackgroundColor(Color.parseColor("#7b7b7b"));

        stopscan = (ImageButton) findViewById(R.id.leavescan);
        stopscan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                thisIsWhy = null;
                textView.setBackgroundColor(Color.parseColor("#7b7b7b"));
                textView2.setBackgroundColor(Color.parseColor("#7b7b7b"));
                Intent i = new Intent(getApplicationContext(),
                        home.class);
                startActivity(i);

            }
        });

        final TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            Log.w("scan", "Detector dependencies are not available");
        } else {
            cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(30)
                    .setAutoFocusEnabled(true)
                    .build();

            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                        {
                            ActivityCompat.requestPermissions(scan.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    RequestCameraPermissionID);
                            return;
                        }
                        cameraSource.start(cameraView.getHolder());

                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    cameraSource.stop();
                }
            });

            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                int isNotVegan =0;

                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {
                    final SparseArray<TextBlock> items = detections.getDetectedItems();








                    if(items.size() != 0)
                    {
                        textView.post(new Runnable() {
                                          @Override
                                          public void run() {



                                              StringBuilder stringBuilder = new StringBuilder();
                                              for(int i=0;i< items.size();i++)
                                              {
                                                  TextBlock item = items.valueAt(i);
                                                  stringBuilder.append(item.getValue());
                                                  stringBuilder.append("\n");

                                              }

                                              InputStream is = null;
                                              String text = "";
                                              try {
                                                  is = getAssets().open("recognizedTEXT.txt");
                                                  int size = is.available();
                                                  byte[] buffer = new byte[size];
                                                  is.read(buffer);
                                                  text = new String(buffer);
                                                  is.close();
                                              } catch (IOException e) {
                                                  e.printStackTrace();
                                              }

                                              String[] arr = text.split(",");
                                              String ss="";

                                              for(int i=0;i<arr.length;i++){

                                                  ss = arr[i];
                                                  int pos= stringBuilder.toString().indexOf(ss);
                                                  if(pos>=0)
                                                  {
                                                      isNotVegan =1;
                                                      if(!kaynadeja(thisIsWhy,ss))
                                                      {
                                                         thisIsWhy.add(ss);
                                                      }

                                                  }
                                              }

                                              if(isNotVegan ==0)
                                              {
                                                  textView.setBackgroundColor(Color.parseColor("#00FA75"));
                                                  textView2.setBackgroundColor(Color.parseColor("#7b7b7b"));
                                              }
                                              else
                                              {
                                                  textView.setBackgroundColor(Color.parseColor("#7b7b7b"));
                                                  textView2.setBackgroundColor(Color.parseColor("#ff0000"));

                                                  details.setVisibility(View.VISIBLE);
                                              }





















                                /*String ss = "";
                                int i=0,out=0;
                                while (i<arr.length && out==0) {
                                    ss = arr[i];
                                    int pos= stringBuilder.toString().indexOf(ss);

                                    if(pos>=0)
                                    {textView.setBackgroundColor(Color.parseColor("#7b7b7b"));
                                     textView2.setBackgroundColor(Color.parseColor("#ff0000"));
                                        out=1;
                                    }
                                    else
                                    {
                                       textView.setBackgroundColor(Color.parseColor("#00FA75"));
                                       textView2.setBackgroundColor(Color.parseColor("#7b7b7b"));
                                    }
                                     i++;
                                }*/







                                          }
                                      }
                        );
                    }
                }
            });
        }



    }



    public boolean kaynadeja(List<String> a,String b)
    {
        for(String c : a)
        {
            if(c.equals(b))
            {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
