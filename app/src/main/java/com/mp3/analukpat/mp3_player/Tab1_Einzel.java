package com.mp3.analukpat.mp3_player;
// Widergabe eines einzelnen Liedes

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class Tab1_Einzel extends Fragment implements MediaPlayer.OnPreparedListener {

    private Button playbtn;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tab1_einzel, container, false);


        //VolumeBar
        SeekBar volume_bar = (SeekBar) rootView.findViewById(R.id.volume_bar);
        volume_bar.setMax(10);


        volume_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volumeValue = (float) progress / 10;
                System.out.print("Lautstärke: " + progress + " VolumeValue: " + volumeValue);
                mediaPlayer.setVolume(volumeValue, volumeValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });









        //Initialisierung Buttons Play
        playbtn = (Button) rootView.findViewById(R.id.Play);


        //Instanz mediaPlayer
        mediaPlayer=MediaPlayer.create(this.getContext(), R.raw.lied1); // This.getContext() weil das ein Tab innerhalb einer anderen view ist



        //Listener für Play und Pause
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    Toast.makeText(rootView.getContext(), "Pause", Toast.LENGTH_SHORT).show();
                    //buttonsState(true, false);
                    mediaPlayer.pause();
                    playbtn.setBackground(ContextCompat.getDrawable(rootView.getContext(), R.drawable.play));
                }else if(!mediaPlayer.isPlaying()){
                Toast.makeText(rootView.getContext(), "Es wird abgespielt", Toast.LENGTH_SHORT).show();
                //buttonsState(false,true);
                onPrepared(mediaPlayer);
                playbtn.setBackground(ContextCompat.getDrawable(rootView.getContext(), R.drawable.pause));}
            }
        });


        //Wenn Wiedergabe zu Ende
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(rootView.getContext(), "Fertig", Toast.LENGTH_SHORT).show();

            }
        });
        return rootView;

    }

// Player vorbereiten
    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.setOnPreparedListener(this);
        mp.start();
    }


}
