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

public class BackgroundTask2 extends AsyncTask<String,Void,String> {
    Context ctx;
    BackgroundTask2(Context ctx){
        this.ctx=ctx;
    }
    @Override
    protected String doInBackground(String... params) {

        String
                housestatus_url="https://iotenabledglucometer.000webhostapp.com/housestatus2.php";


        String method = params[0];
        if(method.equals("homestatus")){
            String i_flatnumber=params[1];
            String i_Switch=params[2];

            try {
                URL url=new URL(housestatus_url);
                HttpURLConnection
                        httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS =httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new
                        OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("u_flatnumber","UTF-8")+"="+URLEncoder.encode(i_flatnumber,"UTF-8")+"&"+
                        URLEncoder.encode("u_switch","UTF-8")+"="+URLEncoder.encode(i_Switch,"UTF-8");


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