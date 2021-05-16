package com.example.entertainers_troupe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;

public class Bill extends AppCompatActivity implements View.OnClickListener {
Button btbrowse,btgo;
TextView tv20;
RadioButton goo,acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        btbrowse=(Button)findViewById(R.id.browse);
        btgo=(Button)findViewById(R.id.go);
        goo=(RadioButton) findViewById(R.id.radioButton3);
        acc=(RadioButton) findViewById(R.id.radioButton4);
        tv20=(TextView) findViewById(R.id.textView20);


        btbrowse.setOnClickListener(this);
        btgo.setOnClickListener(this);
    }
    String path, atype, fname, attach="", attatch1;
    byte[] byteArray = null;

    void showfilechooser(int string) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //getting all types of files

        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), string);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                ////
                Uri uri = data.getData();

                try {
                    path = FileUtils.getPath(this, uri);

                    File fil = new File(path);
                    float fln = (float) (fil.length() / 1024);
                    atype = path.substring(path.lastIndexOf(".") + 1);


                    fname = path.substring(path.lastIndexOf("/") + 1);
                    tv20.setTextColor(Color.BLACK);
                    tv20.setText(fname);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                try {

                    File imgFile = new File(path);

                    if (imgFile.exists()) {

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                        im.setImageBitmap(myBitmap);

                    }


                    File file = new File(path);
                    byte[] b = new byte[8192];
                    Log.d("bytes read", "bytes read");

                    InputStream inputStream = new FileInputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    int bytesRead = 0;

                    while ((bytesRead = inputStream.read(b)) != -1) {
                        bos.write(b, 0, bytesRead);
                    }
                    byteArray = bos.toByteArray();

                    String str = Base64.encodeToString(byteArray, Base64.NO_WRAP);
                    attach = str;


                } catch (Exception e) {
                    Toast.makeText(this, "String :" + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }

                ///

            }
        }

    }

    String payment="";
    @Override
    public void onClick(View view) {
        if (view==btbrowse)
        {
          showfilechooser(1);
        }
        else if(view==btgo)
        {


            if(goo.isChecked())
            {
                payment="google pay";
            }
            else if(acc.isChecked()){

                payment="account";
            }

            if (attach.length()==0){
                tv20.setError("Browse Bill Image");
            }
            else if(payment.length()==0)
            {
                tv20.setError("Choose Payment method");
            }
            else {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("method", payment);
                ed.putString("attach", attach);
                ed.commit();

                Intent i = new Intent(getApplicationContext(), Customer_troup_booking.class);
                startActivity(i);
            }

        }
    }
}
