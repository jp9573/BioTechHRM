package com.example.jay.setpayscale;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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

public class SearchEmployee extends AppCompatActivity {


    Spinner s1;
    EditText emp_name;
    Button search;
//    ProgressBar progressBar;
    String name,intid;
    Context c;
    private RecyclerView recyclerView;
    private OurAdapter adapter;
    private List<DataForSearchEmp> albumList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_emp);
        c = this;
        emp_name=(EditText)findViewById(R.id.emp_name);
        s1=(Spinner)findViewById(R.id.ist1);
        search=(Button)findViewById(R.id.search_emp);
//        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        albumList = new ArrayList<>();
        adapter = new OurAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    public void searchEmp(View view) {

        intid=s1.getItemAtPosition(s1.getSelectedItemPosition()).toString();
        name = emp_name.getText().toString();
        BW bw = new BW(this);
        bw.execute();
    }

    class BW extends AsyncTask<Void, Void, String> {

        Context context;
        BW(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
//            progressBar.setVisibility(View.VISIBLE);
            search.setEnabled(false);
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("https://biotechhrm.000webhostapp.com/Script/AseemSearchEmployee.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String postdata = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+ URLEncoder.encode("initid","UTF-8")+"="+URLEncoder.encode(intid,"UTF-8");
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
//                Toast.makeText(context,s,Toast.LENGTH_LONG).show();
//          progressBar.setVisibility(View.GONE);
            if(s.length() > 0){
                JSONObject jsonObject;
                JSONArray jsonArray;
                String empe_name, inst_id, specialisation, dob, ret_year;
                try {
                    jsonObject = new JSONObject(s);
                    jsonArray = jsonObject.getJSONArray("Packages");
                    int count = 0;
                    while (count < jsonArray.length()) {
                        JSONObject jo = jsonArray.getJSONObject(count);
                        empe_name = jo.getString("empe_name");
                        inst_id = jo.getString("inst_id");
                        specialisation = jo.getString("specialisation");
                        dob = jo.getString("dob");
                        ret_year = jo.getString("ret_year");
                        DataForSearchEmp data = new DataForSearchEmp(empe_name, inst_id, specialisation, dob, ret_year);

                        count++;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            search.setEnabled(true);
        }
    }

}
