package com.example.audiorecordtest2;

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

public class MainActivity extends Activity implements View.OnClickListener {
    final static int SAMPLING_RATE = 11025;
    AudioRecord audioRec = null;
    Button btn = null;
    boolean bIsRecording = false;
    int bufSize;
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.button_id);
        btn.setOnClickListener(this);
        // バッファサイズの計算
        bufSize = AudioRecord.getMinBufferSize(
                SAMPLING_RATE,
                AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT) * 2;

        // AudioRecordの作成
        audioRec = new AudioRecord(
                MediaRecorder.AudioSource.MIC, SAMPLING_RATE,
                AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufSize);
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