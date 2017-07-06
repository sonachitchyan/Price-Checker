package com.example.asus.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.gsm.GsmCellLocation;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.w3c.dom.Text;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView name, count, price, amount_price;
    private double amount;
    private List<Data> datas;
    private String Barcode;
    private LinearLayout info;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private Button list, zero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextView) findViewById(R.id.name);
        count = (TextView) findViewById(R.id.count);
        price = (TextView) findViewById(R.id.price);
        amount_price = (TextView) findViewById(R.id.amount_price);
        list = (Button) findViewById(R.id.list);
        zero = (Button) findViewById(R.id.zero);
        list.setOnClickListener(this);
        zero.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent.getStringExtra("info") ==null){
            datas = new ArrayList<>();
            Data data = new Data("4850013000063", "Բյուրեղ", 0, 130.00);
            datas.add(data);
        }
        else {
            JsonReader jsonReader = new JsonReader(new StringReader(sharedPreferences.getString("info", "")));
            datas = gson.fromJson(jsonReader, new TypeToken<ArrayList<Data>>(){}.getType());
        }
        amount = 0;
        info = (LinearLayout) findViewById(R.id.info_lin);
        info.setVisibility(View.GONE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        gson = new Gson();

        Barcode = "";


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        char pressedKey = (char) event.getUnicodeChar();
        if (pressedKey == '\n') {
            for (Data d : datas) {
                if (Barcode.equals(d.getBarcode())) {
                    d.setCount(d.getCount() + 1);
                    name.setText(d.getName());
                    count.setText(String.valueOf(d.getCount()));
                    price.setText(String.valueOf(d.getPrice()));
                    amount += d.getPrice();
                    amount_price.setText(String.valueOf(amount));
                    info.setVisibility(View.VISIBLE);
                    break;
                }
            }
            Barcode = "";
        } else {
            Barcode = Barcode + pressedKey;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.list:
                Intent intent = new Intent(MainActivity.this, List_Activity.class);
                editor.putString("info", gson.toJson(datas));
                editor.commit();
                startActivity(intent);
                break;
            case R.id.zero:
                datas.clear();
                editor.putString("info", gson.toJson(datas));
                editor.commit();
                info.setVisibility(View.GONE);
                break;
        }
    }
}
