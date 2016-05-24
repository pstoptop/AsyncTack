package com.example.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;
import java.net.URLConnection;


/**
 * Created by Administrator on 2016-03-25.
 */
public class ImageTest extends Activity {

    private ImageView mImageView;

    private ProgressBar mProgressBar;

    private static String URL = "http://d.lanrentuku.com/down/png/1602/tiantiansifangmao/tiantiansifangmao-04.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);

        mImageView = (ImageView) findViewById(R.id.image);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);

        new MyAsyncTask().execute(URL);
    }


    class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {

        //加载进度条
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }


       //显示图片
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mProgressBar.setVisibility(View.GONE);
            mImageView.setImageBitmap(bitmap);
        }

        //下载网络数据（耗时操作）
        @Override
        protected Bitmap doInBackground(String... params) {

            //获取传进来的参数
            String url = params[0]; //取出对应的url
            Bitmap bitmap = null;
            URLConnection connection ;
            InputStream is;
            try {
                connection = new URL(url).openConnection(); //获取网络对象连接
                is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                //通过decodeStream解析输入流
                Thread.sleep(3000);
                bitmap = BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //将bitmap返回
            return bitmap;
        }
    }

}

