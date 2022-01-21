package com.example.audiorecordtest2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Build;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    final static int SAMPLING_RATE = 44100;
    private static final int PERMISSION_RECORD_AUDIO = 1;
    AudioRecord audioRec = null;
    AudioTrack player = null;
    Button btn = null;
    boolean bIsRecording = false;
    int bufSize = 1024;
    byte buf[] = new byte[bufSize];
    TextView mText;

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.button_id);
        btn.setOnClickListener(this);

        mText = (TextView)findViewById(R.id.textView);
        mText.setText("Hello");

        // バッファサイズの計算
        //bufSize = AudioRecord.getMinBufferSize(
        //        SAMPLING_RATE,
        //        AudioFormat.CHANNEL_CONFIGURATION_MONO,
        //        AudioFormat.ENCODING_PCM_16BIT);

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

        checkRecordable();
    }

    public Boolean checkRecordable(){
        if(!SpeechRecognizer.isRecognitionAvailable(getApplicationContext())) {
            //mAlert.setMessage(getString(R.string.speech_not_available));
            //mAlert.show();
            return false;
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
                return false;
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        byte buf[] = new byte[bufSize];
                        // TODO Auto-generated method stub
                        while (bIsRecording) {
                            // 録音データ読み込み
                            audioRec.read(buf, 0, buf.length);
                            player.write(buf, 0, buf.length);
                            Log.v("AudioRecord", "read " + buf.length + " bytes");
                        }
                        // 録音停止
                        Log.v("AudioRecord", "stop");
                        audioRec.stop();
                    }
                }).start();
                btn.setText(R.string.stop_label);
            }
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        audioRec.release();
    }
}