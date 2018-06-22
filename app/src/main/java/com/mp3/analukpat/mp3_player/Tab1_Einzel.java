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

import java.util.Timer;
import java.util.TimerTask;

public class Tab1_Einzel extends Fragment implements MediaPlayer.OnPreparedListener {

    private Button playbtn;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tab1_einzel, container, false);

        //Initialisierung der vertikalen VolumeBar mit custom Seekbar
        VerticalSeekBar vertical_volume_bar = (VerticalSeekBar) rootView.findViewById (R.id.volume_seekbar);
        vertical_volume_bar.setMax(10);


        //Initialisierung Buttons Play
        playbtn = (Button) rootView.findViewById(R.id.Play);


        //Instanz mediaPlayer
        mediaPlayer=MediaPlayer.create(this.getContext(), R.raw.lied1); // This.getContext() weil das ein Tab innerhalb einer anderen view ist
        mediaPlayer.setVolume(0,0);


        //Instanz der SeekBar, die den Musikfortschritt anzeigt
        final SeekBar fortschrittsbar = (SeekBar) rootView.findViewById(R.id.music_bar);

        //Variable, die den Titel des aktuell abgespielten Liedes speichert, diese wird dann an alle anderen Activities übergeben
        String beispielhafterLiedNameZurUebergabe = "beispielhafter Name eines Liedes";



        //OnSeekBarChangeListener für die veritcal_volume_bar
        vertical_volume_bar.setOnSeekBarChangeListener(new VerticalSeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volumeValue = (float) progress / 10;
                mediaPlayer.setVolume(volumeValue, volumeValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Listener für fortschrittsbar !! bei den Bars immer Min und Max setzen !! mp.seekTo spult das Lied auf eine bestimmte ms Zahl
        fortschrittsbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    fortschrittsbar.setMin(0);
                    fortschrittsbar.setMax(mediaPlayer.getDuration());
                    mediaPlayer.seekTo(progress);
                    fortschrittsbar.setProgress(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Listener für Play und Pause
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    Toast.makeText(rootView.getContext(), "Pause", Toast.LENGTH_SHORT).show();
                    mediaPlayer.pause();
                    playbtn.setBackground(ContextCompat.getDrawable(rootView.getContext(), R.drawable.play));
                }else if(!mediaPlayer.isPlaying()){
                Toast.makeText(rootView.getContext(), "Es wird abgespielt", Toast.LENGTH_SHORT).show();
                onPrepared(mediaPlayer);
                playbtn.setBackground(ContextCompat.getDrawable(rootView.getContext(), R.drawable.pause));}
            }
        });

        //Timer der alle 1000ms die Methode run() ausführt
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                fortschrittsbar.setMin(0);
                fortschrittsbar.setMax(mediaPlayer.getDuration());
                fortschrittsbar.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0, 1000);


        //Wenn Wiedergabe zu Ende
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(rootView.getContext(), "Fertig", Toast.LENGTH_SHORT).show();
                playbtn.setBackground(ContextCompat.getDrawable(rootView.getContext(), R.drawable.play));
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
