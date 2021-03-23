package com.company.java_tasks;

import java.util.Arrays;
import java.util.LinkedList;

public class Java_Tasks_6_6 {
    public static void showResults() {

        System.out.println("___[1]___");
        System.out.println(bell(5));

        System.out.println("___[2]___");
        System.out.println(translateWord("What"));
        System.out.println(translateSentence("I like to eat honey waffles."));

        System.out.println("___[3]___");
        System.out.println(validColor("rgb(5,2,7)"));
        System.out.println(validColor("rgba(5,2,7,0.5213)"));
        System.out.println(validColor("rgb(5,,7)"));
        System.out.println(validColor("rgb(5,266,7)"));

        System.out.println("___[4]___");
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        String[] strings4 = {"b"};
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", strings4));

        System.out.println("___[5]___");
        System.out.println(Arrays.toString(getHashTags("How the Avocado Became the Fruit of the Global Trade")));

        System.out.println("___[6]___");
        System.out.println(ulam(6));

        System.out.println("___[7]___");
        System.out.println(longestNonrepeatingSubstring("abcabce"));

        System.out.println("___[8]___");
        System.out.println(convertToRoman(3974));

        System.out.println("___[9]___");
        System.out.println(formula("4 + 5 = 9"));

        System.out.println("___[10]___");
        System.out.println(palindromeDescendant(11211230));

    }


    /**
     *1.	Число Белла - это количество способов, которыми массив из n элементов может быть разбит на непустые подмножества.
     * Создайте функцию, которая принимает число n и возвращает соответствующее число Белла.
     *
     *
     1
     1	2
     2	3	5
     5	7	10	15
     15	20	27	37	52

     */
    private static int bell (int i1){
        int[] array1 = {1};
        int[] array2 = new int[0];
        for (int i = 1; i < i1; i++){

            array2 = new int[i+1];
            array2[0] = array1[array1.length - 1];
            for (int j = 1; j < i+1;j++){
                array2[j] = array2[j - 1] + array1[j - 1];
            }

            array1 = array2;
        }

        return array2[array2.length - 1];
    }


    /**
     *2.	В «поросячей латыни» (свинский латинский) есть два очень простых правила:
     * – Если слово начинается с согласного, переместите первую букву (буквы) слова до гласного до конца слова и добавьте «ay» в конец.
     * have ➞ avehay
     * cram ➞ amcray
     * take ➞ aketay
     * cat ➞ atcay
     * shrimp ➞ impshray
     * trebuchet ➞ ebuchettray
     * –	Если слово начинается с гласной, добавьте "yay" в конце слова.
     * ate ➞ ateyay
     * apple ➞ appleyay
     * oaken ➞ oakenyay
     * eagle ➞ eagleyay
     * Напишите две функции, чтобы сделать переводчик с английского на свинский латинский.
     * Первая функция translateWord (word) получает слово на английском и возвращает это слово,
     * переведенное на латинский язык. Вторая функция translateSentence (предложение) берет
     * английское предложение и возвращает это предложение, переведенное на латинский язык.
     */
    private static String translateWord (String s){

        if (isVowel(s.charAt(0))){
            s += "yay";
        }
        else {
            for (int i = 1; i < s.length(); i++){
                if (isVowel(s.charAt(i))){
                    s = s.substring(i) + s.substring(0, i ) + "ay";
                    break;
                }
            }
        }
        return s;
    }
    private static String translateSentence (String s){
        String[] words = sentenceToWords(s);

        for (String word : words) {
            s = s.replaceFirst(word, translateWord(word));
        }
        return s;
    }


    private static boolean isVowel (char c){
        return c=='a' || c=='e' ||
                c=='i' || c=='o' ||
                c=='u' ||
                c=='A' || c=='E' ||
                c=='I' || c=='O' ||
                c=='U';
    }

    private static String[] sentenceToWords(String s){
        LinkedList<String> linkedList = new LinkedList<>();

        String sBuffer = "";
        for (int i = 0; i < s.length(); i++){
            String Symbol = s.charAt(i) + "";
            if (Symbol.equals(" ") || Symbol.equals(".") || Symbol.equals(",") || Symbol.equals("-")) {

                if (!sBuffer.isEmpty()) linkedList.add(sBuffer);
                sBuffer = "";
            }
            else {
                sBuffer += Symbol;
            }
        }

        String[] ans = new String[linkedList.size()];
        for (int i = 0; i < ans.length; i++){
            ans[i] = linkedList.get(i);
        }
        return ans;
    }

    /**
     *3.	Учитывая параметры RGB (A) CSS, определите,
     * является ли формат принимаемых значений допустимым или нет.
     * Создайте функцию, которая принимает строку (например, " rgb(0, 0, 0)")
     * и возвращает true, если ее формат правильный, в противном случае возвращает false.
     */
    private static boolean validColor (String s){
        if (s.contains("rgba")){

            String sBuffer = "";
            int k = 1;
            for (int i = 5; s.charAt(i) != ')'; i++){

                if (s.charAt(i) == ','){
                    if (sBuffer.isBlank()) return false;
                    if ((Integer.parseInt(sBuffer) > 255 || Integer.parseInt(sBuffer) < 0) && k < 4) return false;
                    if (k == 4 && (Double.parseDouble(sBuffer) > 1 || Double.parseDouble(sBuffer) < 0)) return false;
                    sBuffer = "";
                    k++;
                }
                else sBuffer += s.charAt(i);
            }
            return true;
        }
        else {

            String sBuffer = "";
            for (int i = 4; s.charAt(i) != ')'; i++){

                if (s.charAt(i) == ','){
                    if (sBuffer.isBlank()) return false;
                    if (Integer.parseInt(sBuffer) > 255 || Integer.parseInt(sBuffer) < 0) return false;
                    sBuffer = "";
                }
                else sBuffer += s.charAt(i);
            }
            return true;
        }
    }


    /**
     *4.	Создайте функцию, которая принимает URL (строку),
     * удаляет дублирующиеся параметры запроса и параметры,
     * указанные во втором аргументе (который будет необязательным массивом).
     *
     * Примечание:
     * – Второй аргумент paramsToStrip является необязательным.
     * – paramsToStrip может содержать несколько параметров.
     * – Если есть повторяющиеся параметры запроса с разными значениями,
     * используйте значение последнего встречающегося параметра (см. Примеры № 1 и № 2 выше).
     */
    private static String stripUrlParams (String Url){
        LinkedList<String> linkedList = new LinkedList<>();

        for (int i = Url.indexOf("?"); i < Url.length(); i++){

            if (Url.charAt(i) == '&' || i == Url.indexOf("?") ){

               if (linkedList.contains(Url.charAt(i + 1) + "")){
                   Url = Url.replaceFirst(Url.charAt(i + 1) + "="
                                   + Url.charAt(Url.indexOf(Url.charAt(i + 1) + "=") + 2)
                           , "");
               }
               else {
                   linkedList.add(Url.charAt(i + 1) + "");
               }
            }
        }

        return Url;
    }
    private static String stripUrlParams (String Url, String[] UrlParams){
        LinkedList<String> linkedList = new LinkedList<>();

        for (int i = Url.indexOf("?"); i < Url.length(); i++){

            if (Url.charAt(i) == '&' || i == Url.indexOf("?") ){

                if (linkedList.contains(Url.charAt(i + 1) + "")){
                    Url = Url.replaceFirst(Url.charAt(i + 1) + "="
                                    + Url.charAt(Url.indexOf(Url.charAt(i + 1) + "=") + 2)
                            , "");
                }
                else {
                    linkedList.add(Url.charAt(i + 1) + "");
                }
            }
        }

        for (String param : UrlParams){
            Url = Url.replaceAll(param + "="
                            + Url.charAt(Url.indexOf(param + "=") + 2)
                    , "");
        }

        return Url;
    }


    /**
     *5.	Напишите функцию, которая извлекает три самых длинных слова из заголовка газеты и преобразует их в хэштеги.
     * Если несколько слов одинаковой длины, найдите слово, которое встречается первым.
     */
    private static String[] getHashTags (String s){
        String[] ans = {"#","#","#"};
        String[] strings = sentenceToWords(s);

        for (int j = 0; j < strings.length; j++) {
            for (int i = 0; i <= 2; i++) {
                if (ans[i].replaceFirst("#","").length() < strings[j].length()){
                    ans[i] ="#" + strings[j].toLowerCase();
                    break;
                }
            }
        }

        return ans;
    }


    /**
     * 6. Следующее число в последовательности - это наименьшее положительное число, равное сумме двух разных чисел
     * (которые уже есть в последовательности) ровно одним способом.
     * Тривиально, это 3, так как в стартовой последовательности есть только 2 числа.
     */
    private static int ulam (int n){
        int[] ans = new int[n];
        ans[0] = 1;
        if (n == 1) return ans[0];
        ans[1] = 2;
        if (n == 2) return ans[1];

        int nextNumb = 3;
        for (int j = 2; j < ans.length; j++){

            boolean fAnotherSumFound = false;
            for (int i = 1; i < ans.length; i++) {
                for (int ii = i + 1; ii < ans.length; ii++) {
                    if (ans[ii] + ans[i] == nextNumb && !fAnotherSumFound) {
                        nextNumb++;
                        fAnotherSumFound = true;
                    }
                }
            }


            ans[j] = nextNumb;
            nextNumb++;
        }

        return ans[n - 1];
    }


    /**
     *7. Напишите функцию, которая возвращает самую длинную неповторяющуюся подстроку для строкового ввода.
     * Примечание:
     * – Если несколько подстрок связаны по длине, верните ту, которая возникает первой.
     * – Бонус: можете ли вы решить эту проблему в линейном времени?
     */
    private static String longestNonrepeatingSubstring (String s){
        StringBuilder ans = new StringBuilder();
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < s.length(); i++){
            if (buffer.toString().contains(s.charAt(i) + "")){
                if (buffer.toString().length() > ans.toString().length()){
                    ans = buffer;
                }
                buffer.delete(0, buffer.length());
            }
            buffer.append(s.charAt(i));
        }

        return ans.toString();
    }


    /**
     *8. Создайте функцию, которая принимает арабское число и преобразует его в римское число.
     * Примечание:
     * – Все римские цифры должны быть возвращены в верхнем регистре.
     * – Самое большое число, которое может быть представлено в этой нотации, - 3,999.
     */
    private static String convertToRoman (int n){
        StringBuilder ans = new StringBuilder();
        String wholeNumber = Integer.toString(n);

        for (int i = 0; i < wholeNumber.length(); i++) {
            int pos = Math.abs(i - wholeNumber.length());
            int number = Integer.parseInt(wholeNumber.charAt(i) + "");

            if (number != 0) {

                switch (pos) {

                    case 1:
                        if (number <= 3) {
                            ans.append("I".repeat(number));
                        } else if (number <= 5) {
                            ans.append("I".repeat(5 - number)).append("V");
                        } else if (number < 9){
                            ans.append("V").append("I".repeat(9 - number));
                        } else {
                            ans.append("IX");
                        }
                        break;

                    case 2:
                        if (number <= 3) {
                            ans.append("".repeat(number));
                        } else if (number <= 5) {
                            ans.append("X".repeat(5 - number)).append("L");
                        } else if (number < 9){
                            ans.append("L").append("X".repeat(9 - number));
                        } else {
                            ans.append("XC");
                        }
                        break;

                    case 3:
                        if (number <= 3) {
                            ans.append("".repeat(number));
                        } else if (number <= 5) {
                            ans.append("C".repeat(5 - number)).append("D");
                        } else if (number < 9){
                            ans.append("D").append("C".repeat(9 - number));
                        } else {
                            ans.append("CM");
                        }
                        break;

                    case 4:
                        if (number <= 3) {
                            ans.append("M".repeat(number));
                        }
                }
            }
        }

        return ans.toString();
    }


    /**
     *9. Создайте функцию, которая принимает строку и возвращает true или false в зависимости от того, является ли формула правильной или нет.
     */
    private static boolean formula (String s){
        String[] strings = separatedBySpace(s);
        int ans = 0;
        int buffer = 0;

        if (s.replaceFirst("=", "").contains("=")) return false;
        try{
            ans = Integer.parseInt(strings[0]);
            String c = "";
            for (int i = 1; i < strings.length; i++){
                if (i % 2 == 0){
                    buffer = Integer.parseInt(strings[i]);
                    switch (c){
                        case "=":
                            return ans == buffer;
                        case "/":
                            ans /= buffer;
                            break;
                        case "*":
                            ans *= buffer;
                            break;
                        case "+":
                            ans += buffer;
                            break;
                        case "-":
                            ans -= buffer;
                    }
                } else {
                    c = strings[i];
                    if (!c.equals("/") && !c.equals("*") && !c.equals("+") && !c.equals("-") && !c.equals("=")) return false;

                }
            }
        } catch (NumberFormatException e){
            return false;
        }

        return false;
    }
    private static String[] separatedBySpace (String s){
        int ansArrayLength = 1;
        for (int i = 0; i < s.length(); i++){
            if ((s.charAt(i) + "").equals(" ")){
                ansArrayLength++;
            }
        }

        String[] ans = new String[ansArrayLength];
        Arrays.fill(ans, "");

        int k = 0;
        for (int i = 0; i < s.length(); i++){
            if ((s.charAt(i) + "").equals(" ")){
                k++;
            } else {
                ans[k] += s.charAt(i);
            }

        }
        return ans;
    }


    /**
     *10. Число может не быть палиндромом, но его потомком может быть.
     *  Прямой потомок числа создается путем суммирования каждой пары соседних цифр, чтобы создать цифры следующего числа.
     * Например, 123312 – это не палиндром, а его следующий потомок 363, где: 3 = 1 + 2; 6 = 3 + 3; 3 = 1 + 2.
     *
     * Создайте функцию, которая возвращает значение true, если само число является палиндромом или любой из его потомков вплоть
     * до 2 цифр (однозначное число - тривиально палиндром).
     * Примечание:
     * – Числа всегда будут иметь четное число цифр.
     */
    private static boolean palindromeDescendant (int n){
        if (isPalindrome(Integer.toString(n))) return true;

        for (int i = 1; i <= 2; i++){
            n = getDescendant(n);
            if (isPalindrome(Integer.toString(n))) return true;
        }
        return false;
    }
    private static int getDescendant (int n){
        String number = Integer.toString(n);
        String ans = "";

        for (int i = 1; i < number.length(); i+= 2){
            int num =  Integer.parseInt(number.indexOf(i) + "") + Integer.parseInt(number.indexOf(i - 1) + "");
            ans += Integer.toString(num);
        }

        return Integer.parseInt(ans);
    }
    private static boolean isPalindrome (String s){
        int halfLength =(int) Math.floor((double)s.length() / 2);
        for (int i = 0; i < halfLength; i++){
            if (s.charAt(i) != s.charAt(halfLength - i)) return false;
        }
        return true;
    }


}
