package com.example.praktekkelas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void show_toast(View view)
    {
        EditText edt_fname = (EditText) findViewById(R.id.firstname);
        EditText edt_lname = (EditText) findViewById(R.id.lastname);
        String fullname = edt_fname.getText().toString() + " " + edt_lname.getText().toString();

       if(fullname.equals(" "))
       {
           Toast.makeText(getApplicationContext(),"Please Fill Firstname and lastname"
           ,Toast.LENGTH_SHORT).show();
       }
       else
       {
           Toast.makeText(getApplicationContext(),"Your fullnmame is "+fullname,
                   Toast.LENGTH_SHORT).show();
       }
    }

    public void passing_data(View view)
    {
        EditText edt_fname = (EditText) findViewById(R.id.firstname);
        EditText edt_lname = (EditText) findViewById(R.id.lastname);
        String fname = edt_fname.getText().toString();
        String lname = edt_lname.getText().toString();

        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);

        intent.putExtra("fn_data",fname);
        intent.putExtra("ls_data",lname);

        startActivity(intent);
    }

    public void open_webview(View view)
    {
        Intent intent = new Intent(MainActivity.this, MainActivity3.class);
        startActivity(intent);
    }

    public void materialDesignPage(View view)
    {
        Intent intent = new Intent(MainActivity.this,MainActivity4.class);
        startActivity(intent);
    }

    public void openSQLite(View view)
    {
        Intent intent = new Intent(MainActivity.this,MainActivity6.class);
        startActivity(intent);
    }

    public void calculate(View view)
    {
        int a = 10;
        int b = 10;
        int hasil = 10 * 10;
        System.out.println("Hasil adalah " + hasil);
    }

}