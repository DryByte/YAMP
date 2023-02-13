package me.yamp.events;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;

import java.io.FileInputStream;
import java.io.IOException;

import me.yamp.MainActivity;

public class OnClickMusicButton implements View.OnClickListener {
    MainActivity appCtx;
    MediaPlayer mediaPlayer;
    int musicId;
    public OnClickMusicButton(MainActivity ctx, int id) {
        appCtx = ctx;
        mediaPlayer = ctx.mediaPlayer;
        musicId = id;
    }

    @Override
    public void onClick(View v) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
        }

        try {
            FileInputStream file = new FileInputStream(appCtx.queueManager.queue[musicId].path);
            mediaPlayer.setDataSource(file.getFD());
            mediaPlayer.prepare();
            file.close();
        } catch (IOException e) {
            Log.w("YAMP", "Cant play it lol");
            return;
        }
        mediaPlayer.start();
    }
}
