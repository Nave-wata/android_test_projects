import org.jtransforms.fft.DoubleFFT_1D;
import java.util.*;

public class FFT_Test2_2 {
    public static void main(String[] args) {
        ArrayList<DoubleFFT_1D> ffts = new ArrayList<DoubleFFT_1D>();
        double[] data = { 10, 20, 30, 40, 50 };

        ffts.add(DoubleFFT_1D(5));
        ffts.add(DoubleFFT_1D(5));

        for (DoubleFFT_1D fft : ffts) {
            for (int i = 0; i < data.length; i++) {
                System.out.println("DEF: data[" + i + "] = " + data[i]);
            }
            System.out.println();

            fft.realForward(data);
            for (int i = 0; i < data.length; i++) {
                System.out.println("FFT: data[" + i + "] = " + data[i]);
            }
            System.out.println();

            fft.realInverse(data, true);
            for (int i = 0; i < data.length; i++) {
                System.out.println("IFFT: data[" + i + "] = " + data[i]);
            }
            System.out.println();
        }
    }

    private static DoubleFFT_1D DoubleFFT_1D(int i) {
        DoubleFFT_1D fft = new DoubleFFT_1D(5);
        return fft;
    }
}
