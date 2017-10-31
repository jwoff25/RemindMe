package com.example.jeremy.testapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CountdownCounter extends AppCompatActivity {

    Button countButton;
    Button selectNumber;
    TextView countText;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown_counter);

        countButton = (Button)findViewById(R.id.circle_button);
        selectNumber = (Button)findViewById(R.id.invis_button);
        countText = (TextView)findViewById(R.id.countText);
        input = new EditText(getApplicationContext());

        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtract();
            }
        });

        selectNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void subtract(){
        int val = Integer.parseInt(countText.getText().toString());
        val--;
        countText.setText(String.valueOf(val));
    }

    public void openDialog(){
        AlertDialog alert = new AlertDialog.Builder(this)
                .setTitle("Update Counter")
                .setMessage("Set value of counter.")
                .setView(input)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        countText.setText(value);
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Do nothing.
                    }
                }).show();
    }
}
