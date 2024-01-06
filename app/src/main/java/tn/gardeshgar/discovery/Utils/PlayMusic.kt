package tn.gardeshgar.discovery.Utils

import android.content.Context
import android.media.MediaPlayer
import tn.gardeshgar.discovery.R

class PlayMusic {
    var mMediaPlayer: MediaPlayer? = null

    fun SoundNotification(context: Context?) {
        mMediaPlayer = MediaPlayer.create(context, R.raw.soundialog)
        mMediaPlayer!!.start()
    }

}