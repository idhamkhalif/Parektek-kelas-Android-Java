package com.example.praktekkelas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class display_sqlite_data extends AppCompatActivity {
    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter;
    ListView listView;

    ArrayList<String> ID_Array;
    ArrayList<String> NAME_ARRAY;
    ArrayList<String> PHONE_NUMBER_ARRAY;

    ArrayList<String> ListViewClickItemArray = new ArrayList<>();
    String TempHolder;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sqlite_data);

        listView = (ListView) findViewById(R.id.ListViewDatas);
        ID_Array = new ArrayList<String>();
        NAME_ARRAY = new ArrayList<String>();
        PHONE_NUMBER_ARRAY = new ArrayList<String>();
        sqLiteHelper = new SQLiteHelper(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),show_single_activity.class);
                intent.putExtra("ListViewClickedItemValue", ListViewClickItemArray.get(position).
                        toString());
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume()
    {
        ShowSQLiteDBData();
        super.onResume();
    }

    @SuppressLint("Range")
    public void ShowSQLiteDBData()
    {
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_NAME+"",null);

        ID_Array.clear();
        NAME_ARRAY.clear();
        PHONE_NUMBER_ARRAY.clear();

        if(cursor.moveToFirst()) {
            do {
                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));
                ListViewClickItemArray.add
                        (cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));
                NAME_ARRAY.add
                        (cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));
                PHONE_NUMBER_ARRAY.add
                        (cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_PhoneNumber)));
            }while(cursor.moveToNext());
        }

        listAdapter = new com.example.praktekkelas.ListAdapter(display_sqlite_data.this,
                ID_Array,
                NAME_ARRAY,
                PHONE_NUMBER_ARRAY
        );
        listView.setAdapter(listAdapter);
        cursor.close();
    }

}