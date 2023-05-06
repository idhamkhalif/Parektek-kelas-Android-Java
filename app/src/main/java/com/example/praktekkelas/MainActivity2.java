package com.example.praktekkelas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView fname_txt = (TextView)findViewById(R.id.lname);
        TextView lname_txt = (TextView) findViewById(R.id.fname);

        Intent intent = getIntent();
        String str_fname = intent.getStringExtra("fn_data");
        String str_lname = intent.getStringExtra("ls_data");

        fname_txt.setText(str_fname);
        lname_txt.setText(str_lname);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://www.google.com");
    }

    public void back(View view)
    {
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed()
    {
        if(doubleBackToExitPressedOnce)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this,getResources().getText(R.string.pls_back_again),Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = true;
            }
        },2000);

    }
}