package com.example.jay.setpayscale;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class SetPayScale extends AppCompatActivity {

    EditText DA,HRA,MA,TA,OTHER;
    ProgressBar progressBar;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payscale);
        update = (Button) findViewById(R.id.Update);
        DA = (EditText) findViewById(R.id.da);
        HRA = (EditText) findViewById(R.id.hra);
        MA = (EditText) findViewById(R.id.ma);
        TA = (EditText) findViewById(R.id.ta);
        OTHER = (EditText) findViewById(R.id.oa);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        LoadData ld = new LoadData(this);
        ld.execute();
    }

    public void next(View view) {
        Intent intent = new Intent(this,PostSetting.class);
        startActivity(intent);
    }
    public void updatePayScale(View view) {
            BW bw = new BW(this);
            bw.execute(DA.getText().toString(),HRA.getText().toString(), MA.getText().toString(), TA.getText().toString(), OTHER.getText().toString());
    }

    class LoadData extends AsyncTask<Void, Void, String> {

        Context c;
        LoadData(Context c) {
            this.c = c;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            update.setEnabled(false);
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("https://biotechhrm.000webhostapp.com/Script/pay_scale_select.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
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
        protected void onPostExecute(String result) {
            if(result != null) {
                JSONObject jsonObject;
                JSONArray jsonArray;
                try {
                    jsonObject = new JSONObject(result);
                    jsonArray = jsonObject.getJSONArray("Packages");
                    int count = 0;
                    String da = "", hra = "", ma = "", ta = "", other = "";
                    while (count < jsonArray.length()) {
                        JSONObject jo = jsonArray.getJSONObject(count);
                        da = jo.getString("da");
                        hra = jo.getString("hra");
                        ma = jo.getString("ma");
                        ta = jo.getString("ta");
                        other = jo.getString("arrias");
                        count++;
                    }
                    DA.setText(da);
                    HRA.setText(hra);
                    MA.setText(ma);
                    TA.setText(ta);
                    OTHER.setText(other);
//                    Toast.makeText(c,"done "+result,Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    update.setEnabled(true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class BW extends AsyncTask<String, Void, String> {

        Context context;
        BW(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            update.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("https://biotechhrm.000webhostapp.com/Script/pay_scale_ins.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postdata = URLEncoder.encode("da","UTF-8")+"="+URLEncoder.encode(params[0],"UTF-8")+"&"+
                        URLEncoder.encode("hra","UTF-8")+"="+URLEncoder.encode(params[1],"UTF-8")+"&"+
                        URLEncoder.encode("ma","UTF-8")+"="+URLEncoder.encode(params[2],"UTF-8")+"&"+
                        URLEncoder.encode("ta","UTF-8")+"="+URLEncoder.encode(params[3],"UTF-8")+"&"+
                        URLEncoder.encode("arrias","UTF-8")+"="+URLEncoder.encode(params[4],"UTF-8");
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
            if(Integer.parseInt(s) > 0) {
                Toast.makeText(context,"Data Successfully Updated",Toast.LENGTH_LONG).show();
            }
            progressBar.setVisibility(View.GONE);
            update.setEnabled(true);

        }
    }
}