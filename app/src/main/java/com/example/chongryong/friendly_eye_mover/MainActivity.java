package com.example.chongryong.friendly_eye_mover;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    public static int language_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ItemData> list = new ArrayList<>();
        list.add(new ItemData("Choose Language", R.drawable.choose));
        list.add(new ItemData("ENGLISH", R.drawable.england));
        list.add(new ItemData("GERMAN", R.drawable.germany));
        Spinner sp = (Spinner) findViewById(R.id.spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(this, R.layout.spinner_layout, R.id.txt, list);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg = (ViewGroup) view;
                TextView tv = (TextView) vg.findViewById(R.id.txt);
                Log.d("language", (String) tv.getText());
                if ((String) tv.getText() == "Choose Language") {
                    language_index = 0;
                }
                if ((String) tv.getText() == "ENGLISH") {
                    language_index = 1;
                }
                if ((String) tv.getText() == "GERMAN") {
                    language_index = 2;
                }
//                Toast.makeText(MainActivity.this, tv.getText().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                language_index = 0;
            }
        });

        Button start_btn = (Button) findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (language_index ==  0) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("M e s s a g e")
                            .setMessage("     Please select the language           ")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .show();

                    return;
                }
                StartPage.start_flag = 0;
                Intent intent = new Intent(MainActivity.this, StartPage.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });

        Button explanation_btn = (Button) findViewById(R.id.explanation_btn);
        explanation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (language_index ==  0) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("M e s s a g e")
                            .setMessage("     Please select the language           ")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .show();

                    return;
                }
                StartPage.start_flag = 1;
                startActivity(new Intent(MainActivity.this, StartPage.class));
//                startActivity(new Intent(MainActivity.this, APPIntroTwo.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
    }

    public static void function() {

    }
}