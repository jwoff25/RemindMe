package com.example.jeremy.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button counterButton = (Button)findViewById(R.id.second_button);
        counterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(), UpDownCounter.class);
                startActivity(i);
            }
        });

        final Button countdownButton = (Button)findViewById(R.id.countd_button);
        countdownButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(), CountdownCounter.class);
                startActivity(i);
            }
        });
    }

    

}
