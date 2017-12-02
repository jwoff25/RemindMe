package com.example.jeremy.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class MoneyManager extends AppCompatActivity {

    private Button debit;
    private Button credit;
    private Button setCash;
    private TextView log;
    private ScrollView container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_manager);
        log = new TextView(getApplicationContext());
        container = new ScrollView(getApplicationContext());
        log.setText(R.string.default_log);
        container.addView(log);
    }
}
