package com.company.java_tasks;

import java.util.Arrays;

public class Java_Tasks_2_6 {

    public static void showResults() {

        System.out.println("\n-------Задания 2/6 1-10-------\n");
        /*
        1.	Создайте функцию, которая повторяет каждый символ в строке n раз.
         */
        // [START] (1/6) [1]

        String sRep = "BlackSilver";
        int iRep = 4;

        System.out.println(
                "repeat(" + iRep + ", " + sRep + ") -> " +
                        repeat(sRep,iRep) + "\n"
        );
        // [END]


        /*
        2.	Создайте функцию, которая принимает массив и возвращает
        разницу между самыми большими и самыми маленькими числами.
         */
        // [START] (1/6) [2]

        int [] aiDifferMaxMin = {5, 2, -5, -18, 5, 29};

        System.out.println(
                "differenceMaxMin(" + Arrays.toString(aiDifferMaxMin) + ") -> " +
                        differenceMaxMin(aiDifferMaxMin) + "\n"
        );
        // [END]


        /*
        3.	Создайте функцию, которая принимает массив в качестве аргумента и
        возвращает true или false в зависимости от того,
        является ли среднее значение всех элементов массива целым числом или нет.
         */
        // [START] (1/6) [3]

        float [] afIsAvgW = {2, 5, 3, 0, (float) 4.5};

        System.out.println(
                "isAvgWhole(" + Arrays.toString(afIsAvgW) + ") -> " +
                        isAvgWhole(afIsAvgW)
        );

        afIsAvgW = new float[] {2, 5, 3, 0, 4};

        System.out.println(
                "isAvgWhole(" + Arrays.toString(afIsAvgW) + ") -> " +
                        isAvgWhole(afIsAvgW) + "\n"
        );
        // [END]


        /*
        4.	Создайте метод, который берет массив целых чисел и возвращает массив,
        в котором каждое целое число является суммой самого себя + всех предыдущих чисел в массиве.
         */
        // [START] (1/6) [4]

        int [] aiCumulatSum = {1, 4, 11, -15, 228, 4};

        System.out.println(
                "cumulativeSum(" + Arrays.toString(aiCumulatSum) + ") -> " +
                        Arrays.toString(
                                cumulativeSum(aiCumulatSum)) + "\n"
        );
        // [END]


        /*
        5.	Создайте функцию, которая возвращает число десятичных знаков, которое имеет число (заданное в виде строки).
        Любые нули после десятичной точки отсчитываются в сторону количества десятичных знаков.
         */
        // [START] (1/6) [5]

        String dGetDecP = "45";

        System.out.println(
                "getDecimalPlaces(" + dGetDecP + ") -> " +
                        getDecimalPlaces(dGetDecP) + "\n"
        );
        // [END]


        /*
        6.	Создайте функцию, которая при заданном числе возвращает соответствующее число Фибоначчи.

        0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711...
         */
        // [START] (1/6) [6]

        int iFubo = 0;

        System.out.println(
                "fibonacci(" + iFubo + ") -> " +
                        fibonacci(iFubo) + "\n"
        );
        // [END]


        /*
        7.	Почтовые индексы состоят из 5 последовательных цифр. Учитывая строку,
        напишите функцию, чтобы определить, является ли вход действительным почтовым индексом.
        Действительный почтовый индекс выглядит следующим образом:
            – Должно содержать только цифры (не допускается использование нецифровых цифр).
            – Не должно содержать никаких пробелов.
            – Длина не должна превышать 5 цифр.
         */
        // [START] (1/6) [7]

        String sIsVal = "52245";

        System.out.println(
                "isValid(" + sIsVal + ") -> " +
                        isValid(sIsVal) + "\n"
        );
        // [END]


        /*
        8.	Пара строк образует странную пару, если оба из следующих условий истинны:
            – Первая буква 1-й строки = последняя буква 2-й строки.
            – Последняя буква 1-й строки = первая буква 2-й строки.
            – Создайте функцию, которая возвращает true, если пара строк представляет собой странную пару, и false в противном случае.

         */
        // [START] (1/6) [8]

        String sIsStrangeP1 = "Sparkling";
        String sIsStrangeP2 = "groups";

        System.out.println(
                "isStrangePair(" + sIsStrangeP1 + ", " + sIsStrangeP2 + ") -> " +
                        isStrangePair(sIsStrangeP1,sIsStrangeP2) + "\n"
        );
        // [END]


        /*
        9.	Создайте две функции: isPrefix(word, prefix-) и isSuffix (word, -suffix).
                – isPrefix должен возвращать true, если он начинается с префиксного аргумента.
                – isSuffix должен возвращать true, если он заканчивается аргументом суффикса.
                – В противном случае верните false.
            Примечание:
                Аргументы префикса и суффикса имеют тире - в них.
         */
        // [START] (1/6) [9]

        String sIsPref1 = "Automation";
        String sIsPref2 = "auto-";

        System.out.println(
                "isPrefix(" + sIsPref1 + ", " + sIsPref2 + ") -> " +
                        isPrefix(sIsPref1,sIsPref2) + "\n"
        );
        // [END]


        /*
        10.	Создайте функцию, которая принимает число (шаг) в качестве аргумента и
        возвращает количество полей на этом шаге последовательности.
         */
        // [START] (1/6) [10]


        int iBoxS = 5;

        System.out.println(
                "boxSeq(" + iBoxS + ") -> " +
                        boxSeq(iBoxS) + "\n"
        );
        // [END]
    }

    // (1/6) [1]
    private static String repeat (String n1, int n2){

        // потому что есть объединение строк в цикле
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < n1.length(); i++){
            ans.append(String.valueOf(n1.charAt(i)).repeat(n2));
        }

        return ans.toString();
    }

    // (1/6) [2]
    private static int differenceMaxMin (int [] n1){

        int Max = 0;
        int Min = 0;

        for (int j : n1){
            if (j > Max) Max = j;
            if (j < Min) Min = j;
        }

        return Max - Min;
    }


    // (1/6) [3]
    private static boolean isAvgWhole (float [] n1){
        for (float j : n1){
            if (j % 1 != 0 ) return false;
        }
        return true;
    }


    // (1/6) [4]
    private static int [] cumulativeSum (int [] n1){

        int [] ans = new int[n1.length];
        int sum = 0;

        for (int i = 0; i < n1.length; i++){
            ans[i] = n1[i] + sum;
            sum += n1[i];
        }
        return ans;
    }


    // (1/6) [5]
    private static int getDecimalPlaces (String n1){

        int ans = 0;

        if (n1.contains(".")){

            boolean b = false;

            for (int i = 0; i < n1.length(); i++){
                if (b){
                    ans++;
                }
                if (n1.charAt(i) == '.') b = true;
            }
        }
        return ans;
    }


    // (1/6) [6]
    private static int fibonacci (int n1){

        int i1 = 0;
        int i2 = 1;
        int iBuffer = 0;

        while ( i2 != n1 && n1 != 0){ // n1 != 0 - отдельное условие для частного случая (когда n1 = 0)
            iBuffer = i2;
            i2 += i1;
            i1 = iBuffer;
        }

        return i2 += i1;
    }


    // (1/6) [7]
    private  static boolean isValid (String n1){

        if (n1.length() == 5) {
            for (int i = 0; i < n1.length(); i++) {
                if (n1.charAt(i) < 48 || n1.charAt(i) > 57){
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    // (1/6) [8]
    private static boolean isStrangePair (String n1, String n2){

        return n1.toLowerCase().charAt(0) == n2.toLowerCase().charAt(n2.length() - 1)
                && n2.toLowerCase().charAt(0) == n1.toLowerCase().charAt(n1.length() - 1);
    }


    // (1/6) [9]
    private static boolean isPrefix (String n1, String n2){

        // P.s. Циклы можно заменить на простую обрезку строки через substring и проверку через contains
        if (n2.charAt(0) == '-'){
            for (int i = n1.length() - n2.length() + 1; i < n1.length(); i++){
                if (n1.toLowerCase().charAt(i) != n2.toLowerCase().charAt(i - n1.length() + n2.length())){
                    return false;
                }
            }
        }

        if (n2.charAt(n2.length() - 1) == '-'){
            for (int i = 0; i < n2.length() - 1; i++){
                if (n1.toLowerCase().charAt(i) != n2.toLowerCase().charAt(i)){
                    return false;
                }
            }
        }
        if (!n2.contains("-")) return false;
        return true;
    }


    // (1/6) [10]
    private static int boxSeq (int n1){
        int n2 = 0;
        for (int i = 1; i <= n1; i++){
            if (i % 2 == 1){
                n2 += 3;
            } else {
                n2 -= 1;
            }
        }
        return n2;
    }


}
