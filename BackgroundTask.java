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

public class BackgroundTask extends AsyncTask<String,Void,String> {
    Context ctx;
    BackgroundTask(Context ctx){
        this.ctx=ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String
                register_url="https://iotenabledglucometer.000webhostapp.com/Register2.php";
        String
                login_url="https://iotenabledglucometer.000webhostapp.com/Login2.php";
        String
                getdata_url="https://iotenabledglucometer.000webhostapp.com/sending.php";



        String method = params[0];
        if(method.equals("register")){



            
            String i_name=params[1];
            String i_username=params[2];
            String i_password=params[3];
            String i_phoneno=params[4];
            try {
                URL url=new URL(register_url);
                HttpURLConnection
                        httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS =httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new
                        OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("u_name","UTF-8")+"="+URLEncoder.encode(i_name,"UTF-8")+"&"+
                        URLEncoder.encode("u_username","UTF-8")+"="+URLEncoder.encode(i_username,"UTF-8")+"&"+
                        URLEncoder.encode("u_password","UTF-8")+"="+URLEncoder.encode(i_password,"UTF-8")+"&"+
                        URLEncoder.encode("u_phoneno","UTF-8")+"="+URLEncoder.encode(i_phoneno,"UTF-8");

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
        if(method.equals("login")){
            String login_name=params[1];
            String login_password=params[2];
            try {
                URL url =new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)
                        url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new
                        OutputStreamWriter(outputStream,"UTF-8"));

                String data =URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("login_password","UTF-8")+"="+URLEncoder.encode(login_password,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream =httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                String response="";
                String line="";
                while((line=bufferedReader.readLine())!=null){
                    response+=line;
                }
                bufferedReader.close();
                inputStream.close();
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
            c.startActivity(new Intent(c, MainActivity.class));
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