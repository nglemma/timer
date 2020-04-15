package com.example.egg_timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    TextView timerview;
    Button timerbutton;
    SeekBar timerseekBar;
    boolean change=true;
    CountDownTimer countdown;
    public void startbutton(View view)
    {
        if(change==true)
        {
            timerseekBar.setEnabled(false);
            timerbutton.setText("Stop");
           countdown = new CountDownTimer(timerseekBar.getProgress() * 1000+100, 1000) {
                public void onTick(long timeinseconds) {
                    timeContoller((int) timeinseconds / 1000);
                }

                public void onFinish() {
                    MediaPlayer media = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    media.start();
                    timerbutton.setText("Start");
                }
            }.start();
            change=false;
        }
        else
            {
                countdown.cancel();
                int defaut =30;
                timerseekBar.setEnabled(true);
                timerbutton.setText("Start");
                timeContoller(defaut);
                change=true;
            }
    }

    public void timeContoller(int prog)
    {
        int minutes=prog/60;
        int seconds=prog-(minutes*60);
        String newseconds=Integer.toString(seconds);

        if(newseconds.equals("0"))
        {
            newseconds="00";
        }
        timerview.setText(Integer.toString(minutes)+":"+newseconds);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerseekBar = findViewById(R.id.timerseekBar);
        timerview = findViewById(R.id.timerview);
        timerbutton= findViewById(R.id.timerbutton);

        timerseekBar.setMax(600);
        timerseekBar.setProgress(30);

        timerseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {

                timeContoller(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }
}
