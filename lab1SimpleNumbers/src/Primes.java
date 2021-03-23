public class Primes {

    public static void main (String[] args){

        for (int i = 2; i <= 100; i++){
            if (isPrime(i)) System.out.println(i);
        }
    }

    public static boolean isPrime(int n1) {
        for (int i = 2; i < n1;i++){
            if (n1 % i == 0) return false;
        }
        return true;
    }

}
