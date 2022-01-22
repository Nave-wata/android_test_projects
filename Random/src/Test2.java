public class Test2 {
    static int SAMPLING_RATE = 44100;
    static int bufSize = 1024; // フーリエ変換のサイズと同じ
    static int[] Base_Hz = { 43, 86, 129, 258, 516, 1033, 2024, 4005, 8010, 16020 };
    static int[] Base_Hz_cnt = { 1, 2, 3, 7, 13, 25, 48, 94, 187, 373 }; // 全部で 512 個
    static int[] Base_Hz_cnt_sub;
    static double[] VolUp = new double[bufSize / 2];

    public static void main(String[] args) {
        int i, j;
        int Hz;

        for (i = 0; i < bufSize / 2; i++) {
            Hz = i * SAMPLING_RATE / bufSize;
        }

        for (i = 0; i < 10; i++) {
            if (i != 9) {
                for (j = Base_Hz_cnt[i]; j < Base_Hz_cnt[i + 1]; j++) {

                }
                System.out.println(Base_Hz_cnt[i] + " ~ " + Base_Hz_cnt[i + 1]);
            } else {
                for (j = Base_Hz_cnt[i]; j < 512; j++) {

                }
                System.out.println(Base_Hz_cnt[i] + " ~ " + 512);
            }
        }
    }
}
