package com.cubivue.cubivuecontrols.controls.sound

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import com.cubivue.cubivuecontrols.utils.vibrate
import kotlin.math.ln

class BeeperControl(private val context: Context) {

    private val maxVolume = 100f
    private val tone = Uri.parse("android.resource://" + context.packageName + "/raw/beep")
    private val mediaPlayer = MediaPlayer().apply {
        val audioAttributes = AudioAttributes.Builder()
            .setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED)
            .setLegacyStreamType(AudioManager.STREAM_ALARM)
            .setUsage(AudioAttributes.USAGE_ALARM)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        setDataSource(context, tone)
        setAudioAttributes(audioAttributes)
        prepare()
    }

    fun play(volume: Int = maxVolume.toInt()) {
        if (!mediaPlayer.isPlaying) {
            val calculatedVolume = 1 - (ln(maxVolume - volume.toFloat()) / ln(maxVolume))
            mediaPlayer.setVolume(calculatedVolume, calculatedVolume)
            mediaPlayer.start()
            context.vibrate()
        }
    }
}
