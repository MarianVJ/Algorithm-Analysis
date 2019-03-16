
public class Testeaza_Programatic {

    /*
     * Cu ajutorul metodei din aceasta clasa am testat fiecare algoritm de 10
     * ori si am afisat timpul cel mai mic de rulare pentru algoritmii
     * probabilistici si pentru Miller-Rabin si pentru Fermat am modificat la
     * momentul testarii functia astfel incat sa se afiseze cel mai bun rezultat
     * ca si numar de numere prime determinat ( sa fie cat mai mic)
     */
    public static void main(String[] args) {
        int N = 10000000;
        int result = 1;
        int oldResult = Integer.MAX_VALUE;
        int j, i;
        double final_time = Long.MAX_VALUE;
        long final_mem = 0;
        for (j = 0; j < 9; j++) {
            long beforeUsedMem = Runtime.getRuntime().totalMemory()
                    - Runtime.getRuntime().freeMemory();
            double start = System.currentTimeMillis();

            result = 1;
            int iter = 5;
            for (i = 3; i <= N; i += 2) {
                if (Miller_Rabin.isPrime(i, iter)) {
                    result++;
                }
            }

            double stop = System.currentTimeMillis();
            double time = (stop - start) / 1000;
            long afterUsedMem = Runtime.getRuntime().totalMemory()
                    - Runtime.getRuntime().freeMemory();
            long usedMem = afterUsedMem - beforeUsedMem;
            if (final_time > time) {
                final_time = time;
                final_mem = usedMem;
                oldResult = result;
            }
        }

        System.out.println("Numere prime: " + oldResult);
        System.out.println("Timpul de rulare a fost: " + final_time + " s");
        System.out.println("Memorie folosita: " + (final_mem / 1024) + " KB");

    }

}
