package me.breakor.rx.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import me.breakor.rx.activity.lib.RxActivity

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireView().findViewById<Button>(R.id.button2).setOnClickListener {
            RxActivity.with(this).addIntent(Intent().apply {
                putExtra("param", "abc")
            }).startActivity(SecondActivity::class.java)
                .subscribe {
                    Snackbar.make(requireView(), "Received Result:$it", Snackbar.LENGTH_SHORT)
                        .show()
                }
        }
    }
}