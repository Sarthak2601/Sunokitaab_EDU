package com.example.sunokitaab.Download_Audio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.sunokitaab.R;

import java.io.File;
import java.util.ArrayList;

public class downloadAudioPlayer extends AppCompatActivity implements View.OnClickListener {

    TextView tv_audioname;
    Button btnPausePlay;
    ImageButton btnfwd, btnbwd;
    SeekBar seekBar;
    static MediaPlayer mediaPlayer;
    Handler handler;
    Runnable runnable;
    TextView time,duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_audio_player);

        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        Bundle bundle = getIntent().getExtras();
        ArrayList<File> songs =(ArrayList) bundle.getParcelableArrayList("list");
        int position = bundle.getInt("position");

        Uri uri = Uri.parse(songs.get(position).toString());

        //String songNames[] = bundle.getStringArray("songNames");

        mediaPlayer = MediaPlayer.create(this, uri);

        tv_audioname = findViewById(R.id.tv_audioname);
        btnPausePlay = findViewById(R.id.btn_pauseee);
        btnfwd = findViewById(R.id.btn_ffwddd);
        btnbwd = findViewById(R.id.btn_prev);
        handler = new Handler();
        seekBar = findViewById(R.id.seekBar);
        time = findViewById(R.id.timee);
        duration = findViewById(R.id.durationnn);

        tv_audioname.setText(songs.get(position).getName().replace(".mp3", ""));

      //  mediaPlayer = MediaPlayer.create(this, Uri.parse());

        btnfwd.setOnClickListener(this);
        btnbwd.setOnClickListener(this);
        btnPausePlay.setOnClickListener(this);
        setTime();

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(mp.getDuration());
                mp.start();
                changeSeekBar();
                setTime();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
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

    private void setTime() {
        long songCurrentTime = mediaPlayer.getCurrentPosition();
        long songTotalTime = mediaPlayer.getDuration();

        long secondT = (songCurrentTime / 1000) % 60;
        long minuteT = (songCurrentTime / (1000 * 60)) % 60;

        long secondD = (songTotalTime / 1000) % 60;
        long minuteD = (songTotalTime / (1000 * 60)) % 60;

        String timerD = String.format("%02d:%02d",minuteD, secondD );
        String timerT = String.format("%02d:%02d",minuteT, secondT );


        time.setText(""+ String.valueOf(timerT));
        duration.setText(""+String.valueOf(timerD));
    }

    private void changeSeekBar() {
       seekBar.setProgress(mediaPlayer.getCurrentPosition());

       if(mediaPlayer.isPlaying()){
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pauseee:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnPausePlay.setText("Play");
                    setTime();
                }
                else {
                    mediaPlayer.start();
                    btnPausePlay.setText("Pause");
                    changeSeekBar();
                    setTime();
                }
                break;

            case R.id.btn_ffwddd:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
                setTime();
                break;

            case R.id.btn_prev:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);
                setTime();
                break;
        }
    }
}
