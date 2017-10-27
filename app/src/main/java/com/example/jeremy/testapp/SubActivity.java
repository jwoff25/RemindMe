package com.example.jeremy.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jeremy on 10/13/2017.
 */

public class SubActivity extends AppCompatActivity {
    Button addButton;
    Button subButton;
    TextView countView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        addButton = (Button)findViewById(R.id.addButton);
        subButton = (Button)findViewById(R.id.minusButton);
        countView = (TextView)findViewById(R.id.counterNumber);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtract();
            }
        });
    }

    public void subtract(){
        int count = Integer.parseInt(countView.getText().toString());
        count--;
        countView.setText(String.valueOf(count));
    }

    public void add(){
        int count = Integer.parseInt(countView.getText().toString());
        count++;
        countView.setText(String.valueOf(count));
    }
}
