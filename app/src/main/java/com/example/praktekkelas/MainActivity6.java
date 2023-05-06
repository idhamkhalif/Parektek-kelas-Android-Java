package com.example.praktekkelas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity6 extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabaseObj;
    EditText editTextName, editTextPhoneNumber;
    String NameHolder, NumberHolder, SQLiteDataBaseQueryHolder;
    Button EnterData, ButtonDisplayData;
    Boolean EditTextEmptyHold;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        EnterData = (Button) findViewById(R.id.button);
        ButtonDisplayData = (Button) findViewById(R.id.button2);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhone);

        EnterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabaseBuild();
                SQLiteTableBuild();
                CheckEditTextStatus();
                InsertDataIntoSQLiteDatabase();
                EmptyEditTextAfterDataInsert();
            }
        });

        ButtonDisplayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity6.this,display_sqlite_data.class);
                startActivity(intent);
            }
        });
    }
    public void SQLiteDatabaseBuild()
    {
        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME,
                Context.MODE_PRIVATE,null);
    }

    public void SQLiteTableBuild(){
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE_NAME+
                "(" + SQLiteHelper.Table_Column_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + SQLiteHelper.Table_Column_1_Name+ " VARCHAR,"
                + SQLiteHelper.Table_Column_2_PhoneNumber+ " VARCHAR);");
    }

    public void CheckEditTextStatus()
    {
        NameHolder = editTextName.getText().toString();
        NumberHolder = editTextPhoneNumber.getText().toString();

        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(NumberHolder))
        {
            EditTextEmptyHold = false;
        }
        else
        {
            EditTextEmptyHold = true;
        }
    }

    public void InsertDataIntoSQLiteDatabase()
    {
        try {
            if(EditTextEmptyHold == true)
            {
                SQLiteDataBaseQueryHolder =" INSERT INTO " + SQLiteHelper.TABLE_NAME
                +" (name,phone_number) VALUES('"+NameHolder+"', '" + NumberHolder +"');";
                sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
                sqLiteDatabaseObj.close();
                Toast.makeText(this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
                System.out.println(SQLiteDataBaseQueryHolder);
            }
            else
            {
                Toast.makeText(this,"Please Fill Field",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Error",Toast.LENGTH_SHORT).show();
        }
    }

    public void EmptyEditTextAfterDataInsert()
    {
        editTextName.getText().clear();
        editTextPhoneNumber.getText().clear();
    }

}