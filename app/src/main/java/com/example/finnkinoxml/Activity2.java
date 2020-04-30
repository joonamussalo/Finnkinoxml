package com.example.finnkinoxml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    private static final String TAG = "Activity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        ListView list = (ListView) findViewById(R.id.listview);
        Log.d(TAG, "onCreate: Started.");
        Intent intent = getIntent();
        ArrayList<String> elokuvalista = intent.getStringArrayListExtra("elokuvat");
        System.out.println(elokuvalista.get(2));

        ArrayAdapter adapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,elokuvalista);
        list.setAdapter(adapter);

    }
}
