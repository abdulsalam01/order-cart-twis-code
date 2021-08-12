package com.example.twiscode.ui

import android.app.Application
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

abstract class BaseApp: AppCompatActivity() {

    private var progressBar: ProgressBar? = null

    fun build(context: Context, r: Int): ProgressBar {
        if (this.progressBar === null) {
            this.progressBar = ProgressBar(context)
            this.progressBar!!.isIndeterminate = true

            val params = RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            val relativeLayout = findViewById<RelativeLayout>(r)

            params.addRule(RelativeLayout.CENTER_IN_PARENT)
            params.addRule(RelativeLayout.CENTER_HORIZONTAL)
            relativeLayout!!.addView(this.progressBar, params)
        }

        return this.progressBar!!
    }

    fun show() {
        this.progressBar!!.visibility = View.VISIBLE
    }

    fun hide() {
        this.progressBar!!.visibility = View.GONE
    }

    fun get(): ProgressBar {
        return this.progressBar!!
    }
}