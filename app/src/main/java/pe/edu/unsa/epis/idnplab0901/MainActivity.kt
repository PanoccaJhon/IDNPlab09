package pe.edu.unsa.epis.idnplab0901

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnPlay = findViewById<ImageButton>(R.id.btnPlay)
        val btnStop = findViewById<ImageButton>(R.id.btnPause)

        btnPlay.setOnClickListener {
            val audioPlayServiceIntent = Intent(this, AudioPlayService::class.java)
            audioPlayServiceIntent.putExtra(AudioPlayService.FILE_NAME, "audio.mp3")
            audioPlayServiceIntent.putExtra(AudioPlayService.COMMAND, AudioPlayService.ACTION_PLAY)
            startService(audioPlayServiceIntent)
        }
        btnStop.setOnClickListener {
            val audioPlayServiceIntent = Intent(this, AudioPlayService::class.java)
            audioPlayServiceIntent.putExtra(AudioPlayService.COMMAND, AudioPlayService.ACTION_STOP)
            startService(audioPlayServiceIntent)
        }
    }
}