package com.thenextbiggeek.iphonexsxrwallpapers;

import android.app.WallpaperManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.IOException;
import java.lang.reflect.Field;

public class FinalActivity extends AppCompatActivity {

    public  WallpaperManager wallpaperManager;
    int resId;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Bundle mBundle = getIntent().getExtras();
        resId = mBundle.getInt("RES_ID");
        ImageView mImageView = findViewById(R.id.imageView);
        mImageView.setImageResource(resId);



    }

    public void setWallpaper(View view) {
        //voila. let's set this wallpaper up

        final ProgressBar progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Wallpaper Changed. Enjoy ;)", Toast.LENGTH_LONG).show();

            }
        };
        handler.postDelayed(runnable, 800);
        LongOperation longOperation = new LongOperation();
        longOperation.execute();
    }



    public void cancel(View v){
        Intent mIntent = new Intent(FinalActivity.this, MainActivity.class);
        startActivity(mIntent);
    }


    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
            try {
                wallpaperManager.setResource(resId);
            } catch (IOException e) {
                e.printStackTrace();

            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}


