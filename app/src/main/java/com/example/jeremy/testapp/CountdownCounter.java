package com.example.jeremy.testapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
        input.setInputType(InputType.TYPE_CLASS_NUMBER);

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
        final AlertDialog.Builder alert_builder = new AlertDialog.Builder(this)
                .setTitle("Update Counter")
                .setMessage("Set value of counter.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        countText.setText(value);
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
