package com.mp3.analukpat.mp3_player;
//Anzeige aller Titel
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import static android.os.Environment.DIRECTORY_MUSIC;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class Tab1_Titel extends Fragment {

    TextView textView;
    ListView listViewTitel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab1_titel, container, false);

       textView = rootView.findViewById(R.id.section_label);

       //listview
       listViewTitel = rootView.findViewById(R.id.listViewTitel);


       //Dieser Pfad wird durchsucht: storage/emulated/0/Music/
        //Das ist nicht die externe SD Karte, sondern der interne Speicher!
        pfad_durchsuchen(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)+"");


        final ListIterator<File> liedIterator = titel_liste.listIterator(0);

        for(int i = 0; i < titel_liste.size(); i ++){
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(titel_liste.get(i).getAbsolutePath());


            String albumName = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            String titleAuthor = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_AUTHOR);
            String titleName = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);


            textView.setText(textView.getText() + " <<" + titleName + " by " + titleAuthor + ">>");


        }
        return rootView;
    }


    ArrayList<File> titel_liste = new ArrayList<>();
    public void pfad_durchsuchen(String pfad) {
        File rootFolder = new File(pfad);
        File[] datei_liste;
        datei_liste = rootFolder.listFiles();
        //Toast.makeText(this.getContext(), datei_liste[1] + "test", Toast.LENGTH_SHORT).show();
        //textView.setText(textView.getText() + "Ordner wurde erstellt");

        if (datei_liste != null) {
            //textView.setText(textView.getText() + "<datei_liste ist nicht null>" + datei_liste.length);
            for (int i = 0; i < datei_liste.length; i++) {
                if (datei_liste[i].isDirectory()) {
                    //textView.setText(textView.getText() + "Im Ordner wurde ein Ordner gefunden ");
                    pfad_durchsuchen(datei_liste[i].getAbsolutePath());
                } else {
                    if (datei_liste[i].getName().endsWith(".mp3")){
                        titel_liste.add(datei_liste[i]);
                        Toast.makeText(this.getContext(), datei_liste[i] + "test", Toast.LENGTH_SHORT).show();
                        //textView.setText(textView.getText() + "Eine MP3 wurde gefunden und der Liste hinzugefügt" );
                        //textView.setText(textView.getText() + "<" + datei_liste[i].getName() + ">");
                        //textView.setText(textView.getText() + "<"+datei_liste[i]+">");
                    }
                }
                //textView.setText(textView.getText() + ">>forSchleife ist vorbei<<");
            }
        }
        else{
            //textView.setText(textView.getText() + "Der angegebene Pfad enhält keine Dateien");
        }
    }


}
