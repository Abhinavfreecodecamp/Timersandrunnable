package com.example.android.timersandrunnable;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timercontrol;
    TextView timertextview;
    Button go;
    CountDownTimer ct;
    boolean countdown = false;
    public void updatetimer(int secondsleft){
        int min = (int)secondsleft/60;
        int sec = secondsleft-min*60;
        String sec1 = Integer.toString(sec);
        if (sec1.length()==1){
            sec1 = "0"+sec1;
        }
        timertextview.setText(min+":"+sec1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timercontrol =(SeekBar)findViewById(R.id.seekBar);
        timertextview = (TextView)findViewById(R.id.textView);
        go = (Button)findViewById(R.id.timerbutton);
        timercontrol.setMax(600);
        timercontrol.setProgress(0);

        timercontrol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updatetimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countdown == false) {
                    countdown =true;
                    timercontrol.setEnabled(false);
                    go.setText("stop");
                    ct = new CountDownTimer(timercontrol.getProgress() * 1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            updatetimer((int) millisUntilFinished / 1000);
                        }

                        @Override
                        public void onFinish() {
                            timercontrol.setEnabled(true);
                            timercontrol.setProgress(0);
                            go.setText("stop");
                        }
                    };
                    ct.start();
                }
                else{
                    countdown = false;
                    go.setText("Go!");
                    timercontrol.setEnabled(true);
                    timertextview.setText("0:00");
                    timercontrol.setProgress(0);
                    ct.cancel();
                }
            }
        });


    }

}
