package com.example.insights

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView

class androidDevelopment1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_development1)
        val androidDevelopmentVideoView1 : VideoView =findViewById(R.id.androidDevelopmentVideoView1)
        val videoPath :String = "android.resource://" + getPackageName()+"/"+R.raw.android_development_1
        androidDevelopmentVideoView1.setVideoURI(Uri.parse(videoPath))
        androidDevelopmentVideoView1.setMediaController(MediaController(this))
        MediaController(this).setAnchorView(androidDevelopmentVideoView1)
    }
}