import org.jtransforms.fft.DoubleFFT_1D;

public class FFT_Test2_2 {
    public static void main(String[] args) {
        DoubleFFT_1D[] ffts;

        for (int i = 0; i < 5; i++) {
            ffts[i] = new DoubleFFT_1D(10);
        }
    }
}
