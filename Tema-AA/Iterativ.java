
public class Iterativ {

    public static int algoritmIterativ(int N) {
        int contor = 1;
        int flag;
        int i, j;
        for (i = 3; i <= N; i += 2) {
            flag = 1;
            for (j = 3; j < i / 2; j += 2) {
                if (i % j == 0) {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1) {
                contor++;
            }
        }
        return contor;
    }

    public static void main(String[] args) {
        int N = 1000000;
        int result = 0;
        long beforeUsedMem = Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory();

        double start = System.currentTimeMillis();
        result = algoritmIterativ(N);
        double stop = System.currentTimeMillis();
        double time = (stop - start) / 1000;
        long afterUsedMem = Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory();
        long usedMem = afterUsedMem - beforeUsedMem;
        System.out.println("Numere prime: " + result);
        System.out.println("Timpul de rulare a fost: " + time + " s");
        System.out.println("Memorie folosita: " + (usedMem / 1024) + " KB");
    }
}
