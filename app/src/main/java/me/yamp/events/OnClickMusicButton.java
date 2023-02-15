package me.yamp.events;

import android.view.View;

import me.yamp.MainActivity;

public class OnClickMusicButton implements View.OnClickListener {
    MainActivity appCtx;
    int musicId;
    public OnClickMusicButton(MainActivity ctx, int id) {
        appCtx = ctx;
        musicId = id;
    }

    @Override
    public void onClick(View v) {
        appCtx.playerManager.playMusic(musicId);
    }
}
