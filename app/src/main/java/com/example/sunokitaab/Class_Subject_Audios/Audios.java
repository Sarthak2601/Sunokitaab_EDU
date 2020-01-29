package com.example.sunokitaab.Class_Subject_Audios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sunokitaab.LoginActivity;
import com.example.sunokitaab.MainUi;
import com.example.sunokitaab.R;
import com.example.sunokitaab.rss.FeedAdapter;
import com.example.sunokitaab.rss.HTTPDataHandler;
import com.example.sunokitaab.rss.RSSObject;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Audios extends AppCompatActivity implements FeedAdapter.OnChapterListener {

    //Toolbar toolbar;
    RecyclerView recyclerView;
    RSSObject rssObject;
    FeedAdapter adapter;

    String url_title;
    File file;
    Button download;


    private ApiKey_handler apiKeyHandler = new ApiKey_handler();
    private boolean flag;
    private int ste;
    private MediaPlayer mediaPlayer;
    private SeekBar seekbar;
    private Button play;
    private Button pause;
    private ImageButton btn_frev , btn_ffwd;
    private TextView audioname;
    private boolean seekPressed;

    private String curSong = "";
    private String curTitle = "";
    private String textSetter = "";

    private long startTimesecs = 0;
    private long finalTimesecs = 0;

    private int forwardTime = 20000;
    private int backwardTime = 10000;

    private TextView time;
    private TextView duration;

    private Handler myHandler = new Handler();


    //API
    private final String RSS_to_Json_API = "https://api.rss2json.com/v1/api.json?rss_url="; //rss2jsonapi

    //feedtojson
//    private final String RSS_to_Json_API = "https://feed2json.org/convert?url=https%3A%2F%2Fwww.podcasts.com%2Frss_feed%2F";

    private final String API_KEY = "rxgdkvedigso5ufz2orgvm99jhmuimybnnfiizf8";
    private final String FEED_ITEMS_COUNT = "90";
    //RSS link
    private static String RSS_link="";
    String RSS = "";
/*
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_class6__english);
//
//        Bundle bundle = getIntent().getExtras();
//        if(bundle != null) {
//            RSS_link = bundle.getString("rss");
//        }
//        else{
//            Toast.makeText(this, "RSS NOT RECEIVED", Toast.LENGTH_SHORT).show();
//        }
//        //Toast.makeText(this, "" + RSS_link, Toast.LENGTH_SHORT).show();
//        //Intent intent = getIntent();
//        //RSS_link = intent.getStringExtra("rss");
//        recyclerView = findViewById(R.id.class6EnglishRecycler);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//       // adapter = new FeedAdapter(rssObject,getBaseContext());
//        //recyclerView.setAdapter(adapter);
//
//        loadRSS();
//
//    }
    */

    private void loadRSS() {
        AsyncTask<String,String,String> loadRSSAsync = new AsyncTask<String, String, String>() {

            ProgressDialog mDialog = new ProgressDialog(Audios.this);

            @Override
            protected void onPreExecute() {
                mDialog.setMessage("Please wait...");
                mDialog.show();
            }

            @Override
            protected String doInBackground(String... strings) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                Log.e("Debug", "DoInBAck");
                result = http.GetHTTPData(strings[0]);
                return result;

            }

            @Override
            protected void onPostExecute(String s) {
                mDialog.dismiss();
                rssObject = new Gson().fromJson(s,RSSObject.class);
                Log.e("Debug", "onPostExecute");
                FeedAdapter adapter = new FeedAdapter(rssObject,getApplicationContext(), Audios.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };

        StringBuilder url_get_data = new StringBuilder(RSS_to_Json_API);
        url_get_data.append(RSS_link);
//        Log.d("api" , url_get_data.toString());
        url_get_data.append("&api_key=");
        url_get_data.append(apiKeyHandler.route(RSS_link.substring(RSS_link.length()-1)));
        url_get_data.append("&count=");
        url_get_data.append(FEED_ITEMS_COUNT);
        loadRSSAsync.execute(url_get_data.toString());
    }


    @Override
    public void onChapterClick(String url, String title) {
        Log.d("media_links", "normalizer");
        Log.d("media_links", url);
        if (mediaPlayer!=null){
            mediaPlayer.reset();
        }

        curSong = url;
        audioname.setText(curTitle);
        curTitle = title;
        ste = 0;
        Toast.makeText(getApplicationContext(),"Buffering",Toast.LENGTH_SHORT).show();
        start();
    }



    public static int oneTimeOnly = 0;

    public void loadAudio(String url){
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync(); // might take long! (for buffering, etc)

        Log.d("mediaplayer", "mediaplayer preparing async");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class6__english);

//        seekPressed = false;

        Log.d("lifecycle", mediaPlayer==null ? "null" : "exists");
        Log.d("lifecycle", play==null? "buttons null": "buttons exist");
        Log.d("lifecycle", curTitle.equals("")? "url dead": "url alive");

        seekbar = (SeekBar)findViewById(R.id.seekBarmain);
        seekbar.setClickable(false);
        download = findViewById(R.id.downloadContent);

        ste = 0;
        play = (Button) findViewById(R.id.btn_play);
        pause = (Button) findViewById(R.id.btn_pause);
        btn_ffwd = (ImageButton) findViewById(R.id.btn_ffwd);
        btn_frev = (ImageButton) findViewById(R.id.btn_frev);

        duration = (TextView) findViewById(R.id.duration);
        time = (TextView) findViewById(R.id.time);

        audioname = (TextView) findViewById(R.id.tv_audioname);


        if (mediaPlayer!=null) {
            if (mediaPlayer.isPlaying()) {
                pause.setVisibility(View.VISIBLE);
                play.setVisibility(View.GONE);
            }
        } else {
            pause.setVisibility(View.GONE);
            play.setVisibility(View.VISIBLE);
            ste = 0;
        }


        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            RSS_link = bundle.getString("rss");
        }
        else{
            Toast.makeText(this, "RSS NOT RECEIVED", Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this, "" + RSS_link, Toast.LENGTH_SHORT).show();
        //Intent intent = getIntent();
        //RSS_link = intent.getStringExtra("rss");
        recyclerView = findViewById(R.id.class6EnglishRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // adapter = new FeedAdapter(rssObject,getBaseContext());
        //recyclerView.setAdapter(adapter);

        loadRSS();


        Log.d("mediaplayer", "mediaplayer block");


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer != null) {
                    mediaPlayer.start();
//                    myHandler.postDelayed(UpdateSongTime, 100);
                    pause.setVisibility(View.VISIBLE);
                    play.setVisibility(View.GONE);
                }
            }
        });


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Pausing",Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
                pause.setVisibility(View.GONE);
                play.setVisibility(View.VISIBLE);
            }
        });


        btn_ffwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTimesecs;

                if((temp+forwardTime)<=finalTimesecs){
                    startTimesecs = startTimesecs + forwardTime;
                    mediaPlayer.seekTo((int) startTimesecs);
//                    Toast.makeText(getApplicationContext(),"ffwd",Toast.LENGTH_SHORT).show();
                }else{
//                    Toast.makeText(getApplicationContext(),"Cannot jump forward 5 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_frev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTimesecs;

                if((temp-backwardTime)>0){
                    startTimesecs = startTimesecs - backwardTime;
                    mediaPlayer.seekTo((int) startTimesecs);
//                    Toast.makeText(getApplicationContext(),"frev",Toast.LENGTH_SHORT).show();
                }else{
//                    Toast.makeText(getApplicationContext(),"Cannot jump backward 5 seconds",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void start() {


        if(ste == 0) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            ste = 1;
        }else{
            mediaPlayer.reset();
        }
        try {
            mediaPlayer.setDataSource(curSong);
            audioname.setText(curTitle);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync(); // might take long! (for buffering, etc)

        Log.d("mediaplayer", "mediaplayer preparing async");
//                  loadAudio("http://awscdn.podcasts.com/Ch-16-Swami-Teoonram-Ji-Part-1-a832.mp3");
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d("mediaplayer", "mediaplayer starting");

                finalTimesecs = mediaPlayer.getDuration();
                startTimesecs = mediaPlayer.getCurrentPosition();


                Log.d("mediaplayer", "durations found");


                    seekbar.setMax((int) finalTimesecs);

                seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    int progressChangedValue = 0;

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        seekPressed = true;
                        Log.d("seekbar", "seekpressed set true 1");
                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser) {
                        progressChangedValue = progress;
                        if (mediaPlayer != null) {

                            textSetter = String.format("%d", TimeUnit.MILLISECONDS.toSeconds((long) progressChangedValue) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                            toMinutes((long) progressChangedValue)));

                            textSetter = textSetter.length() == 1 ? "0" + textSetter : textSetter;

                            time.setText(String.format("%d:%s",
                                    TimeUnit.MILLISECONDS.toMinutes((long) progressChangedValue),
                                    textSetter));

                        }

//                        Log.d("mediaplayer", "textsetter  is at "+ textSetter );
//                                Toast.makeText(Audios.this, "Seek bar progress is :" + progressChangedValue,
//                                        Toast.LENGTH_SHORT).show();

                        textSetter = "";
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        seekchangeTo(progressChangedValue);
                        seekPressed = false;
                        Log.d("seekbar", "seekpressed set false 2");
                    }
                });


                Log.d("mediaplayer", "if block");
                Log.d("mediaplayer", Long.toString(finalTimesecs));
                Log.d("mediaplayer", Long.toString(startTimesecs));


                //duration setText
//                        textSetter = Long.toString(finalTimesecs/60) + ":" + Long.toString(finalTimesecs%60);
//                        duration.setText(textSetter);
//                        textSetter = "";

                duration.setText(String.format("%d:%d",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTimesecs),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTimesecs) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTimesecs)))
                );
                time.setText(String.format("%d:%d",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTimesecs),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTimesecs) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTimesecs)))
                );


                mp.start();
                Log.d("mediaplayer", "times set");
                seekbar.setProgress((int)startTimesecs);
                seekPressed = false;
                Log.d("seekbar", "seekpressed set false 3");
                myHandler.postDelayed(UpdateSongTime,100);
                pause.setVisibility(View.VISIBLE);
                play.setVisibility(View.GONE);
            }
        });
    }

    private void seekchangeTo(int progressChangedValue) {
        if(mediaPlayer!=null){
            mediaPlayer.seekTo(progressChangedValue);
        }
    }


    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            Log.d("seekbar", !seekPressed ? "false" : "true");
            if (mediaPlayer != null && !seekPressed) {
                startTimesecs = mediaPlayer.getCurrentPosition();
                textSetter = String.format("%d",TimeUnit.MILLISECONDS.toSeconds((long) startTimesecs) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                toMinutes((long) startTimesecs)));

                textSetter = textSetter.length() == 1 ? "0"+ textSetter : textSetter;

                time.setText(String.format("%d:%s",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTimesecs),
                        textSetter));
                textSetter = "";

                seekbar.setProgress((int) startTimesecs);
                myHandler.postDelayed(this, 100);
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        ste = 0;
        oneTimeOnly = 0;
        super.onPause();
    }

//    protected void onStop(){
//
//        super.onStop();
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logOut:
                AuthUI.getInstance().signOut(Audios.this)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startActivity(new Intent(Audios.this, LoginActivity.class));
                                finish();
                                Toast.makeText(Audios.this, "Logged Out", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Audios.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle","onStop invoked");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle","onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        Log.d("lifecycle","onDestroy invoked");
    }


    public void DownloadMedia(){
        Bundle extras = getIntent().getExtras();
        url_title =  extras.getString("link");
        file = new File(Environment.getExternalStorageDirectory() + "/SunoKitaab/"+ url_title);
    }

}

