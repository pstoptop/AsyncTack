package com.example.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2016-03-25.
 */
public class ProgressBarTest extends Activity {

    private ProgressBar mProgressBar;

    private MyAsyncTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mTask = new MyAsyncTask();
        mTask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mTask != null && mTask.getStatus() == AsyncTask.Status.RUNNING) {
            //cancel只是将对应的AsyncTask标记为cancel状态，并不是真正的取消状态
            mTask.cancel(true);
        }
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            //模拟进度更新
            for (int i = 0; i < 100; i++) {
                if(isCancelled()){
                    break;
                }
                publishProgress(i);

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //获取进度值
            if (isCancelled()){
                return;
            }
            mProgressBar.setProgress(values[0]);
        }
    }
}
