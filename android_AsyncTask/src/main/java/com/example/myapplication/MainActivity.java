package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyAsyncTask task = new MyAsyncTask();
        task.execute();
    }

    public void loadImage(View view){
        startActivity(new Intent(this,ImageTest.class));
    }

    public void loadProgressbar(View view){
        startActivity(new Intent(this,ProgressBarTest.class));
    }
}
