package com.example.jay.setpayscale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click_p(View view) {
        Intent intent = new Intent(this,ProfileEdit.class);
        startActivity(intent);
    }

    public void searchEmployee(View view) {
        Intent intent = new Intent(this,SearchEmployee.class);
        startActivity(intent);
    }

    public void vacancySearch(View view) {
        Intent intent = new Intent(this,VacancySearch.class);
        startActivity(intent);
    }

    public void payScale(View view) {
        Intent intent = new Intent(this,SetPayScale.class);
        startActivity(intent);
    }

    public void postSetting(View view) {
        Intent intent = new Intent(this,PostSetting.class);
        startActivity(intent);
    }

    public void viewInstitute(View view) {

    }
}
