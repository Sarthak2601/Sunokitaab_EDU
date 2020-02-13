package com.sunokitaab.sunokitaab.Download_Audio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sunokitaab.sunokitaab.R;

public class DownloadedMediaAdapter extends ArrayAdapter<String>{

    public static final String TAG = "downloadedAudioAdapter";

    private Context context;
    int mResource;

    public DownloadedMediaAdapter(@NonNull Context mcontext, int resource, String[] audios) {
        super(mcontext, resource, audios);
        context = mcontext;
        mResource = resource;
    }
//
//    @Override
//    public void onClick(View v) {
//
//    }

    public interface OndownloadedAudioClick{
        void OndownloadedAuioclick(String url, String title);
    }

    public interface OndownloadedAudioDelClick{
        void OnDownloadedAdioDelclick(String url, String title);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).toString();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(mResource,parent, false);

        TextView audioName = (TextView)  convertView.findViewById(R.id.downloaded_audioname);
        Button delButton = (Button) convertView.findViewById(R.id.delButton);
        audioName.setText(name);

//        delButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                OndownloadedAudioDelClick.
//            }
//        });


        return convertView;
    }


}
