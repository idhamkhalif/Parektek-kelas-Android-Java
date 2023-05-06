package com.example.praktekkelas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getResources().getText(R.string.pls_back_again), Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = true;
            }
        }, 2000);
    }

    public void write(View view)
    {
        EditText writeData = (EditText) findViewById(R.id.messages_txt);
        String data_txt = writeData.getText().toString();
        TextView textView = (TextView) findViewById(R.id.readText);

        if(!data_txt.isEmpty())
        {
            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            if(!file.exists())
            {
                file.mkdir();
            }
            try {
                File gpxfile = new File(file,"myFile.txt");
                FileWriter writer = new FileWriter(gpxfile);
                writer.append(data_txt); writer.flush(); writer.close();
                textView.setText(read());
                Toast.makeText(this,"Succes Write Data to txt",Toast.LENGTH_SHORT).show();
            }catch (Exception ex){System.out.println("Error is : " + ex);}
        }
    }

    public String read()
    {
        File fileEvents = new File(String.valueOf(Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DOCUMENTS+"/myFile.txt")));
       StringBuilder text = new StringBuilder();
       try {
           BufferedReader br = new BufferedReader(new FileReader(fileEvents));
           String line;
           while ((line = br.readLine()) != null)
           {
               text.append(line); text.append(' ');
           }
           br.close();
       }
       catch (Exception ex)
       {System.out.println("Error : " + ex);}
       String result = text.toString();return result;
    }
}