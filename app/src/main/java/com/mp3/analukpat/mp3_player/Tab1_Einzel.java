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
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.*;
import android.net.Uri;

import java.util.Timer;
import java.util.TimerTask;

public class Tab1_Einzel extends Fragment implements MediaPlayer.OnPreparedListener {

    private TextView current_time, total_time;
    private Button playbtn, stoppbtn, previousbtn, nextbtn, shufflebtn;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tab1_einzel, container, false);

        //Initialisierung der vertikalen VolumeBar mit custom Seekbar
        final VerticalSeekBar vertical_volume_bar = (VerticalSeekBar) rootView.findViewById (R.id.volume_seekbar);
        vertical_volume_bar.setMax(10);


        //Initialisierung der Buttons
        playbtn = (Button) rootView.findViewById(R.id.Play);
        stoppbtn = (Button) rootView.findViewById(R.id.Stopp);
        previousbtn = (Button) rootView.findViewById(R.id.Previous);
        nextbtn = (Button) rootView.findViewById(R.id.Next);
        shufflebtn = (Button) rootView.findViewById(R.id.Shuffle);

        //Initialisierung der TextViews
        current_time = (TextView) rootView.findViewById(R.id.tab1_einzel_current_duration);
        total_time = (TextView) rootView.findViewById(R.id.tab1_einzel_total_duration);

        //Versuch eine Liste von Liedern zu erstellen, später müsste sowas dann in die Klasse Wiedergabeliste?
        final ArrayList<Integer> wiedergabeliste = new ArrayList<>();
        wiedergabeliste.add(R.raw.lied1);
        wiedergabeliste.add(R.raw.lied2);
        wiedergabeliste.add(R.raw.lied3);
        wiedergabeliste.add(R.raw.lied4);
        final ListIterator<Integer> liedIterator = wiedergabeliste.listIterator(0);


        //Instanz mediaPlayer
        //mediaPlayer=MediaPlayer.create(this.getContext(), R.raw.lied1); // This.getContext() weil das ein Tab innerhalb einer anderen view ist
        mediaPlayer = MediaPlayer.create(this.getContext(), wiedergabeliste.get(0));
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

        //Listener für Stopp
        stoppbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(rootView.getContext(), "Stopp", Toast.LENGTH_SHORT).show();
                mediaPlayer.stop();
                playbtn.setBackground(ContextCompat.getDrawable(rootView.getContext(), R.drawable.play));
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(rootView.getContext(), wiedergabeliste.get(0));
                mediaPlayer.setVolume((float)vertical_volume_bar.getProgress()/10, (float)vertical_volume_bar.getProgress()/10);
                fortschrittsbar.setProgress(0);
                current_time.setText("0:00");
                total_time.setText("0:00");
            }
        });

        //Listener für Next
        nextbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        //Listener für Previous
        previousbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        //Instanz der MainActivity, da UI Änderungen nur von der MainActivity vorgenommen werden können
        //mit der Methode runOnUiThread können dann Methoden von der MainActivity ausgeführt werden lassen
        final WiedergabeActivity wdgact = new WiedergabeActivity();

        //Timer der alle 1000ms die Methode run() ausführt
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (mediaPlayer.isPlaying()) {
                    //kontinuierliches Bewegen der Bar um den Fortschritt des Liedes anzuzeigen
                    fortschrittsbar.setMin(0);
                    fortschrittsbar.setMax(mediaPlayer.getDuration());
                    fortschrittsbar.setProgress(mediaPlayer.getCurrentPosition());


                    //wdgact ist eine Instanz der WiedergabeActivity und direkt über dem Timer instanziiert
                    //alles was innerhalb dieser Methode steht wird nicht von Tab1_Einzel sondern von WiedergabeActivity ausgeführt
                    wdgact.runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            //Untere TextView, die anzeigt bei welcher Minute das aktuelle Lied ist
                            int umrechnungsekunden_current = (int)(mediaPlayer.getCurrentPosition()/1000);
                            int anzahlminuten_current = umrechnungsekunden_current/60;
                            int anzahlsekunden_current = umrechnungsekunden_current-anzahlminuten_current*60;
                            if(anzahlsekunden_current<10) {
                                current_time.setText(anzahlminuten_current + ":0" + anzahlsekunden_current);
                            }
                            else{
                                current_time.setText(anzahlminuten_current+":"+anzahlsekunden_current);
                            }
                            //Untere TextView, die anzeigt wie lang das abgespielte Lied insgesamt ist !!
                            // die Methode muss später woanders hin, hier wird sie unnötig oft ausgeführt
                            int umrechnungsekunden_total = (int)(mediaPlayer.getDuration()/1000);
                            int anzahlminuten_total = umrechnungsekunden_total/60;
                            int anzahlsekunden_total = umrechnungsekunden_total-anzahlminuten_total*60;
                            if(anzahlsekunden_total<10) {
                                total_time.setText(anzahlminuten_total + ":0" + anzahlsekunden_total);
                            }else{
                                total_time.setText(anzahlminuten_total+":"+anzahlsekunden_total);
                            }
                        }
                    });
                }else{
                }
            }
        },0, 1000);


        //Wenn Wiedergabe zu Ende
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                /*if(liedIterator.hasNext()){
                    mediaPlayer.stop();
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.selectTrack(0);
                    onPrepared(mediaPlayer);
                }else {*/
                    Toast.makeText(rootView.getContext(), "Fertig", Toast.LENGTH_SHORT).show();
                    playbtn.setBackground(ContextCompat.getDrawable(rootView.getContext(), R.drawable.play));
                    mediaPlayer.stop();
                //}
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
