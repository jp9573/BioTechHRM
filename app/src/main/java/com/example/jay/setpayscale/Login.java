package com.example.jay.setpayscale;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Login extends AppCompatActivity {

    Intent welcome;
    Context c;
    EditText enteredUsername,enteredPassword;
    String username="",password="";
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        c = this;
        enteredUsername = (EditText) findViewById(R.id.username);
        enteredPassword = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.loginButton);

    }

    public void loginValidation(View v2) {
        //Toast.makeText(this, "loginValidation", Toast.LENGTH_SHORT).show();

        username = String.valueOf(enteredUsername.getText());
        password = String.valueOf(enteredPassword.getText());
        if(username.equals("") || password.equals("")){
            Toast.makeText(this, "Please enter valid username and password", Toast.LENGTH_SHORT).show();
        }else if {

            check ch = new check();
            ch.execute();            
        }

    }

    class check extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            button.setEnabled(false);
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("https://biotechhrm.000webhostapp.com/Script/Login.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postdata = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String line,result = "";
                while((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {        
            if(s.equals(null)){
                Toast.makeText(c,"Data Not Loading",Toast.LENGTH_SHORT).show();
            }else{
                if(s.equals("Success")){
                    welcome = new Intent(c,MainActivity.class);
                    startActivity(welcome);
                }else {
                    Toast.makeText(c,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                }
            }

            button.setEnabled(true);
        }
    }

}
