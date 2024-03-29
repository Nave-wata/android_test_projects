public class Test2 {
    static int SAMPLING_RATE = 44100;
    static int bufSize = 1024; // フーリエ変換のサイズと同じ
    static int[] Base_Hz = { 43, 86, 129, 258, 516, 1033, 2024, 4005, 8010, 16020 };
    static int[] Base_Hz_cnt = { 1, 2, 3, 7, 13, 25, 48, 94, 187, 373 }; // 全部で 512 個
    static int[] Base_Hz_cnt_sub = new int[bufSize];
    static double[] volUp = new double[bufSize / 2];

    public static void main(String[] args) {
        int i, j, k = 0;
        int Hz, sub;
        double ratio, tmp;

        for (i = 0; i < bufSize / 2; i++) {
            Hz = i * SAMPLING_RATE / bufSize;
        }

        for (i = 0; i < 10; i++) {
            if (i != 9) {
                sub = (Base_Hz_cnt[i + 1] - 1) - Base_Hz_cnt[i];
                System.out.println(sub);
                ratio = 1.0 / sub;
                tmp = 1.0;
                for (j = Base_Hz_cnt[i]; j < Base_Hz_cnt[i + 1]; j++) {
                    tmp -= ratio;
                    volUp[k] = tmp;
                    k++;
                }
                for (j = Base_Hz_cnt[i + 1]; j < Base_Hz_cnt[i]; j--) {

                }
                // System.out.println(Base_Hz_cnt[i] + " ~ " + Base_Hz_cnt[i + 1]);
            } else {
                sub = (512 - 1) - Base_Hz_cnt[i];
                System.out.println(sub);
                for (j = Base_Hz_cnt[i]; j < 512; j++) {

                }
                // System.out.println(Base_Hz_cnt[i] + " ~ " + 512);
            }
        }
    }
}
