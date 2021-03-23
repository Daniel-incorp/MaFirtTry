package com.company.java_tasks;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Java_Tasks_4_6 {
    public static void showResults(){

        System.out.println("___[1]___");
        formatEssay(10,7,"hello my name is Bessie and this is my essay");
        System.out.println();

        System.out.println("___[2]___");
        System.out.println(Arrays.stream(split("()(()())((((()))))()"))
                .filter(Objects::nonNull) // убираем null элементы массива
                .collect(Collectors.joining(", ")));


        System.out.println("___[3]___");
        System.out.println(toCamelCase("To camel case"));
        System.out.println(toSnakeCase("To snake Case"));

        System.out.println("___[4]___");
        System.out.println(overTime(9,18, 10, 1.5));

        System.out.println("___[5]___");
        System.out.println(BMI("55 kilos", "1.65 meters"));

        System.out.println("___[6]___");
        System.out.println(bugger(999));

        System.out.println("___[7]___");
        System.out.println(toStartShortHand("aaabccsssdedd"));

        System.out.println("___[8]___");
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));

        System.out.println("___[9]___");
        System.out.println(trouble(1222345, 12345));
        System.out.println(trouble(666789, 12345667));

        System.out.println("___[10]___");
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));


    }

    /**
     *1.	Бесси работает над сочинением для своего класса писателей. Поскольку ее почерк довольно плох, она решает напечатать эссе с помощью текстового процессора. Эссе содержит N слов (1≤N≤100), разделенных пробелами. Каждое слово имеет длину от 1 до 15 символов включительно и состоит только из прописных или строчных букв. Согласно инструкции к заданию, эссе должно быть отформатировано очень специфическим образом: каждая строка должна содержать не более K (1≤K≤80) символов, не считая пробелов. К счастью, текстовый процессор Бесси может справиться с этим требованием, используя следующую стратегию:
     * – Если Бесси набирает Слово, и это слово может поместиться в текущей строке, поместите его в эту строку. В противном случае поместите слово на следующую строку и продолжайте добавлять к этой строке. Конечно, последовательные слова в одной строке все равно должны быть разделены одним пробелом. В конце любой строки не должно быть места.
     * –	К сожалению, текстовый процессор Бесси только что сломался. Пожалуйста, помогите ей правильно оформить свое эссе!
     *
     * Вам будут даны n, k и строка
     */
    private static void formatEssay(int n, int k, String s){
        String[] words = new String[n];

        int wordNumber = 0;
        String Buffer = "";
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == ' ' ){ // встретился пробел -> добавляем слово в массив words
                words[wordNumber] = Buffer;
                Buffer = "";
                wordNumber++;
            }
            else Buffer += s.charAt(i);
            if (i == s.length() - 1) words[wordNumber] = Buffer; // добавление слова при окончании строки
        }

        Buffer = "";
        for (int i = 0; i < n; i++){

            if (Buffer.length() + words[i].length() > k){
                Buffer = "";
                System.out.println();
            }
            System.out.print(words[i] + " ");
            Buffer += words[i];

        }
    }

    /**
     * 2.	Напишите функцию, которая группирует строку в кластер скобок. Каждый кластер должен быть сбалансирован.
     */
    private static String[] split (String s){
        String[] ans = new String[s.length()];

        int num = 0;
        int left = 0;
        int right = 0;
        int j = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(') left++;
            if (s.charAt(i) == ')') right++;
            if (left == right){

                left = 0;
                right = 0;

                ans[num] = s.substring(j, i + 1);
                j = i + 1;
                num++;
            }
        }
        return ans;
    }

    /**
     *3.	Создайте две функции toCamelCase () и toSnakeCase (), каждая из которых берет одну строку и преобразует ее либо в camelCase, либо в snake_case.
     * Примечание:
     * – Snake case — стиль написания составных слов, при котором несколько слов разделяются символом подчеркивания (_), и не имеют пробелов в записи, причём каждое слово обычно пишется с маленькой буквы — «foo_bar», «hello_world» и т. д.
     *
     * – CamelCase — стиль написания составных слов, при котором несколько слов пишутся слитно без пробелов, при этом каждое слово внутри фразы пишется с прописной буквы. Стиль получил название CamelCase, поскольку прописные буквы внутри слова напоминают горбы верблюда
     */
    private static String toCamelCase (String s){

        for (int i = 0; i < s.length(); i++){
            if (i != s.length() - 1 && (s.charAt(i) == '_' || s.charAt(i) == ' ')){
                s = s.replace(s.substring(i,i+2), s.substring(i,i+2).toUpperCase());

            }
        }
        return s.replaceAll("_", "").replaceAll(" ", "");
    }
    private static String toSnakeCase (String s){
        return s.replaceAll(" ", "_").toLowerCase();
    }

    /**
     * 4.	Напишите функцию, которая вычисляет сверхурочную работу и оплату, связанную с сверхурочной работой.
     *
     * Работа с 9 до 5: обычные часы работы
     * После 5 вечера это сверхурочная работа
     * Ваша функция получает массив с 4 значениями:
     * – Начало рабочего дня, в десятичном формате, (24-часовая дневная нотация)
     * – Конец рабочего дня. (Тот же формат)
     * – Почасовая ставка
     * – Множитель сверхурочных работ
     *
     * Ваша функция должна возвращать:
     * $ + заработанные в тот день (округлены до ближайшей сотой)
     *
     * Примечание:
     * С 16 до 17 регулярно, поэтому 1 * 30 = 30
     * С 17 до 18 сверхурочно, поэтому 1 * 30 * 1,8 = 54
     * 30 + 54 = 84,00 $
     */
    private static String overTime (int beginTime, int finishTime, int payPerHour, double overtimeMultiplier){

        double ans = 0;

        for (int i = beginTime ; i < finishTime; i++){
            if (9 <= i && i < 17) ans += payPerHour;
            else ans += payPerHour * overtimeMultiplier;
        }
        return "$" + ans;
    }


    /**
     * 5.	Индекс массы тела (ИМТ) определяется путем измерения вашего веса в килограммах и деления на квадрат вашего роста в метрах. Категории ИМТ таковы:
     *
     * Недостаточный вес: <18,5
     * Нормальный вес: 18.5-24.9
     * Избыточный вес: 25 и более
     * Создайте функцию, которая будет принимать вес и рост (в килограммах, фунтах, метрах или дюймах) и возвращать ИМТ и связанную с ним категорию. Округлите ИМТ до ближайшей десятой.
     */
    private static String BMI (String weight, String height){
        double kg;
        double meters;
        double BMI;

        String Buffer = "";
        for (int i = 0; i < weight.length(); i++){
            if (weight.charAt(i) == ' '){
                break;
            }
            Buffer += weight.charAt(i);
        }
        if (weight.contains("pounds")) kg = Integer.parseInt(Buffer) * 0.453592;
        else kg = Double.parseDouble(Buffer);


        Buffer = "";
        for (int i = 0; i < height.length(); i++){
            if (height.charAt(i) == ' '){
                break;
            }
            Buffer += height.charAt(i);
        }
        if (height.contains("inches")) meters = Integer.parseInt(Buffer) * 0.0254;
        else meters = Double.parseDouble(Buffer);

        BMI = Math.floor(kg / (meters * meters) * 10) / 10;

        if (BMI >= 25) return BMI + " Overweight";
        if (BMI >= 18.5) return BMI + " Normal weight";
        return BMI + " Underweight";
    }


    /**
     * 6.	Создайте функцию, которая принимает число и возвращает его мультипликативное постоянство, которое представляет собой количество раз, которое вы должны умножать цифры в num, пока не достигнете одной цифры.
     */
    private static int bugger (int i1){
        String s = Integer.toString(i1);
        int Buffer;
        int ans = 0;
        while (s.length() > 1){

            Buffer = Integer.parseInt(s.substring(0,1));
            for (int i = 1; i < s.length(); i++){
                Buffer *= Integer.parseInt(s.substring(i,i+1));
            }
            s = Integer.toString(Buffer);
            ans++;
        }
        return ans;
    }

    /**
     * 7.	Напишите функцию, которая преобразует строку в звездную стенографию. Если символ повторяется n раз, преобразуйте его в символ*n.
     */
    private static String toStartShortHand(String s){
        char cBuffer;
        String sBuffer;
        String ans = s;
        for (int i = 1; i < s.length(); i++){

            cBuffer = s.charAt(i - 1);

            int numOfChars = 0;
            sBuffer = "";
            if (s.charAt(i) == cBuffer){
                numOfChars++;
                sBuffer += cBuffer;
                for (int j = i; j < s.length(); j++){
                    if (s.charAt(j) == cBuffer) {
                        numOfChars++;
                        sBuffer += cBuffer;
                    }
                    else break;
                }
                i += numOfChars - 1;
                ans = ans.replace(sBuffer, cBuffer + "*" + Integer.toString(numOfChars));
            }
        }
        return ans;
    } // надо было сделать метод нахождения повторений!

    /**
     * 8.	Создайте функцию, которая возвращает true, если две строки рифмуются, и false в противном случае. Для целей этого упражнения две строки рифмуются, если последнее слово из каждого предложения содержит одни и те же гласные.
     *
     * Примечание:
     * – Без учета регистра.
     * – Здесь мы не обращаем внимания на такие случаи, как "thyme" и "lime".
     * – Мы также игнорируем такие случаи, как "away" и "today" (которые технически рифмуются, хотя и содержат разные гласные).
     */
    private static boolean doesRhyme (String s1, String s2){
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        String s1Word = "";
        String s2Word = "";

        String sBuffer1;
        String sBuffer2;

        for (int i = s1.length() - 1; i >= 0; i--){
            if (s1.charAt(i) == ' ') break;
            if (isVowel(s1.charAt(i))) s1Word += s1.charAt(i);
        }
        for (int i = s2.length() - 1; i >= 0; i--){
            if (s2.charAt(i) == ' ') break;
            if (isVowel(s2.charAt(i))) s2Word += s2.charAt(i);
        }

        sBuffer1 = s1Word;
        sBuffer2 = s2Word;

        if (s1Word.length() != s2Word.length()) return false;
        for (int i = 0; i < sBuffer1.length(); i++){
            s1Word = s1Word.replace(sBuffer2.charAt(i) + "", "");
            s2Word = s2Word.replace(sBuffer1.charAt(i) + "", "");
        }

        return (s1Word.isEmpty() && s2Word.isEmpty());
    }
    private static boolean isVowel (char c){
        return c=='a' || c=='e' ||
                c=='i' || c=='o' ||
                c=='u';
    }

    /**
     *9.	Создайте функцию, которая принимает два целых числа и возвращает true, если число повторяется три раза подряд в любом месте в num1 и то же самое число повторяется два раза подряд в num2.
     */
    private static boolean trouble (int i1, int i2){
        String s1 = Integer.toString(i1);
        String s2 = Integer.toString(i2);

        for (int i = 2; i < s1.length();i++){

            if (s1.charAt(i-1) == s1.charAt(i) &&
            s1.charAt(i - 2) == s1.charAt(i)){
                for (int j = 1; j < s2.length();j++){
                    if (s2.charAt(j - 1) == s2.charAt(j)) return true;
                }
            }
        }
        return false;
    }

    /**
     *10.	Предположим, что пара одинаковых символов служит концами книги для всех символов между ними. Напишите функцию, которая возвращает общее количество уникальных символов (книг, так сказать) между всеми парами концов книги.
     * Эта функция будет выглядеть следующим образом:
     */
    private static int countUniqueBooks (String s, char c){
        String sBuffer = "";
        int ans = 0;
        boolean searchingBooks = false;

        for (int i = 0; i < s.length();i++){

            if (s.charAt(i) == c){
                if (!searchingBooks) searchingBooks = true;
                else {
                        searchingBooks = false;
                        sBuffer = "";
                }
            }


            if (!sBuffer.contains(s.charAt(i) + "") && s.charAt(i) != c && searchingBooks) {
                ans++;
                sBuffer += s.charAt(i);
            }
        }
        return ans;
    }
}
