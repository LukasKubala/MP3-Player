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

    private Button playbtn, pausebtn;
    private MediaPlayer mediaPlayer;

    //VolumeBar
    private SeekBar volume_bar;
    private AudioManager audioManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab1_einzel);


        //Initialisierung Buttons Play
        playbtn = (Button) findViewById(R.id.Play);
        pausebtn = (Button) findViewById(R.id.Pause);

        //Instanz mediaPlayer
        mediaPlayer=MediaPlayer.create(this, R.raw.lied1);

        //Listener
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Musik_Wiedergabe.this, "Es wird abgespielt", Toast.LENGTH_SHORT).show();
                buttonsState(false,true);
                mediaPlayer.start();
            }
        });

        pausebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Musik_Wiedergabe.this, "Pause", Toast.LENGTH_SHORT).show();
                buttonsState(true, false);
                mediaPlayer.pause();
            }
        });

        //Volumebar
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initControls();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Wenn Wiedergabe zu Ende
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(Musik_Wiedergabe.this, "Fertig", Toast.LENGTH_SHORT).show();
                buttonsState(true, false);
            }
        });

    }
    //wechseln zw. Zustand der Buttons play und pause
    private void buttonsState(boolean playB, boolean pauseB) {
        playbtn.setEnabled(playB);
        pausebtn.setEnabled(pauseB);
    }

    //für Volumebar
    private void initControls() {
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
