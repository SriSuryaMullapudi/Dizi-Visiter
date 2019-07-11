package com.example.dizivisitor;



import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class LoginActivity extends AppCompatActivity {
    String us_name,us_password;

    ViewFlipper v_flipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        int images[] = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};

        v_flipper = findViewById(R.id.viewflipper);

        for (int image:images ){
            flipperImages(image);
        }

        final EditText upass =(EditText)findViewById(R.id.upass);
        final EditText username =(EditText)findViewById(R.id.uname);
        Button login=(Button)findViewById(R.id.login);
        TextView regbutt=(TextView)findViewById(R.id.butregister);
        regbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new
                        Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessiondata obj = new sessiondata();
                obj.context = LoginActivity.this;
                us_name=username.getText().toString();
                us_password=upass.getText().toString();
                obj.username = us_name;
                String method="login";
                BackgroundTask backgroundTask=new BackgroundTask(getApplicationContext());
                backgroundTask.execute(method,us_name,us_password);

            }

        });
    }

    public void flipperImages(int images){
        ImageView imageview = new ImageView(this);
        imageview.setBackgroundResource(images);

        v_flipper.addView(imageview);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);
        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }
}