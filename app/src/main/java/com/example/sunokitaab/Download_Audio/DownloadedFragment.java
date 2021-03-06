package com.example.sunokitaab.Download_Audio;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sunokitaab.MainActivity;
import com.example.sunokitaab.R;
import com.example.sunokitaab.Services.OnClearFromRecentService;
import com.example.sunokitaab.mediaPlayer_notification.CreateNotification;
import com.example.sunokitaab.mediaPlayer_notification.Playable;
import com.example.sunokitaab.mediaPlayer_notification.Track;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class DownloadedFragment extends Fragment implements Playable {

    private ListView listView;
    private String songNames[];
    private Context context;
    TextView tv_audioname;
    Button btnPausePlay;
    ImageButton btnfwd, btnbwd;
    SeekBar seekBar;
    //static MediaPlayer mediaPlayer;
    Handler handler;
    Runnable runnable;
    TextView time,duration;
    private String audioTitle;
    NotificationManager notificationManager;
    SimpleExoPlayer simpleExoPlayer;
    Boolean isPlaying;
    List<Track> list;
    int Pos = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_downloaded, null);
        isPlaying = false;
        audioTitle = "songName";

        if(simpleExoPlayer!=null){
            //simpleExoPlayer.stop();
            simpleExoPlayer.stop(true);
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
        tv_audioname = rootview.findViewById(R.id.tv_audioname);
        btnPausePlay = rootview.findViewById(R.id.btn_pauseee);
        btnfwd = rootview.findViewById(R.id.btn_ffwddd);
        btnbwd = rootview.findViewById(R.id.btn_prev);
        handler = new Handler();
        seekBar = rootview.findViewById(R.id.seekBar);
        time = rootview.findViewById(R.id.timee);
        duration = rootview.findViewById(R.id.durationnn);
        listView = rootview.findViewById(R.id.list);
        context = getContext().getApplicationContext();

        File file = new File(Environment.getExternalStorageDirectory() + "/SunoKitaab/");

        final ArrayList<File> songs = readSongs(file);

        songNames = new String[songs.size()];
        for(int i =0; i <songs.size(); ++i){
            songNames[i] = songs.get(i).getName().toString().replace(".mp3","");
        }

        populateTrack(file);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
            context.registerReceiver(broadcastReceiver,new IntentFilter("TRACKS_TRACKS"));
            context.startService(new Intent(context, OnClearFromRecentService.class));

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,R.layout.songs_layout,R.id.textView,songNames );
        listView.setAdapter(adapter);

        try {

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    if (simpleExoPlayer != null) {
                        simpleExoPlayer.stop();
                        simpleExoPlayer.release();
                    }

                    Uri uri = Uri.parse(songs.get(position).toString());


                    audioTitle = songs.get(position).getName().replace(".mp3", "");
                    tv_audioname.setText(audioTitle);
                    tv_audioname.setVisibility(View.VISIBLE);
                    Toast toast = Toast.makeText(context, "Press play to continue.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,0);
                    toast.show();
                    setupPlayer(uri);
                    isPlaying = true;
                    changeSeekBar();

                    //setTime();
                    //onTrackPlay();
                    //String songNames[] = bundle.getStringArray("songNames");
                    //mediaPlayer = MediaPlayer.create(getActivity(), uri);


                    btnfwd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            simpleExoPlayer.seekTo(simpleExoPlayer.getCurrentPosition() + 5000);
                            setTime();
                        }
                    });
                    btnbwd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            simpleExoPlayer.seekTo(simpleExoPlayer.getCurrentPosition() - 5000);
                            setTime();
                        }
                    });
                    btnPausePlay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            seekBar.setMax(Integer.parseInt(String.valueOf(simpleExoPlayer.getDuration())));
                            seekBar.setVisibility(View.VISIBLE);

                            if (isPlaying) {
                                simpleExoPlayer.setPlayWhenReady(false);
                                isPlaying = false;
                                onTrackPause();
                                btnPausePlay.setText("Play");
                                setTime();
                            } else {
                                simpleExoPlayer.setPlayWhenReady(true);
                                isPlaying = true;
                                btnPausePlay.setText("Pause");
                                changeSeekBar();
                                onTrackPlay();
                                setTime();
                            }
                        }
                    });
                    Pos = position;
                    if (isPlaying) {
                        onTrackPause();
                    } else {
                        onTrackPlay();
                    }
                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if(fromUser){
                                simpleExoPlayer.seekTo(progress);
                                setTime();

                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });

                }



            });



        }catch (Exception e){
            Toast.makeText(context, ""+ e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            Log.d("boom", "onCreateView: " + e.getMessage());
        }
        return rootview;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    private void setTime() {

        if(simpleExoPlayer!=null) {


            long songCurrentTime = simpleExoPlayer.getCurrentPosition();
            long songTotalTime = simpleExoPlayer.getDuration();

            long secondT = (songCurrentTime / 1000) % 60;
            long minuteT = (songCurrentTime / (1000 * 60)) % 60;

            long secondD = (songTotalTime / 1000) % 60;
            long minuteD = (songTotalTime / (1000 * 60)) % 60;

            String timerD = String.format("%02d:%02d", minuteD, secondD);
            String timerT = String.format("%02d:%02d", minuteT, secondT);


            time.setText("" + String.valueOf(timerT));
            duration.setText("" + String.valueOf(timerD));
        }

    }

    private void changeSeekBar() {


        if(simpleExoPlayer!=null){
            seekBar.setProgress(Integer.parseInt(String.valueOf(simpleExoPlayer.getCurrentPosition())));
            runnable = new Runnable() {
                @Override
                public void run() {
                    changeSeekBar();
                    setTime();
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //changeSeekBar();

        if(simpleExoPlayer!=null){


              // simpleExoPlayer.stop();
               simpleExoPlayer.stop(true);
               simpleExoPlayer.release();
               simpleExoPlayer = null;

        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationManager.cancelAll();
        }
        context.unregisterReceiver(broadcastReceiver);
    }

    public void populateTrack(File file){

        list = new ArrayList<>();

       for(int i = 0; i < readSongs(file).size() ; i++){
            try {
                list.add(new Track(songNames[i]));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

      // list.add(new Track("Track1"));
      // list.add(new Track("Track 2"));

    }

    private void createChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CreateNotification.CHANNEL_ID,"SunoKitaab", NotificationManager.IMPORTANCE_LOW);
            notificationManager = context.getSystemService(NotificationManager.class);

            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
                String action = intent.getExtras().getString("actionname");

                switch (action){
                    case CreateNotification.ACTION_PREVIOUS:
                        onTrackPrevious();
                        break;
                    case CreateNotification.ACTION_PLAY :
                        if(isPlaying){
                            onTrackPause();
                        }
                        else{
                            onTrackPlay();
                        }
                        break;
                    case CreateNotification.ACTION_NEXT:
                        onTrackNext();
                        break;
                }
        }
    };

    @Override
    public void onTrackPrevious() {

        Pos --;
        CreateNotification.createNotification(context, list.get(Pos), R.drawable.ic_pause_black_24dp,Pos,list.size() -1);
        tv_audioname.setText(list.get(Pos).getTitle());

    }

    @Override
    public void onTrackPlay() {

        CreateNotification.createNotification(context, list.get(Pos), R.drawable.ic_pause_black_24dp,Pos,list.size() -1);
        btnPausePlay.setText("Pause");
        tv_audioname.setText(list.get(Pos).getTitle());
        simpleExoPlayer.setPlayWhenReady(true);
        isPlaying = true;
        seekBar.setMax(Integer.parseInt(String.valueOf(simpleExoPlayer.getDuration())));
        seekBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void onTrackPause() {
        CreateNotification.createNotification(context, list.get(Pos), R.drawable.ic_play_arrow_black_24dp,Pos,list.size() -1);
        btnPausePlay.setText("Play");
        tv_audioname.setText(list.get(Pos).getTitle());
        simpleExoPlayer.setPlayWhenReady(false);
        isPlaying = false;
    }

    @Override
    public void onTrackNext() {
        Pos ++;
        CreateNotification.createNotification(context, list.get(Pos), R.drawable.ic_pause_black_24dp,Pos,list.size() -1);
        tv_audioname.setText(list.get(Pos).getTitle());
    }



    private void setupPlayer(Uri uri){

        DefaultRenderersFactory renderersFactory = new DefaultRenderersFactory(context,null,DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF);
        TrackSelector trackSelector = new DefaultTrackSelector();
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(renderersFactory,trackSelector); //Global

        String userAgent = Util.getUserAgent(context,"Sunokitaab");
        ExtractorMediaSource mediaSource =new ExtractorMediaSource(uri,
                new DefaultDataSourceFactory(context,userAgent),
                new DefaultExtractorsFactory(),
                null,
                null);

        simpleExoPlayer.prepare(mediaSource);//Local
        simpleExoPlayer.setPlayWhenReady(false);//Local

    }
}
