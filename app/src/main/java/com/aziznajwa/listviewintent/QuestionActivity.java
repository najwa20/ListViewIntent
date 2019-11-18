package com.aziznajwa.listviewintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {
    Intent i;
    TextView q;
    Button oui;
    Button non;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = getIntent();
        Bundle b=i.getBundleExtra("bundel");
        String title=b.getString("title");
        String desc=b.getString("desc");
        q=findViewById(R.id.question);
        q.setText("utiliser-vouz "+title+" comme "+desc);
        oui=findViewById(R.id.buttonOui);
        non=findViewById(R.id.buttonNon);
        non.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b=i.getBundleExtra("bundel");
                b.putString("rep","non");
                Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                i1.putExtra("bundel",b);
                startActivity(i1);
            }
        });
        oui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b=i.getBundleExtra("bundel");
                b.putString("rep","oui");
                Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                i1.putExtra("bundel",b);
                startActivity(i1);
            }
        });
    }
}
