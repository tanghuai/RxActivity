package me.breakor.rx.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import me.breakor.rx.activity.lib.RxActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button1).setOnClickListener {
            RxActivity.with(this).addIntent(Intent().apply {
                putExtra("param", "abc")
            }).startActivity(SecondActivity::class.java)
                    .subscribe {
                        Snackbar.make(window.decorView, "Received Result:$it", Snackbar.LENGTH_SHORT).show()
                    }
        }
    }
}