package com.mp3.analukpat.mp3_player;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class Musik_Wiedergabe extends AppCompatActivity {

    //VolumeBar
    private SeekBar volume_bar;
    private AudioManager audioManager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab1_einzel);


        //Volumebar
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initControls();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    //für Volumebar
    public void initControls() {
        try {
            volume_bar= findViewById(R.id.volume_bar);
            audioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volume_bar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volume_bar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
            volume_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }catch (Exception e){

        }



    }
}
