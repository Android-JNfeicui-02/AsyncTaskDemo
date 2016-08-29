package edu.feicui.asynctaskdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class ProgressActivity extends AppCompatActivity {

    ProgressBar mProgressBar;
    private AsyncProgress mAsyncProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);


        mAsyncProgress = new AsyncProgress();
        mAsyncProgress.execute();

    }

    @Override
    protected void onPause() {
        super.onPause();
        AsyncTask.Status status = mAsyncProgress.getStatus();
        if (mAsyncProgress != null && status == AsyncTask.Status.RUNNING) {
            // 只是标记了 Async Task 可以退出  实际状态没有退出
            mAsyncProgress.cancel(true);
        }
    }

    private class AsyncProgress extends AsyncTask<Void, Integer, Void> {


        @Override
        protected Void doInBackground(Void... params) {

            for (int i = 0; i < 100; i++) {

                if (isCancelled()) {
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
            mProgressBar.setProgress(values[0]);
        }
    }
}
