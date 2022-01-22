import org.jtransforms.fft.DoubleFFT_1D;

public class FFT_Test2_vscode {
    static DoubleFFT_1D fft;
    static int FFT_SIZE = 8;
    static double[] data = new double[FFT_SIZE];

    public static void main(String[] args) throws java.lang.NoClassDefFoundError {
        for (int i = 0; i < FFT_SIZE; i++) {
            data[i] = 10.0 + i;
            System.out.println("data[" + i + "] = " + data[i]);
        }
        System.out.println();

        fft = new DoubleFFT_1D(FFT_SIZE);

        try {
            fft.realForward(data);
        } catch (NoClassDefFoundError e) {
            System.out.println("fft.realForward(data)");
        }

    }
}
