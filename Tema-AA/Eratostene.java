
public class Eratostene {

    public static int ciurulLuiEratostene(int N) {
        int contor = 1;
        int flag;
        int i, j;
        // Am folosit byte deoarece acestea au alocate doar (un octet)
        byte[] prim = new byte[N + 1];
        // Testez doar numerele impare
        for (i = 3; i <= N; i += 2) {
            if (prim[i] == 0) {
                contor++;
                for (j = i + i; j <= N; j += i) {
                    prim[j] = 1;
                }
            }
        }
        return contor;
    }

    public static void main(String[] args) {
        int N = 1000000;
        int result = 0;
        int j;

        long beforeUsedMem = Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory();
        double start = System.currentTimeMillis();
        result = ciurulLuiEratostene(N);
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
