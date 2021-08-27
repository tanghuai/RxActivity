package me.breakor.rx.activity.lib

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter

class RxActivity {

    companion object {

        fun with(activity: FragmentActivity): Builder {
            return Builder(activity)
        }

        fun with(fragment: Fragment): Builder {
            return Builder(fragment)
        }
    }

    class Builder private constructor() {

        private var fragment: Fragment? = null
        private var activity: FragmentActivity? = null

        constructor(fragment: Fragment) : this() {
            this.fragment = fragment
        }

        constructor(activity: FragmentActivity) : this() {
            this.activity = activity
        }

        private var intent: Intent? = null

        fun addIntent(intent: Intent): Builder {
            this.intent = intent
            return this
        }

        fun startActivity(clz: Class<out Activity>): Observable<Result> {
            if (intent == null) {
                intent = Intent()
            }
            return Observable.create {
                if (fragment != null) {
                    V4Fragment(intent!!.setClass(fragment!!.requireContext(), clz), it).apply {
                        this@Builder.fragment!!.requireActivity().supportFragmentManager.beginTransaction()
                            .add(this, "V4Fragment")
                            .commitAllowingStateLoss()
                    }
                } else {
                    V4Fragment(intent!!.setClass(activity!!, clz), it).apply {
                        this@Builder.activity!!.supportFragmentManager.beginTransaction()
                            .add(this, "V4Fragment")
                            .commitAllowingStateLoss()
                    }
                }
            }
        }
    }

    data class Result(
        var requestCode: Int,
        var resultCode: Int,
        var data: Intent?
    ) {
        fun isOk(): Boolean {
            return resultCode == Activity.RESULT_OK
        }
    }

    class V4Fragment(var intent: Intent, var emitter: ObservableEmitter<Result>) :
        Fragment() {

        override fun onAttach(context: Context) {
            super.onAttach(context)
            startActivityForResult(intent, 0)
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            emitter.onNext(Result(0, resultCode, data))
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }
    }
}