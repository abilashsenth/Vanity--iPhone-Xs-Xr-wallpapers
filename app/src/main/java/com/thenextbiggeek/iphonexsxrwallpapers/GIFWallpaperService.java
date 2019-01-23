package com.thenextbiggeek.iphonexsxrwallpapers;

import android.app.Service;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.os.IBinder;
import android.service.wallpaper.WallpaperService;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;

import android.media.MediaPlayer;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

public class GIFWallpaperService extends WallpaperService
{
    protected static int                playheadTime = 0;

    @Override
    public Engine onCreateEngine()
    {
        return new VideoEngine();
    }

    class VideoEngine extends Engine
    {

        private final String        TAG     = getClass().getSimpleName();
        private final MediaPlayer   mediaPlayer;
        public VideoEngine()
        {
            super();
            Log.i( TAG, "( VideoEngine )");
            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.vid);
            mediaPlayer.setLooping(true);
        }

        @Override
        public void onSurfaceCreated( SurfaceHolder holder )
        {
            Log.i( TAG, "onSurfaceCreated" );
            mediaPlayer.setSurface(holder.getSurface());
            mediaPlayer.start();
        }

        @Override
        public void onSurfaceDestroyed( SurfaceHolder holder )
        {
            Log.i( TAG, "( INativeWallpaperEngine ): onSurfaceDestroyed" );
            playheadTime = mediaPlayer.getCurrentPosition();
            mediaPlayer.reset();
            mediaPlayer.release();
        }
    }

}
