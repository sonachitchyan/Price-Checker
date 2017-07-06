package com.example.asus.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class List_Activity extends AppCompatActivity {
    private Adapter adapter;
    private RecyclerView recyclerView;
    private List<Data> dataList;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson = new Gson();
    private Button zero, back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView= (RecyclerView) findViewById(R.id.rec);
        zero = (Button) findViewById(R.id.zero_2);
        back= (Button) findViewById(R.id.back);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        String temp_data = sharedPreferences.getString("info", "");
        com.google.gson.stream.JsonReader jsonReader = new com.google.gson.stream.JsonReader(new StringReader(temp_data));
        dataList = gson.fromJson(jsonReader, new TypeToken<ArrayList<Data>>(){}.getType());
        adapter = new Adapter(dataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataList.clear();
                adapter.notifyDataSetChanged();
                editor.putString("info", gson.toJson(dataList));
                editor.commit();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List_Activity.this, MainActivity.class);
                intent.putExtra("info", gson.toJson(dataList));
                startActivity(intent);
            }
        });

    }
}
