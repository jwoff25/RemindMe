package com.example.jeremy.testapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MoneyManager extends AppCompatActivity {

    //buttons
    private Button debit;
    private Button credit;
    private Button setCash;

    //text views
    private TextView cashText;
    private TextView log;

    //scroll view (?)
    private ScrollView container;

    //edit text for dialog boxes
    private EditText input;

    private Context context;

    //shared preferences to save values
    SharedPreferences prefs;
    SharedPreferences.Editor prefs_editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_manager);

        context = getApplicationContext();
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        cashText = (TextView)findViewById(R.id.moneyText);
        cashText.setText(String.format(Locale.getDefault(), "$%.02f", Float.parseFloat(prefs.getString("Cash", "$00.00"))));
        log = (TextView)findViewById(R.id.cashLog);
        container = new ScrollView(getApplicationContext());
        log.setText(prefs.getString("Log", getString(R.string.default_log)));
        //container.addView(log);
        input = new EditText(getApplicationContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        //buttons
        debit = (Button)findViewById(R.id.debitButton);
        credit = (Button)findViewById(R.id.creditButton);
        setCash = (Button)findViewById(R.id.moneyButton);

        debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_debit();
            }
        });

        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_credit();
            }
        });

        setCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void writeToLog(String s){
        String log_text = prefs.getString("Log", getString(R.string.default_log));
        if (log_text.equals(getString(R.string.default_log))){
            log_text = s + "\n";
            prefs_editor = prefs.edit();
            prefs_editor.putString("Log", log_text);
            prefs_editor.apply();
        } else {
            log_text += s + "\n";
            prefs_editor = prefs.edit();
            prefs_editor.putString("Log", log_text);
            prefs_editor.apply();
        }
        log.setText(prefs.getString("Log", getString(R.string.default_log)));
    }

    public void do_debit(){ //this garbage makes me want to kill myself but it works so i'll take it
        final AlertDialog.Builder alert_builder = new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("How much would you like to add?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (!input.getText().toString().equals("")) {
                            float debit_amount = Float.parseFloat(input.getText().toString());
                            float value = Float.parseFloat(prefs.getString("Cash", "00.00"));
                            String before = String.format(Locale.getDefault(), "%.02f", value);
                            Log.d("nullcheck", "am i null?");
                            float new_total = value + debit_amount;
                            prefs_editor = prefs.edit();
                            prefs_editor.putString("Cash", String.valueOf(new_total));
                            prefs_editor.apply();
                            String after = String.format(Locale.getDefault(), "%.02f", new_total);
                            String final_text = "$" + String.format(Locale.getDefault(), "%.02f", Float.parseFloat(prefs.getString("Cash", "00.00"))); //format to two decimal points
                            cashText.setText(final_text);
                            writeToLog("Debit: $" + String.format(Locale.getDefault(), "%.02f", debit_amount) + ", Before: $" + before + ", After: $" + after);
                            dialog.dismiss();
                        } else { //add a serious message dude
                            Log.d("cmon man", "hands up");
                            //Toast t = new Toast(context);
                            //t.makeText()
                            //t.show();
                        }
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

    public void do_credit(){
        final AlertDialog.Builder alert_builder = new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("How much would you like to subtract?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (!input.getText().toString().equals("")) {
                            float credit_amount = Float.parseFloat(input.getText().toString());
                            float value = Float.parseFloat(prefs.getString("Cash", "00.00"));
                            String before = String.format(Locale.getDefault(), "%.02f", value);
                            float new_total = value - credit_amount;
                            prefs_editor = prefs.edit();
                            prefs_editor.putString("Cash", String.valueOf(new_total));
                            prefs_editor.apply();
                            String after = String.format(Locale.getDefault(), "%.02f", new_total);
                            String final_text = "$" + String.format(Locale.getDefault(), "%.02f", Float.parseFloat(prefs.getString("Cash", "00.00"))); //format to two decimal points
                            cashText.setText(final_text);
                            writeToLog("Credit: $" + String.format(Locale.getDefault(), "%.02f", credit_amount) + ", Before: $" + before + ", After: $" + after);
                            dialog.dismiss();
                        } else {
                            Log.d("no way", "jose man");
                        }
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

    public void openDialog(){
        final AlertDialog.Builder alert_builder = new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("Set value of cash.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (!input.getText().toString().equals("")) {
                            String value = String.format("%.02f", Float.parseFloat(input.getText().toString()));
                            prefs_editor = prefs.edit();
                            prefs_editor.putString("Cash", value);
                            prefs_editor.apply();
                            String final_text = "$" + prefs.getString("Cash", "00.00");
                            cashText.setText(final_text);
                            dialog.dismiss();
                        } else {
                            Log.d("no way sir", "no way");
                            dialog.dismiss();
                        }
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
