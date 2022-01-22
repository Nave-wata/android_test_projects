class Test1 {
    static int SAMPLING_RATE = 44100;
    static int bufSize = 1024; // フーリエ変換のサイズと同じ

    public static void main(String[] args) {
        int i;

        for (i = 0; i < bufSize / 2; i++) {
            System.out.print(i * SAMPLING_RATE / bufSize + ", ");
        }
    }
}