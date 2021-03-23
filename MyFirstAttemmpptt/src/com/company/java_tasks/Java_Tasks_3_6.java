package com.company.java_tasks;



public class Java_Tasks_3_6 {
    public static void showResults() {
        System.out.println("___[1]___");
        System.out.println(solutions(1,0,-1));

        System.out.println("___[2]___");
        System.out.println(findZip("zip zip"));

        System.out.println("___[3]___");
        System.out.println(checkPerfect(6));

        System.out.println("___[4]___");
        System.out.println(flipEndChars("nan"));

        System.out.println("___[5]___");
        System.out.println(isValidHexCode("#1Af4Ab"));

        int[] arr1 = {1,3,4,4,4};
        int[] arr2 = {2,5,7};
        System.out.println("___[6]___");
        System.out.println(same(arr1,arr2));

        System.out.println("___[7]___");
        System.out.println(isKaprekar(297));

        System.out.println("___[8]___");
        System.out.println(longestZero("00010010000"));

        System.out.println("___[9]___");
        System.out.println(nextPrime(24));

        System.out.println("___[10]___");
        System.out.println(rightTriangle(3,4,5));
    }

    /**
     *1.	Квадратное уравнение ax2 + bx + c = 0 имеет либо 0, либо 1, либо 2 различных решения для действительных значений x. учитывая a, b и c, вы должны вернуть число решений в уравнение.
     */
    private static int solutions (int a, int b, int c){
        if (b*b - 4*a*c < 0) return 0;
        if (b*b - 4*a*c == 0) return 1;
        return 2; // if b*b - 4*a*c > 0
    }

    /**
     * 2.	Напишите функцию, которая возвращает позицию второго вхождения " zip " в строку, или -1, если оно не происходит по крайней мере дважды. Ваш код должен быть достаточно общим, чтобы передать все возможные случаи, когда "zip" может произойти в строке.
     */
    private static int findZip(String str1){
        String str = str1.toLowerCase();
        if (str.contains("zip")) str = str.replaceFirst("zip", "   ");
        if (str.contains("zip")) return str.indexOf("zip");
        return -1;
    }

    /**
     * 3.	Создайте функцию, которая проверяет, является ли целое число совершенным числом или нет. Совершенное число - это число, которое можно записать как сумму его множителей, исключая само число.
     * Например, 6-это идеальное число, так как 1 + 2 + 3 = 6, где 1, 2 и 3-Все коэффициенты 6. Точно так же 28-это совершенное число, так как 1 + 2 + 4 + 7 + 14 = 28.
     */
    private static boolean checkPerfect(int x){
        int x2 = 0;
        for (int i = x - 1 ; i >= 1; i--){
            if (x % i == 0) x2 += i;
        }
        return x2 == x;
    }

    /**
     *4.	Создайте функцию, которая принимает строку и возвращает новую строку с заменой ее первого и последнего символов, за исключением трех условий:
     * – Если длина строки меньше двух, верните "несовместимо".".
     * – Если первый и последний символы совпадают, верните "два-это пара.".
     */
    private static String flipEndChars (String s){
        if (s.length() < 3) return "несовместимо";

        if (s.charAt(0) == s.charAt(s.length()-1)) return "два - это пара";
        else return s.charAt(s.length()-1) + s.substring(1,s.length() - 1) + s.charAt(0);
    }

    /**
     *5.	Создайте функцию, которая определяет, является ли строка допустимым шестнадцатеричным кодом.
     * Шестнадцатеричный код должен начинаться с фунтового ключа # и иметь длину ровно 6 символов. Каждый символ должен быть цифрой от 0-9 или буквенным символом от A-F. все буквенные символы могут быть прописными или строчными.
     */
    private static boolean isValidHexCode (String s){
        if (s.charAt(0) != '#' || s.length() != 7) return false;
        for (int i = 1; i < s.length(); i++){
            if (  !(s.charAt(i) >= '0' & s.charAt(i) <= '9')
                    && !(s.toLowerCase().charAt(i) >= 'a' & s.toLowerCase().charAt(i) <= 'f') ) return false;
        }

        return true;
    }

    /**
     *6.	Напишите функцию, которая возвращает true, если два массива имеют одинаковое количество уникальных элементов, и false в противном случае.
     */
    private static boolean same (int[] ar1, int[] ar2){
        return uniqueElements(ar1) == uniqueElements(ar2);
    }
    private static int uniqueElements(int[] ar1){
        int amountUnique = 0;
        for (int x = 0; x < ar1.length - 1; x++) {
            boolean unique = true;
            for (int y = x + 1; y < ar1.length; y++){
                if (ar1[y] == ar1[x]) {
                    unique = false;
                    break;
                }
            }
            if (unique) amountUnique++;
        }
        return  amountUnique;
    }

    /**
     *7.    Число Капрекара-это положительное целое число, которое после возведения в квадрат и разбиения на две лексикографические части равно сумме двух полученных новых чисел:
     * – Если количество цифр квадратного числа четное, то левая и правая части будут иметь одинаковую длину.
     * – Если количество цифр квадратного числа нечетно, то правая часть будет самой длинной половиной, а левая-самой маленькой или равной нулю, если количество цифр равно 1.
     * – Учитывая положительное целое число n, реализуйте функцию, которая возвращает true, если это число Капрекара, и false, если это не так.
     */
    private static boolean isKaprekar (int x){
        int x1;
        int x2;
        String xx = Integer.toString(x*x);

        if (xx.length() < 2){
            x1 = 0;
            x2 = x * x;
        }
        else {
            x1 = Integer.parseInt(xx.substring(0,Math.floorDiv(xx.length(),2)));
            x2 = Integer.parseInt(xx.substring(Math.floorDiv(xx.length(),2)));
        }

        return (x1 + x2) == x;
    }

    /**
     *8.	Напишите функцию, которая возвращает самую длинную последовательность последовательных нулей в двоичной строке.
     */
    private static String longestZero (String s){
        String sMax = "";
        String sBuf = "";
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '0') sBuf += "0";
            if (s.charAt(i) == '1'){
                if (sBuf.length() > sMax.length()) sMax = sBuf;
                sBuf = "";
            }
        }
        if (sBuf.length() > sMax.length()) sMax = sBuf;
        return sMax;
    }

    /**
     *9.	Если задано целое число, создайте функцию, которая возвращает следующее простое число. Если число простое, верните само число.
     */
    private static int nextPrime (int i){
        for (int j = i; j < Integer.MAX_VALUE; j++){

            boolean isPrime = true;
            for (int k = 2; k < j; k++){
                if (j % k == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) return j;
        }
        return -1;
    }

    /**
     *10.	Учитывая три числа, x, y и z, определите, являются ли они ребрами прямоугольного треугольника.
     */
    private static boolean rightTriangle (int a, int b, int c){
        if ( Math.sqrt(b*b + c*c) == a) return true;
        if ( Math.sqrt(a*a + c*c) == b) return true;
        if ( Math.sqrt(a*a + b*b) == c) return true;
        return false;
    }

}
