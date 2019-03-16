import java.util.Random;

public class Fermat {
    // Aceasta functie calculeaza b^n mod p
    static int power(int b, int n, int p) {
        long res = 1; // Initialize result
        long a = b; // copiez b ul , si il retin ca fiind logn deoarece datorita
                    // inmultirilor
                    // din while e posibil pt valori mari ale lui b sa se
                    // produca overflow

        while (n > 0) {
            // Daca n este impar inmultesc rezultatul cu baza
            if ((n & 1) == 1)
                res = (res * a) % p;

            n = n >> 1; // n = n/2
            a = (a * a) % p;
        }
        return (int) res;
    }

    static boolean isPrime(int n, int k) {
        // Cazuri speciale
        if (n <= 1 || n == 4)
            return false;
        if (n <= 3)
            return true;

        Random rand = new Random();
        while (k > 0) {
            // Alegem un numar random intre 2 si n-2
            int a = 2 + rand.nextInt(n - 2) % (n - 4);

            // Daca nu indeplineste conditiile teoremei lui Fermat
            // Stim sigur ca numarul nu este prim
            if (power(a, n - 1, n) != 1)
                return false;

            k--;
        }

        return true;
    }

    public static void main(String[] args) {
        int N = 1000000;
        int contor = 1; // singurul numar prim par este 2
        int i;
        long beforeUsedMem = Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory();
        double start = System.currentTimeMillis();
        int iter = 5;
        for (i = 3; i <= N; i += 2) {
            if (isPrime(i, iter)) {
                contor++;
            }
        }
        double stop = System.currentTimeMillis();
        long afterUsedMem = Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory();

        long usedMem = afterUsedMem - beforeUsedMem;
        double time = (stop - start) / 1000;
        System.out.println("Numere prime: " + contor);
        System.out.println("Timpul de rulare a fost: " + time + " s");
        System.out.println("Memorie folosita: " + (usedMem / 1024) + " KB");

    }
}
