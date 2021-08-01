package com.cqc.mymediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String TAG = "MainActivityCQC";
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View play = findViewById(R.id.button1);
        View pause = findViewById(R.id.button2);
        View replay = findViewById(R.id.button3);
        View stop = findViewById(R.id.button4);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);
        stop.setOnClickListener(this);

        try {
            initPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initPlayer() throws IOException {
        if(mediaPlayer == null){
            mediaPlayer = new MediaPlayer();
            //create方法代替了setDataSource和setAudioStreamType和prepare()
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.music);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                try {
                    play();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case  R.id.button2:
                pause();
                break;
            case R.id.button3:
                replay();
                break;
            case R.id.button4:
                stop();
                break;
            default:
                break;
        }
    }

    private void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void replay() {
        if(mediaPlayer != null){
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
            Log.i(TAG,"REPLAY IS CALLED");
        }

    }

    private void pause() {
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    private void play() throws IOException {
        if (mediaPlayer != null){
            mediaPlayer.start();
        }else {
            initPlayer();
            play();
        }

    }

}