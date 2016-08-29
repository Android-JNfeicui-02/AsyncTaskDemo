package edu.feicui.asynctaskdemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MyAsync myAsync = new MyAsync();
        // myAsync.execute();

    }

    public void openImage(View view) {
        Intent intent  = new Intent(this,ImageActivity.class);
        startActivity(intent);
    }

    public void openProgress(View view) {
        Intent intent  = new Intent(this,ProgressActivity.class);
        startActivity(intent);
    }

    private class MyAsync extends AsyncTask<Void,Void,Void> {

        private static final String TAG = "MyAsync";
        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute: start");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d(TAG, "onPostExecute: start");

            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Log.d(TAG, "onProgressUpdate: start");

            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.d(TAG, "doInBackground: start");
            publishProgress();
            return null;
        }
    }
}
