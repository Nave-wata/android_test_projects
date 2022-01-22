import org.jtransforms.fft.DoubleFFT_1D;

public class FFT_Test2_vscode {
    static int FFT_SIZE = 8;
    static double[] data = new double[FFT_SIZE];

    public static void main(String[] args) throws java.lang.NoClassDefFoundError {
        for (int i = 0; i < FFT_SIZE; i++) {
            data[i] = 10.0 + i;
            System.out.println("ORG:  i = " + i + ", val = " + data[i]);
        }
        System.out.println();

        DoubleFFT_1D fft = new DoubleFFT_1D(FFT_SIZE);

        // フーリエ変換(FFT)の実行
        fft.realForward(data);
        // data[0]は実数成分、data[1]は虚数成分～data[n]は実数成分、data[n+1]は虚数成分
        for (int i = 0; i < FFT_SIZE; i++) {
            // フーリエ変換後のデータを出力
            System.out.println("FFT:  i = " + i + ", val = " + data[i]);
        }
        System.out.println();

        // 逆フーリエ変換
        fft.realInverse(data, true);
        for (int i = 0; i < FFT_SIZE; i++) {
            // 逆フーリエ変換後のデータを出力
            System.out.println("IFFT: i = " + i + ", val = " + data[i]);
        }
        System.out.println();
    }
}
