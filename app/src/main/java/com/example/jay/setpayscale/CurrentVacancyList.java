package com.example.jay.setpayscale;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CurrentVacancyList extends AppCompatActivity {

    String jsonData;
    JSONObject jsonObject;
    JSONArray jsonArray;
    CustomAdapter customAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_vacancy_list);
        listView = (ListView) findViewById(R.id.currentVacancyList);
        GiveData gd = new GiveData(this);
        gd.execute();
    }

    class GiveData extends AsyncTask<Void, Void, String> {
        Context c;

        GiveData(Context c) {
            this.c = c;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("https://biotechhrm.000webhostapp.com/Script/AseemTempCurrent.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line, result = "";
                while ((line = bufferedReader.readLine()) != null) {
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
            jsonData = s;
            customAdapter = new CustomAdapter(c,R.layout.row_layout);
            listView.setAdapter(customAdapter);
            try {
                jsonObject = new JSONObject(jsonData);
                jsonArray = jsonObject.getJSONArray("Packages");
                int count = 0;
                Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
                String empe_id,empe_name,empe_gender,inst_id,perf,ret_year;
                while(count < jsonArray.length()) {
                    JSONObject jo = jsonArray.getJSONObject(count);
                    empe_id = jo.getString("empe_id");
                    empe_name = jo.getString("empe_name");
                    empe_gender = jo.getString("empe_gender");
                    inst_id = jo.getString("inst_id");
                    perf = jo.getString("perf");
                    ret_year = jo.getString("ret_year");
                    Data data = new Data(empe_id,empe_name,empe_gender,inst_id,perf,ret_year);
                    customAdapter.add(data);
                    count++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
