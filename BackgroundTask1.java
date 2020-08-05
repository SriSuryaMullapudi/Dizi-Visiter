package com.example.dizivisitor;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask1 extends AsyncTask<String,Void,String> {
    Context ctx;
    BackgroundTask1(Context ctx){
        this.ctx=ctx;
    }
   // @Override
    protected String doInBackground(String... params) {

        String
                plannedvisitor_url="https://iotenabledglucometer.000webhostapp.com/plannedvisitor2.php";

        String
                housestatus_url="https://iotenabledglucometer.000webhostapp.com/housestatus2.php";


        String method = params[0];
        if(method.equals("plannedvisitor")){
            String i_name=params[1];
            String i_gender=params[2];
            String i_phonenumber=params[3];
            String i_purpose=params[4];
            String i_date=params[5];
            String i_timeofmeeting=params[6];
            String i_visitortype=params[7];
            String i_identitytype=params[8];
            String i_identityrefernce=params[9];
            try {
                URL url=new URL(plannedvisitor_url);
                HttpURLConnection
                        httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS =httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new
                        OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("u_name","UTF-8")+"="+URLEncoder.encode(i_name,"UTF-8")+"&"+
                        URLEncoder.encode("u_gender","UTF-8")+"="+URLEncoder.encode(i_gender,"UTF-8")+"&"+
                        URLEncoder.encode("u_phonenumber","UTF-8"     )+"="+URLEncoder.encode(i_phonenumber,"UTF-8")+"&"+
                        URLEncoder.encode("u_purpose","UTF-8")+"="+URLEncoder.encode(i_purpose,"UTF-8")+"&"+
                        URLEncoder.encode("u_date","UTF-8")+"="+URLEncoder.encode(i_date,"UTF-8")+"&"+
                        URLEncoder.encode("u_timeofmeeting","UTF-8")+"="+URLEncoder.encode(i_timeofmeeting,"UTF-8")+"&"+
                        URLEncoder.encode("u_visitortype","UTF-8")+"="+URLEncoder.encode(i_visitortype,"UTF-8")+"&"+
                        URLEncoder.encode("u_identitytype","UTF-8")+"="+URLEncoder.encode(i_identitytype,"UTF-8")+"&"+
                        URLEncoder.encode("u_identityrefernce","UTF-8")+"="+URLEncoder.encode(i_identityrefernce,"UTF-8");


                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS =httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new
                        InputStreamReader(IS,"iso-8859-1"));
                String response="";
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    response+=line;
                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "please try again later";
    }


    @Override
    protected void onPreExecute() {
        Toast.makeText(ctx,"Please wait...",Toast.LENGTH_LONG).show();
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Registeration Successfull...")) {
            Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
        }else if (result.equals("Success")){
            sessiondata obj = new sessiondata();
            Context c = obj.context;
            c.startActivity(new Intent(c, plannedvisitor.class));
        }
        else{
            Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
        }
        super.onPostExecute(result);
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public void execute(String method, Switch btnSwitch) {

    }
}
