package com.example.jeremy.testapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.message;

/**
 * Created by Jeremy on 10/13/2017.
 */

public class UpDownCounter extends AppCompatActivity {
    //FIELD VARIABLES
    Button addButton;
    Button subButton;
    Button settingsButton;
    TextView countView;
    EditText input;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        input = new EditText(getApplicationContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);

        addButton = (Button)findViewById(R.id.addButton);
        subButton = (Button)findViewById(R.id.minusButton);
        settingsButton = (Button)findViewById(R.id.configButton);
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
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    //SUBTRACT
    public void subtract(){
        if (!countView.getText().equals("-")) {
            int count = Integer.parseInt(countView.getText().toString());
            count--;
            countView.setText(String.valueOf(count));
        }
    }

    //ADD
    public void add(){
        if (!countView.getText().equals("-")) {
            int count = Integer.parseInt(countView.getText().toString());
            count++;
            countView.setText(String.valueOf(count));
        }
    }

    //ONLY WORKS ONCE LOL
    public void openDialog(){
        final AlertDialog.Builder alert_builder = new AlertDialog.Builder(this)
                .setTitle("Update Counter")
                .setMessage("Set value of counter.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        countView.setText(value);
                        dialog.dismiss();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });
        if (input.getParent()!=null){
            ((ViewGroup)input.getParent()).removeView(input);
            input.setText(null);
        }
        alert_builder.setView(input);
        final AlertDialog alert = alert_builder.create();
        input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    alert.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
        alert.show();
    }
}
