package me.yamp;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import me.yamp.events.OnClickMusicButton;
import me.yamp.managers.QueueManager;

public class MainActivity extends AppCompatActivity {
    public MediaPlayer mediaPlayer;
    public QueueManager queueManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = new MediaPlayer();
        AudioAttributes att = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        mediaPlayer.setAudioAttributes(att);

        queueManager = new QueueManager(getApplicationContext());
        queueManager.loadQueue();

        for (int i = 0; i < queueManager.queue.length; i++) {
            Music m = queueManager.queue[i];

            Button buttonView = new Button(this);
            buttonView.setText(m.name);
            buttonView.setOnClickListener(new OnClickMusicButton(this, i));

            LinearLayout lay = findViewById(R.id.musiclist);
            lay.addView(buttonView);
        }
    }
}