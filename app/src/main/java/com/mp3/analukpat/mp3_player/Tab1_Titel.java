package com.mp3.analukpat.mp3_player;
//Anzeige aller Titel
import android.content.ContentResolver;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    private ArrayList<Lied> LiedListe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab1_titel, container, false);

       textView = rootView.findViewById(R.id.section_label);




       //Dieser Pfad wird durchsucht: storage/emulated/0/Music/
        //Das ist nicht die externe SD Karte, sondern der interne Speicher!
        //pfad_durchsuchen(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)+"");


        findeLieder();
        ArrayList<String> Liste_der_Titel_als_Strings = new ArrayList<>();

       // for(int i = 0; i < LiedListe.size(); i ++){
            //MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            //mmr.setDataSource(titel_liste.get(i).getAbsolutePath());

            //textView.setText(textView.getText()+ "" + LiedListe);
            //String albumName = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            //String titleAuthor = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_AUTHOR);
            //String titleName = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);

            //Liste_der_Titel_als_Strings.add(titleName);

            //textView.setText(textView.getText() + " <<" + titleName + " by " + titleAuthor + ">>");


      //  }

        //in der onCreate Methode wird der ArrayAdapter erzeugt
        /*ArrayAdapter<String> Titel_View_Adapter =
                new ArrayAdapter<>(
                        getActivity(), // Die aktuelle Umgebung
                        R.layout.list_item_titel, // ID der XML-Layout Datei der einzelnen Daten der ListView
                        R.id.list_item_titel_textview, // ID der TextView
                        Liste_der_Titel_als_Strings); // Daten in einer ArrayList*/

        AnzeigeAdapter AnzAdap = new AnzeigeAdapter(LiedListe,this.getContext());
        listViewTitel = rootView.findViewById(R.id.listViewTitel);
        listViewTitel.setAdapter(AnzAdap);
        //listview
        //listViewTitel = rootView.findViewById(R.id.listViewTitel);
        //listViewTitel.setAdapter(Titel_View_Adapter);

        return rootView;
    }


   /* ArrayList<File> titel_liste = new ArrayList<>();
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
                        //Toast.makeText(this.getContext(), datei_liste[i] + "test", Toast.LENGTH_SHORT).show();
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
    }*/


// diese Methode findet alle Lieder, die extern verfügbar sind und erstellt eine Liste
    public void findeLieder(){
        LiedListe= new ArrayList<Lied>();

        ContentResolver musicResolver = this.getContext().getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor LiedCursor = musicResolver.query(musicUri, null, null, null, null);

        if(LiedCursor!=null && LiedCursor.moveToFirst()){
            int titelcol = LiedCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idcol = LiedCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int interpretcol = LiedCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);

            do {

                long konId = LiedCursor.getLong(idcol);
                String konTitel = LiedCursor.getString(titelcol);
                String konInterpret = LiedCursor.getString(interpretcol);

                LiedListe.add(new Lied(konId,konTitel,konInterpret));

            }
            while (LiedCursor.moveToNext());
        }
    }




}
