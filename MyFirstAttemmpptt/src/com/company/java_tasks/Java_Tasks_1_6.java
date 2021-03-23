package com.company.java_tasks;

import java.util.Arrays;

public class Java_Tasks_1_6 {
    public static void showResults() {

        System.out.println("\n-------Задания 1/6 1-10-------\n");
        /*
        	1. В Java есть единственный оператор, способный обеспечить остаток от операции деления.
        	Два числа передаются в качестве параметров. Первый параметр, разделенный на второй параметр,
        	будет иметь остаток, возможно, ноль. Верните это значение
         */
        // [START] (1/6) [1]

        int rem1 = -5;
        int rem2 = -6;

        System.out.println(
                "remainder(" + rem1 + ", " + rem2 + ") -> " +
                        remainder(rem1,rem2) + "\n"
        );
        // [END]


        /*
        2.	Напишите функцию, которая принимает основание и высоту треугольника и возвращает его площадь.
         */
        // [START] (1/6) [2]

        int tri1 = 7;
        int tri2 = 5;

        System.out.println(
                "triArea(" + tri1 + ", " + tri2 + ") -> " +
                        triArea(tri1,tri2) + "\n"
        );
        // [END]


        /*
        3.	В этой задаче фермер просит вас сказать ему, сколько ног можно сосчитать среди всех его животных. Фермер разводит три вида:
                chickens = 2 legs
                cows = 4 legs
                pigs = 4 legs
            Фермер подсчитал своих животных, и он дает вам промежуточный итог для каждого вида.
            Вы должны реализовать функцию, которая возвращает общее количество ног всех животных.

         */
        // [START] (1/6) [3]

        int iChickens = 4;
        int iCows = 3;
        int iPigs = 6;

        System.out.println(
                "animals(" + iChickens + ", " + iCows + ", " + iPigs + ") -> " +
                        animals(iChickens,iCows,iPigs) + "\n"
        );

        // [END]



        /*
        4.	Создайте функцию, которая принимает три аргумента (prob, prize, pay) и возвращает true,
        если prob * prize > pay; в противном случае возвращает false.
         */
        // [START] (1/6) [4]

        float iProb = (float) 0.05;
        int iPrise = 100;
        int iPay = 5;

        System.out.println(
                "profitableGamble(" + iProb + ", " + iPrise + ", " + iPay + ") -> " +
                        profitableGamble(iProb,iPrise,iPay)
        );

        iProb = (float) 0.15;
        iPrise = 100;
        iPay = 5;

        System.out.println(
                "profitableGamble(" + iProb + ", " + iPrise + ", " + iPay + ") -> " +
                        profitableGamble(iProb,iPrise,iPay) + "\n"
        );
        // [END]


        /*
        5.	Напишите функцию, которая принимает 3 числа и возвращает, что нужно сделать с a и b:
        они должны быть сложены, вычитаны, умножены или разделены, чтобы получить N.
        Если ни одна из операций не может дать N, верните "none".
            3 числа – это N, a и b.
         */
        // [START] (1/6) [5]

        float fN = (float) 0.5;
        int iOperA = 5;
        int iOperB = 10;

        System.out.println(
                "operation(" + fN + ", " + iOperA + ", " + iOperB + ") -> " +
                        operation(fN,iOperA,iOperB) + "\n"
        );
        // [END]


        /*
        6.	Создайте функцию, которая возвращает значение ASCII переданного символа.
         */
        // [START] (1/6) [6]

        char cToa1 = 'A';
        System.out.println(
                "ctoa(" + cToa1 +  ") -> " +
                        ctoa(cToa1) + "\n"
        );
        // [END]


        /*
        7.	Напишите функцию, которая берет последнее число из последовательного списка чисел и
        возвращает сумму всех чисел до него и включая его.
         */
        // [START] (1/6) [7]

        int iFucc = 5;

        System.out.println(
                "addUpTo(" + iFucc +  ") -> " +
                        addUpTo(iFucc) + "\n"
        );
        // [END]


        /*
        8.	Создайте функцию, которая находит максимальное значение третьего ребра треугольника,
        где длины сторон являются целыми числами.
         */
        // [START] (1/6) [8]

        int iEdgeA = 9;
        int iEdgeB = 7;

        System.out.println(
                "nextEdge(" + iEdgeA + ", " + iEdgeB + ") -> " +
                        nextEdge(iEdgeA, iEdgeB) + "\n"
        );
        // [END]


        /*
        9.	Создайте функцию, которая принимает массив чисел и возвращает сумму его кубов.
         */
        // [START] (1/6) [9]

        int [] iArray = {1, 4, 5, 2, 3};

        System.out.println(
                "sumOfCubes(" + Arrays.toString(iArray) + ") -> " +
                        sumOfCubes(iArray) + "\n"
        );
        // [END]


        /*
        10.	Создайте функцию, которая будет для данного a, b, c выполнять следующие действия:
            – Добавьте A к себе B раз.
            – Проверьте, делится ли результат на C.
         */
        // [START] (1/6) [10]

        int iA = 5;
        int iB = 4;
        int iC = 2;

        System.out.println(
                "abcmath(" + iA + ", " + iB + ", " + iC + ") -> " +
                        abcmath(iA,iB,iC) + "\n"
        );
        // [END]
    }

    // (1/6) [1]
    private static float remainder (float n1, float n2){
        if (n1 < 0 && n2 < 0) return Math.abs(n1 % n2);
        return n1 % n2;
    }

    // (1/6) [2]
    private static float triArea (float n1, float n2){
        float ans;

        n1 = Math.abs(n1);
        n2 = Math.abs(n2);

        ans = (n1 * n2);
        ans = ans / 2;

        return ans;
        // note: делим на два отдельно, потому что нужно сначала два int преобразовать в float
    }

    // (1/6) [3]
    private static float animals (float n1, float n2, float n3){

        n1 = Math.abs(n1);
        n2 = Math.abs(n2);
        n3 = Math.abs(n3);

        return n1 * 2 + n2 * 4 + n3 * 4;
    }

    // (1/6) [4]
    private static boolean profitableGamble (float n1, float n2, float n3){

        n1 = Math.abs(n1);
        n2 = Math.abs(n2);
        n3 = Math.abs(n3);

        return (n1 * n2) > n3;
    }

    // (1/6) [5]
    private static String operation (float n1, float n2, float n3) {

        if (n2 + n3 == n1) {
            return "Added";
        } else if (n2 - n3 == n1) {
            return "Subtracted";
        } else if (n2 * n3 == n1) {
            return "Multiplied";
        } else if (n2 / n3 == n1) {
            return "Divided";
        } else {
            return "None";
        }
    }

    // (1/6) [6]
    private static int ctoa (char n1) {

        return n1;
    }

    // (1/6) [7]
    private static long addUpTo (int n1) {

        if (n1 >= 0) {
            long ans = 1;

            for (int i = 2; i <= n1; i++) {
                ans *= i;
            }

            return ans;
        }
        else {
            return -1;
            // возвращаем -1, если число меньше 0
        }
    }

    // (1/6) [8]
    private static int nextEdge (int n1, int n2){
        return n1 + n2 - 1;
    }

    // (1/6) [9]
    private static long sumOfCubes (int [] n1){
        long ans = 0;
        for (int j : n1) {
            ans += j * j * j;
        }
        return ans;
    }

    // (1/6) [10]
    public static boolean abcmath (float n1, float n2, float n3){
        for (int i = 1; i <= n2; i++){
            n1 += n1;
        }
        return n1 % n3 == 0;
    }
}
