package com.thenextbiggeek.iphonexsxrwallpapers;

import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
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
            }
        };
        handler.postDelayed(runnable, 800);
        wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setResource(resId);
            Toast.makeText(this, "Wallpaper Changed. Enjoy ;)", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Wallpaper changing had a problem. Try again", Toast.LENGTH_LONG).show();

        }

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
}
