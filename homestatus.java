package com.example.dizivisitor;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class homestatus extends AppCompatActivity {
    String flatnumber,Switch;
    Switch btnSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homestatus);

        final EditText etflatnumber = (EditText) findViewById(R.id.flatnumber);
        btnSwitch =(Switch) findViewById(R.id.switch1);
        final Switch etswitch = (Switch) findViewById(R.id.switch1);


        Button bRegister = (Button) findViewById(R.id.submitr);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flatnumber=etflatnumber.getText().toString();
                Switch=etswitch.getText().toString();
                if (btnSwitch.isChecked())
                    Switch = btnSwitch.getTextOn().toString();
                else
                    Switch = btnSwitch.getTextOff().toString();
                Toast.makeText(getApplicationContext(),"Status of home: " + Switch,Toast.LENGTH_SHORT).show();

                String method="homestatus";
                if(flatnumber!=null&&btnSwitch!=null) {
                    BackgroundTask2 backgroundTask = new BackgroundTask2(getApplicationContext());
                    backgroundTask.execute(method, flatnumber, Switch);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"All fields require",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
