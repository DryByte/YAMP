package me.yamp.managers;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import me.yamp.Music;

public class QueueManager {
    public Music[] queue;
    public int randomSeed;
    private Context appCtx;

    public QueueManager(Context ctx) {
        appCtx = ctx;
    }

    public void loadQueue() {
        String[] mProject = {MediaStore.MediaColumns.DISPLAY_NAME, MediaStore.MediaColumns.DATA};
        Cursor cursor = appCtx.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                mProject,
                null,
                null);

        int columnName = cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME);
        int columnPath = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);

        queue = new Music[cursor.getCount()];
        while(cursor.moveToNext()) {
            if (cursor.getPosition() < 0)
                continue;

            queue[cursor.getPosition()] = new Music(cursor.getString(columnName), cursor.getString(columnPath));
        }
    }
}
