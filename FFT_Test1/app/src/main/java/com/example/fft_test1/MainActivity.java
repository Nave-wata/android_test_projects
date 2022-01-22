package com.example.fft_test1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import org.jtransforms.fft.DoubleFFT_1D;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int FFT_SIZE = 8 ;
        double[] data ;
        data = new double[FFT_SIZE * 2];

        // オリジナルデータを出力
        for ( int i=0; i<FFT_SIZE; i++ ) {
            data[i] = 10.0 + i ;
            Log.d("ORG", "i=" + i + " val=" +data[i] ) ;
        }

        DoubleFFT_1D fft = new DoubleFFT_1D(FFT_SIZE) ;

        // フーリエ変換(FFT)の実行
        fft.realForward(data);
        // data[0]は実数成分、data[1]は虚数成分～data[n]は実数成分、data[n+1}は虚数成分
        for ( int i=0; i<FFT_SIZE; i++ ) {
            // フーリエ変換後のデータを出力
            Log.d("FFT", "i=" + i + " val=" + data[i] ) ;
        }

        // 逆フーリエ変換
        fft.realInverse(data,  true) ;
        for ( int i=0; i<FFT_SIZE; i++ ) {
            // 逆フーリエ変換後のデータを出力
            Log.d("IFFT", "i=" + i + " val=" + data[i] ) ;
        }
    }

}