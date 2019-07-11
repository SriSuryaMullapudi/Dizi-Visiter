package com.example.dizivisitor;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Switch btnSwitch = (Switch) findViewById(R.id.butswitch1);
        final Switch etswitch = (Switch) findViewById(R.id.butswitch1);


        Button regbutt= (Button) findViewById(R.id.butplannedvisitor);
        regbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new
                        Intent(getApplicationContext(),plannedvisitor.class);
                startActivity(intent);
            }
        });



        Button regbutt2= (Button) findViewById(R.id.butviewplannedvisitor);
        regbutt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new
                        Intent(getApplicationContext(),viewplannedstatus.class);
                startActivity(intent);
            }
        });

        Button regbutt3= (Button) findViewById(R.id.butindetails);
        regbutt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new
                        Intent(getApplicationContext(),viewplannedstatus.class);
                startActivity(intent);
            }
        });

        Button regbutt4= (Button) findViewById(R.id.butvendor);
        regbutt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new
                        Intent(getApplicationContext(),maiddetails.class);
                startActivity(intent);
            }
        });
    }
}
