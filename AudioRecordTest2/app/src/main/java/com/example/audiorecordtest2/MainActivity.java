package com.example.audiorecordtest2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    final static int SAMPLING_RATE = 44100;
    private static final int PERMISSION_RECORD_AUDIO = 1;
    AudioRecord audioRec = null;
    AudioTrack player = null;
    Button btn = null;
    boolean bIsRecording = false;
    int bufSize = 1024;
    int seekBarMax = 8;
    int seekBarProgress = 0;
    double[] vol_ary = {0.2, 0.4, 0.6, 0.8, 1.0, 1.2, 1.4, 1.6, 1.8};
    String[] vol_str = {"-4", "-3", "-2", "-1", "0", "1", "2", "3", "4"};
    double vol = vol_ary[0];
    TextView mText;

    /** Called when the activity is first created. */

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button_id);
        btn.setOnClickListener(this);

        mText = findViewById(R.id.textView);
        mText.setText("Buffer Size = " + bufSize);

        // AudioRecordの作成
        audioRec = new AudioRecord(
                MediaRecorder.AudioSource.MIC,
                SAMPLING_RATE,
                AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufSize);

        // AudioTrackの作成
        player = new AudioTrack(
                AudioManager.STREAM_MUSIC,
                SAMPLING_RATE,
                AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufSize,
                AudioTrack.MODE_STREAM
                );

        // SeekBarのインスタンスを取得
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(seekBarMax);
        seekBar.setProgress(seekBarProgress);
        // SeekBarのつまみの変更を検知する
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // つまみが変更された時に処理が実行される
                mText.setText(vol_str[progress]);
                vol = vol_ary[progress];
                Log.v("SeekBar", "progress" + vol);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // ユーザーがタップ開始した時に処理が実行される
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // ユーザーがタップ終了した時に処理が実行される
            }
        });

        checkRecordable();
    }

    @SuppressLint("ObsoleteSdkInt")
    public void checkRecordable(){
        if(!SpeechRecognizer.isRecognitionAvailable(getApplicationContext())) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if(ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.RECORD_AUDIO
                        },
                        PERMISSION_RECORD_AUDIO);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btn) {
            if (bIsRecording) {
                btn.setText(R.string.start_label);
                bIsRecording = false;
            } else {
                // 録音開始
                Log.v("AudioRecord", "startRecording");
                player.play();
                audioRec.startRecording();
                bIsRecording = true;
                // 録音スレッド
                new Thread(() -> {
                    byte[] inputBuffer = new byte[bufSize];
                    byte[] outputBuffer;
                    while (bIsRecording) {
                        // 録音データ読み込み
                        audioRec.read(inputBuffer, 0, bufSize);
                        outputBuffer = Amplification(inputBuffer);
                        player.write(outputBuffer, 0, bufSize);
                        // Log.v("AudioRecord", "read " + bufSize + " bytes");
                    }
                    // 録音停止
                    Log.v("AudioRecord", "stop");
                    audioRec.stop();
                }).start();
                btn.setText(R.string.stop_label);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        audioRec.release();
    }

    protected byte[] Amplification(byte[] inputBuffer) {
        double[] buf = new double[bufSize];
        byte[] outputBuffer = new byte[bufSize];
        int i;

        for (i = 0; i < bufSize; i++) {
            buf[i] = (double)inputBuffer[i] * vol;
        }
        for (i = 0; i < bufSize; i++) {
            outputBuffer[i] = (byte)buf[i];
        }

        return outputBuffer;
    }
}