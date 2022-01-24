import org.jtransforms.fft.DoubleFFT_1D;
import java.util.*;

public class FFT_Test2_2 {
    public static void main(String[] args) {
        ArrayList<DoubleFFT_1D> ffts = new ArrayList<DoubleFFT_1D>();
        ffts.add(DoubleFFT_1D(5));
    }

    private static DoubleFFT_1D DoubleFFT_1D(int i) {
        DoubleFFT_1D fft = new DoubleFFT_1D(5);
        return fft;
    }
}
