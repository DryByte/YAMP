package me.yamp.managers;

import static java.lang.Thread.sleep;

import android.media.AudioAttributes;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;

import me.yamp.MainActivity;
import me.yamp.Music;

public class PlayerManager implements Runnable {
    MainActivity appCtx;
    MediaPlayer mediaPlayer;
    Thread seekbarThread;

    public PlayerManager(MainActivity ctx) {
        appCtx = ctx;

        mediaPlayer = new MediaPlayer();
        AudioAttributes att = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        mediaPlayer.setAudioAttributes(att);

        seekbarThread = new Thread(this);
        seekbarThread.start();
    }

    public void playMusic(int id) {
        Music music = appCtx.queueManager.queue[id];

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
        }

        MediaMetadataRetriever mediaMetadata = new MediaMetadataRetriever();

        try {
            FileInputStream file = new FileInputStream(music.path);
            mediaPlayer.setDataSource(file.getFD());
            mediaMetadata.setDataSource(file.getFD());

            mediaPlayer.prepare();
            file.close();
        } catch (IOException e) {
            Log.w("YAMP", "Cant play it lol");
            return;
        }

        mediaPlayer.start();
        String durationMSString = mediaMetadata.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        appCtx.updatePlaying(music.name);
        appCtx.setSeekBarMax(Integer.parseInt(durationMSString)/1000);
    }

    public void jumpTo(int position) {
        mediaPlayer.seekTo(position);
    }

    public void run() {
        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (!mediaPlayer.isPlaying())
                continue;

            int currentPosition = mediaPlayer.getCurrentPosition()/1000;
            appCtx.updateSeekBarPosition(currentPosition);
            Log.w("YAMPPP", String.valueOf(currentPosition));
        }
    }
}
