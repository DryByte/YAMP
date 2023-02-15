package me.yamp.events;

import android.widget.SeekBar;

import me.yamp.MainActivity;

public class OnSeekBarChange implements SeekBar.OnSeekBarChangeListener {
    MainActivity appCtx;

    public OnSeekBarChange(MainActivity ctx) {
        appCtx = ctx;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            appCtx.playerManager.jumpTo(progress*1000);
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {}
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
