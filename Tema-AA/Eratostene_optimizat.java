
public class Eratostene_optimizat {

    public static int ciurulLuiEratosteneOptimizat(int N) {
        int contor = 1; // Singurul numar prim par este 2
        int i, j, i2;
        // Nu mai este necesara alocarea a N octeti deoarece fiecare octet poate
        // retine 8 "date" booleene
        byte[] prim = new byte[(N + 1) >> 3];
        for (i = 1; ((i * i) << 1) + (i << 1) <= N; i += 1) {
            if ((prim[i >> 3] & (1 << (i & 7))) == 0) {

                for (j = ((i * i) << 1) + (i << 1); (j << 1)
                        + 1 <= N; j += (i << 1) + 1) {
                    prim[j >> 3] |= (1 << (j & 7));
                }
            }
        }
        for (i = 1; 2 * i + 1 <= N; ++i)
            if ((prim[i >> 3] & (1 << (i & 7))) == 0)
                contor++;

        return contor;
    }

    public static void main(String[] args) {
        int N = 1000000;
        int result = 0;
        long beforeUsedMem = Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory();
        double start = System.currentTimeMillis();
        result = ciurulLuiEratosteneOptimizat(N);
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
