package com.mp3.analukpat.mp3_player;
// Widergabe eines einzelnen Liedes

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class Tab1_Einzel extends Fragment implements MediaPlayer.OnPreparedListener {

    private Button playbtn, pausebtn;
    private MediaPlayer mediaPlayer;

    //VolumeBar
    private SeekBar volume_bar;
    private AudioManager audioManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tab1_einzel, container, false);


        //Initialisierung Buttons Play
        playbtn = (Button) rootView.findViewById(R.id.Play);
        pausebtn = (Button) rootView.findViewById(R.id.Pause);

        //Instanz mediaPlayer
        mediaPlayer=MediaPlayer.create(this.getContext(), R.raw.lied1);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.prepareAsync();


        //Listener
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(rootView.getContext(), "Es wird abgespielt", Toast.LENGTH_SHORT).show();
                buttonsState(false,true);
                onPrepared(mediaPlayer);
            }
        });

        pausebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(rootView.getContext(), "Pause", Toast.LENGTH_SHORT).show();
                buttonsState(true, false);
                mediaPlayer.pause();
                //mediaPlayer.seekTo(0);
            }
        });

        //Wenn Wiedergabe zu Ende
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(rootView.getContext(), "Fertig", Toast.LENGTH_SHORT).show();
                buttonsState(true, false);
            }
        });
        return rootView;

    }
    //wechseln zw. Zustand der Buttons play und pause
    private void buttonsState(boolean playB, boolean pauseB) {
        playbtn.setEnabled(playB);
        pausebtn.setEnabled(pauseB);

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
    //public void onPrepared(MediaPlayer player){
    //    player.start();
    //}

}
