package pe.edu.unsa.epis.idnplab0901

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class AudioPlayService: Service() {
    private lateinit var mediaPlayer: MediaPlayer

    companion object {
        const val FILE_NAME = "FILE_NAME"
        const val COMMAND = "COMMAND"
        const val ACTION_PLAY = "pe.edu.unsa.epis.idnplab0901.action.PLAY"
        const val ACTION_STOP = "pe.edu.unsa.epis.idnplab0901.action.STOP"
    }
    override fun onBind(intent: Intent): IBinder? {

        return null
    }


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val filename = intent.getStringExtra(FILE_NAME)
        val command = intent.getStringExtra(COMMAND)
        mediaPlayer = MediaPlayer()
        if (command == ACTION_PLAY) {
            playAudio(filename)
        } else if (command == ACTION_STOP) {
            stopAudio()
        }
        return START_STICKY
    }
    private fun playAudio(filename: String?) {
        if (filename!=null) {
            val assetFileDescriptor = assets.openFd(filename)
            mediaPlayer.setDataSource(
                assetFileDescriptor.fileDescriptor,
                assetFileDescriptor.startOffset,
                assetFileDescriptor.length)

            assetFileDescriptor.close()
            mediaPlayer.prepare()
            mediaPlayer.setVolume(1f, 1f)
            mediaPlayer.isLooping = false
            mediaPlayer.start()
        }
    }
    private fun stopAudio() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
    }
}