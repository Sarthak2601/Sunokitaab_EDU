package com.example.sunokitaab.Download_Audio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sunokitaab.R;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

public class DownloadedFragment extends Fragment {

    private ListView listView;
    private String songNames[];
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_downloaded, null);

        listView = rootview.findViewById(R.id.list);
        context = getContext().getApplicationContext();

        File file = new File(Environment.getExternalStorageDirectory() + "/SunoKitaab/");

        final ArrayList<File> songs = readSongs(file);

        songNames = new String[songs.size()];
        for(int i =0; i <songs.size(); ++i){
            songNames[i] = songs.get(i).getName().toString().replace(".mp3","");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,R.layout.songs_layout,R.id.textView,songNames );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putStringArray("songNames",songNames);
                startActivity(
                        new Intent(getActivity(), downloadAudioPlayer.class)
                            .putExtra("position", position)
                            .putExtra("list",songs));

            }
        });

        return rootview;


    }

    private ArrayList<File> readSongs(File root){
        ArrayList<File> arrayList = new ArrayList<File>();
        File files[] = root.listFiles();

        for(File file : files){
            if(file.isDirectory()){
                arrayList.addAll(readSongs(file));
            }
            else {
                if(file.getName().endsWith(".mp3")){
                    arrayList.add(file);
                }
            }
        }

        return arrayList;
    }
}
