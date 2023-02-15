package me.yamp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import me.yamp.events.OnClickMusicButton;
import me.yamp.events.OnSeekBarChange;
import me.yamp.managers.PlayerManager;
import me.yamp.managers.QueueManager;

public class MainActivity extends AppCompatActivity {
    public QueueManager queueManager;
    public PlayerManager playerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queueManager = new QueueManager(getApplicationContext());
        queueManager.loadQueue();

        playerManager = new PlayerManager(this);

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChange(this));

        for (int i = 0; i < queueManager.queue.length; i++) {
            Music m = queueManager.queue[i];

            Button buttonView = new Button(this);
            buttonView.setText(m.name);
            buttonView.setOnClickListener(new OnClickMusicButton(this, i));

            LinearLayout lay = findViewById(R.id.musiclist);
            lay.addView(buttonView);
        }
    }

    public void updatePlaying(String name) {
        TextView playingText = findViewById(R.id.musicPlayingText);
        playingText.setText(name);
    }

    public void updateSeekBarPosition(int position) {
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setProgress(position, true);
    }

    public void setSeekBarMax(int maxDuration) {
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(maxDuration);
    }
}