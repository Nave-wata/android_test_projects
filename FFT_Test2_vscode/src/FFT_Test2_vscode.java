import org.jtransforms.fft.DoubleFFT_1D;

public class FFT_Test2_vscode {
    static int FFT_SIZE = 10;
    static double[] data = new double[FFT_SIZE];
    static double[] data2 = new double[FFT_SIZE];

    public static void main(String[] args) throws java.lang.NoClassDefFoundError {
        DoubleFFT_1D fft1 = new DoubleFFT_1D(5);
        DoubleFFT_1D fft2 = new DoubleFFT_1D(10);

        for (int i = 0; i < 5; i++) {
            data[i] = 10.0 + i;
            System.out.println("ORG:  i = " + i + ", val = " + data[i]);
        }
        System.out.println();

        // フーリエ変換(FFT)の実行
        fft1.realForwardFull(data);
        // data[0]は実数成分、data[1]は虚数成分～data[n]は実数成分、data[n+1]は虚数成分
        for (int i = 0; i < FFT_SIZE; i++) {
            // フーリエ変換後のデータを出力
            System.out.println("FFT:  i = " + i + ", val = " + data[i]);
        }
        System.out.println();

        // 逆フーリエ変換
        fft1.realInverseFull(data, true);
        for (int i = 0; i < FFT_SIZE; i++) {
            // 逆フーリエ変換後のデータを出力
            System.out.println("IFFT: i = " + i + ", val = " + data[i]);
        }
        System.out.println();
    }
}
