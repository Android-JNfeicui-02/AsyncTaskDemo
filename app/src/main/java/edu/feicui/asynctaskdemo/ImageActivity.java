package edu.feicui.asynctaskdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageActivity extends AppCompatActivity {

    ImageView mImageView;
    ProgressBar mProgressBar;

    public static final String url = "http://ww2.sinaimg.cn/mw600/62ca611cgw1f68nizwj9sj20j60j6gq5.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        mImageView = (ImageView) findViewById(R.id.iv);
        mProgressBar = (ProgressBar) findViewById(R.id.pb);

        ImageAsync async = new ImageAsync();
        async.execute(url);

    }

    /**
     * 要的是一张图片 一个图片 对应的是 Bitmap --> 位图
     */
    private class ImageAsync extends AsyncTask<String, Void, Bitmap> {
        /**
         * 实现联网功能
         * @param params
         * @return
         */
        @Override
        protected Bitmap doInBackground(String... params) {

            String url = params[0];
            Bitmap bitmap = null;

            HttpURLConnection connection;
            InputStream is;

            try {
                connection = (HttpURLConnection) new URL(url).openConnection();
                is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 加载图片
                bitmap = BitmapFactory.decodeStream(bis);

                bis.close();
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mProgressBar.setVisibility(View.GONE);
            mImageView.setImageBitmap(bitmap);
        }
    }
}
