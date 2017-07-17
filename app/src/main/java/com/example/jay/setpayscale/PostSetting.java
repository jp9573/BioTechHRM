package com.example.jay.setpayscale;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

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
import java.util.ArrayList;
import java.util.List;

public class PostSetting extends AppCompatActivity {

    ProgressBar progressBar;
    Button update;
    Spinner spinner;
    EditText gradePay,payBand,salary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_setting);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        gradePay = (EditText) findViewById(R.id.gradePay);
        payBand = (EditText) findViewById(R.id.payBand);
        salary = (EditText) findViewById(R.id.salary);
        update = (Button) findViewById(R.id.Update);
        spinner = (Spinner) findViewById(R.id.spinner);
        LoadData ld = new LoadData(this);
        ld.execute();

    }

    // Initially loading of post_name data
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
                URL url = new URL("https://biotechhrm.000webhostapp.com/Script/postReturn.php");
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
                try{
                    ArrayList<String> data = new ArrayList<>();
                    JSONObject jsonObject;
                    JSONArray jsonArray;
                    jsonObject = new JSONObject(result);
                    jsonArray = jsonObject.getJSONArray("Packages");
                    int count = 0;
                    while (count < jsonArray.length()) {
                        JSONObject jo = jsonArray.getJSONObject(count);
                        data.add(jo.getString("post_name"));
                        count++;
                    }
                    final ArrayList<String> temp = data;
                    MyAdapter adapter = new MyAdapter(data);
                    spinner.setAdapter(adapter);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent,
                                                   View view, int pos, long id) {
                            Data dt = new Data(temp);
                            dt.execute(pos);
                        }
                        public void onNothingSelected(AdapterView parent) {

                        }
                    });
                    progressBar.setVisibility(View.GONE);
                    update.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Data from post_name selection spinner
    class Data extends AsyncTask<Integer, Void, Void> {
        final ArrayList<String> temp;
        String d1,d2,d3;
        Data(ArrayList<String> data) {
            temp = data;
        }

        @Override
        protected Void doInBackground(Integer... params) {
            int pos = params[0];
            try {
                URL url = new URL("https://biotechhrm.000webhostapp.com/Script/postDataReturn.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postdata = URLEncoder.encode("post_name","UTF-8")+"="+ URLEncoder.encode(temp.get(pos),"UTF-8");
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
                JSONObject jsonObject;
                JSONArray jsonArray;
                try {
                    jsonObject = new JSONObject(result);
                    jsonArray = jsonObject.getJSONArray("Packages");
                    int count = 0;
                    while (count < jsonArray.length()) {
                        JSONObject jo = jsonArray.getJSONObject(count);
                        d1 = jo.getString("grade_pay");
                        d2 = jo.getString("pay_band");
                        d3 = jo.getString("salary");
                        count++;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            gradePay.setText(d1);
            payBand.setText(d2);
            salary.setText(d3);
        }
    }

    class MyAdapter extends BaseAdapter implements SpinnerAdapter {
        private final List<String> data;

        public MyAdapter(ArrayList<String> data){
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View recycle, ViewGroup parent) {
            TextView text;
            if (recycle != null){
                text = (TextView) recycle;
            } else {
                text = (TextView) getLayoutInflater().inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
            }
            text.setTextColor(Color.BLACK);
            text.setText(data.get(position).toString());
            return text;
        }
    }
}
