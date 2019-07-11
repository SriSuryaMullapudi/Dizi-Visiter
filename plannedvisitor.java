package com.example.dizivisitor;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class plannedvisitor extends AppCompatActivity {


    TextView mTv;
    Button mBtn;
    Calendar c;
    DatePickerDialog dpd;
    EditText chooseTime;
    TimePickerDialog timePickerDialog;
    int currentHour;
    int currentMinute;
    String amPm;


    String name,phonenumber,purpose,gender,date,timeofmeeting,visitortype,identitytype,identityrefernce,spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plannedvisitor);



        final EditText etName = (EditText) findViewById(R.id.name);
        final EditText etphonenumber = (EditText) findViewById(R.id.phonenumber);
        final EditText etpurpose = (EditText) findViewById(R.id.purpose);
        final EditText ettimeofmeeting = (EditText) findViewById(R.id.timeofmeeting);
        final EditText etidentityrefernce = (EditText) findViewById(R.id.identityrefernce);

        mTv = (TextView)findViewById(R.id.date);
        mBtn = (Button)findViewById(R.id.btnpick);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month=c.get(Calendar.MONTH);
                int year= c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(plannedvisitor.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        mTv.setText(mDay + "/" + (mMonth+1) + "/" +mYear);
                    }
                },day,month,year);
                dpd.show();
            }
        });

        chooseTime = findViewById(R.id.timeofmeeting);
        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                currentHour = c.get(Calendar.HOUR_OF_DAY);
                currentMinute = c.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(plannedvisitor.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay >=12){
                            amPm = "PM";
                        } else{
                            amPm = "AM";
                        }
                        chooseTime.setText(String.format("%2d:%2d",hourOfDay,minute)+ amPm);
                    }
                }, currentHour,currentMinute, false);
                timePickerDialog.show();
            }
        });

        Spinner mySpinner = (Spinner) findViewById(R.id.visitortype);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(plannedvisitor.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.visitortype));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        Spinner mySpinner1 = (Spinner) findViewById(R.id.identitytype);
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(plannedvisitor.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.identitytype));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner1.setAdapter(myAdapter1);

        Button bRegister = (Button) findViewById(R.id.submitr);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=etName.getText().toString();
                phonenumber=etphonenumber.getText().toString();
                purpose=etpurpose.getText().toString();
                timeofmeeting=ettimeofmeeting.getText().toString();
                identityrefernce=etidentityrefernce.getText().toString();

                String method="plannedvisitor";
                if(name!=null&&gender!=null&&phonenumber!=null&&purpose!=null&&date!=null&&timeofmeeting!=null&&visitortype!=null&&identitytype!=null&&identityrefernce!=null ) {
                    BackgroundTask1 backgroundTask = new BackgroundTask1(getApplicationContext());
                    backgroundTask.execute(method, name,gender,phonenumber,purpose,date,timeofmeeting,visitortype,identitytype,identityrefernce);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"All fields require",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}