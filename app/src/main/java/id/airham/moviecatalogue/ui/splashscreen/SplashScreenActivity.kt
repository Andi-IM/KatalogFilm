package id.airham.moviecatalogue.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import id.airham.moviecatalogue.databinding.ActivitySplashScreenBinding
import id.airham.moviecatalogue.ui.home.HomeActivity

/**
 *  Kelas ini merupakan splashScreen yang memunculkan informasi aplikasi
 *  activity ini akan sleep selama 2000ms
 */

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var activitySplashScreenBinding: ActivitySplashScreenBinding

    companion object {
        const val DELAY_TIME_IN_MILIS = 2000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(activitySplashScreenBinding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, HomeActivity::class.java))
            finish()
        }, DELAY_TIME_IN_MILIS.toLong())

    }
}