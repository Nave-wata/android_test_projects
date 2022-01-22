public class Test2 {
    static int SAMPLING_RATE = 44100;
    static int bufSize = 1024; // フーリエ変換のサイズと同じ
    static int[] Base_Hz = { 43, 86, 129, 172, 258, 516, 1033, 2024, 4005, 8010, 16020 };
    static int[] Base_Hz_cnt = { 1, 2, 3, 4, 6, 12, 24, 47, 93, 186, 372 };

    public static void main(String[] args) {
        int i;
        int Hz;

        for (i = 0; i < bufSize / 2; i++) {
            Hz = i * SAMPLING_RATE / bufSize;

            if (Hz == Base_Hz[0]) {
                System.out.println(Base_Hz[0] + " = " + i);
            } else if (Hz == Base_Hz[1]) {
                System.out.println(Base_Hz[1] + " = " + i);
            } else if (Hz == Base_Hz[2]) {
                System.out.println(Base_Hz[2] + " = " + i);
            } else if (Hz == Base_Hz[3]) {
                System.out.println(Base_Hz[3] + " = " + i);
            } else if (Hz == Base_Hz[4]) {
                System.out.println(Base_Hz[4] + " = " + i);
            } else if (Hz == Base_Hz[5]) {
                System.out.println(Base_Hz[5] + " = " + i);
            } else if (Hz == Base_Hz[6]) {
                System.out.println(Base_Hz[6] + " = " + i);
            } else if (Hz == Base_Hz[7]) {
                System.out.println(Base_Hz[7] + " = " + i);
            } else if (Hz == Base_Hz[8]) {
                System.out.println(Base_Hz[8] + " = " + i);
            } else if (Hz == Base_Hz[9]) {
                System.out.println(Base_Hz[9] + " = " + i);
            } else if (Hz == Base_Hz[10]) {
                System.out.println(Base_Hz[10] + " = " + i);
            } else {

            }
        }
    }
}
