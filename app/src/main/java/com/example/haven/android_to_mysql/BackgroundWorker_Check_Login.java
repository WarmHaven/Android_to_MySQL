package com.example.haven.android_to_mysql;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

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

/**
 * Created by Haven on 2018/2/22.
 */

public class BackgroundWorker_Check_Login  extends AsyncTask<String,Void,String>{
    Context context;
    AlertDialog alertDialog;
    Activity activity;
    private String result="";

    BackgroundWorker_Check_Login(Context ctx,Activity activity){
        context = ctx;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];
        String login_url = "http://10.0.2.2/app_link/Check_Login.php";
        if(type.equals("Login")){
            try {

                String name = voids[1];
                String pass = voids[2];


                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("name","UTF-8") +"="+ URLEncoder.encode(name,"UTF-8") +"&"
                        +URLEncoder.encode("pass","UTF-8") +"="+ URLEncoder.encode(pass,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(inputStream,"iso-8859-1")));

                String line;

                while((line = bufferedReader.readLine())!=null){
                    result+=line;
                }


                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;







            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return  null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String aVoid) {
        alertDialog.setMessage(aVoid);
        alertDialog.show();

        if(result.equals("Login Success")){
            alertDialog.show();

            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.getApplicationContext().startActivity(intent);
        }else {
            alertDialog.show();
        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    public String getResult(){
        return result;
    }






}
