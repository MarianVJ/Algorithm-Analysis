import java.util.Random;

public class Miller_Rabin {

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

    static boolean miillerTest(int t, int n) {
        // Alegem un numar random intre [2,n-2] (baza)
        Random rand = new Random();
        int b = 2 + rand.nextInt(n - 2) % (n - 4);

        // Calculam b^t mod n
        long y = power(b, t, n);

        // Daca b^t indeplineste conditia fermat atunci numarul este pseudo-prim
        if (y == 1 || y == n - 1)
            return true;

        // Refacem valoarea lui ( cand inmultim t ul cu 2 ) cat timp nu am ajuns
        // la n--1
        // Cat timp nu am este indeplinita una din cele doua conditii de iesire:
        // (i) (y^2) % n nu este 1 ( numarul nu e prim )
        // (i) (y^2) % n nu este n-1 ( numarul e prim pentru baza curenta)
        // la fiecare pas i ( i reprezentand numarul iteratiei ) y^2 reprezinta
        // de fapt b^(n * 2^i) care trebuie congruent cu n-1(modulo m ) pentru a
        // trece testul(Conditie necesara )
        while (t != n - 1) {
            y = (y * y) % n;
            t *= 2;

            if (y == 1)
                return false;
            if (y == n - 1)
                return true;
        }

        // Return compus
        return false;
    }

    static boolean isPrime(int n, int k) {
        // Cazuri speciale
        if (n <= 1 || n == 4)
            return false;
        if (n <= 3)
            return true;

        // Calculam t ul astfel incat n-1 = 2^s * t , unde t sa fie impar
        int t = n - 1;
        while (t % 2 == 0)
            t /= 2;

        // Aplicam testul Miller-Rabin de k ori, ( constanta de securitate)
        for (int i = 0; i < k; i++)
            if (miillerTest(t, n) == false)
                return false;
        // Daca a trecut testul de toate cele k ori asta inseamna ca este prim
        // cu o anumita probabilitate care creste odata cu dimenisunea acestuia
        return true;
    }

    public static void main(String[] args) {
        int N = 1000000;
        int contor = 1; // Singurul numar prim par este 2
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
        double time = (stop - start) / 1000;
        long afterUsedMem = Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory();

        long usedMem = afterUsedMem - beforeUsedMem;
        System.out.println("Numere prime: " + contor);
        System.out.println("Timpul de rulare a fost: " + time + " s");
        System.out.println("Memorie folosita: " + (usedMem / 1024) + " KB");

    }

}
