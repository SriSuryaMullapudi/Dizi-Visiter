package com.example.dizivisitor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    String name,username,password,phoneno,date;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText etPhoneno = (EditText) findViewById(R.id.phone);
        final EditText etName = (EditText) findViewById(R.id.name);
        final EditText etUsername = (EditText) findViewById(R.id.username);
        final EditText etPassword = (EditText) findViewById(R.id.pass);
        Button bRegister = (Button) findViewById(R.id.submitr);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=etName.getText().toString();
                username=etUsername.getText().toString();
                password=etPassword.getText().toString();
                phoneno=etPhoneno.getText().toString();
                String method="register";
                if(name!=null&&username!=null&&password!=null&&phoneno!=null ) {
                    BackgroundTask backgroundTask = new BackgroundTask(getApplicationContext());
                    backgroundTask.execute(method, name, username, password, phoneno);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"All fields require",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}