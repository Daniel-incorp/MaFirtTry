package com.company.java_tasks;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

public class Java_Tasks_5_6 {

    public static void showResults() {

        System.out.println("___[1]___");
        System.out.println(Arrays.toString(encrypt("Cogniferous")));
        int[] i1 = {72, 116, 108, 115, 110, 107, 106, 119, 116, 122, 120};
        System.out.println(decrypt(i1));

        System.out.println("___[2]___");
        System.out.println(canMove("пешка", "A1", "A2"));
        System.out.println(canMove("конь", "A1", "C2"));
        System.out.println(canMove("слон", "A1", "E5"));
        System.out.println(canMove("ферзь", "A5", "E5"));
        System.out.println(canMove("король", "A3", "B4"));

        System.out.println("___[3]___");
        System.out.println(canComplete("butl", "beautiful") );

        System.out.println("___[4]___");
        int[] i4 = {16, 28};
        System.out.println(sumDigProd(i4));

        System.out.println("___[5]___");
        String[] strings = {"toe", "ocelot", "maniac"};
        System.out.println(Arrays.stream(sameVowelGroup(strings))
                .filter(Objects::nonNull) // убираем null элементы массива
                .collect(Collectors.joining(", ")));

        System.out.println("___[6]___");
        System.out.println(validateCard("1234567890123456"));
        System.out.println(validateCard("1234567890123452"));

        System.out.println("___[7]___");
        System.out.println(numToEng(87));
        System.out.println(numToEng(4));
        System.out.println(numToEng(322));

        System.out.println("___[8]___");
        System.out.println(getSha256Hash("password123"));

        System.out.println("___[9]___");
        System.out.println(correctTitle("sansa stark, lady of winterfell."));

        System.out.println("___[10]___");
        System.out.println(hexLattice(19));

    }


    /**
     *1.	Пришло время отправлять и получать секретные сообщения.
     * Создайте две функции, которые принимают строку и массив и возвращают закодированное или декодированное сообщение.
     * Первая буква строки или первый элемент массива представляет собой символьный код этой буквы. Следующие элементы-это различия между символами: например, A +3 --> C или z -1 --> y.
     *
     */
    private static int[] encrypt (String s){
        int[] ans = new int[s.length()];

        for (int i = 0; i < s.length();i++){
            ans[i] = s.charAt(i) + 5;
        }
        return ans;
    }
    private static String decrypt (int[] ints){
        String ans = "";

        for (int i = 0; i < ints.length;i++){
            ans += (char) (ints[i] - 5);
        }
        return ans;
    }

    /**
     *2.	Создайте функцию, которая принимает имя шахматной фигуры, ее положение и целевую позицию. Функция должна возвращать true, если фигура может двигаться к цели, и false, если она не может этого сделать.
     * Возможные входные данные - "пешка", "конь", "слон", "Ладья", "Ферзь"и " король".
     */
    private static boolean canMove (String figure, String start, String finish){
        figure = figure.toLowerCase();
        switch (figure){
            case "пешка":
                return (start.charAt(0) == finish.charAt(0) &&
                        start.charAt(1) + 1 == finish.charAt(1));
            case "конь" :
                return ( (Math.abs(start.charAt(0) - finish.charAt(0)) == 2 &&
                          Math.abs(start.charAt(1) - finish.charAt(1)) == 1) ||
                         (Math.abs(start.charAt(0) - finish.charAt(0)) == 1 &&
                          Math.abs(start.charAt(1) - finish.charAt(1)) == 2));
            case "слон" :
                return ( (Math.abs(start.charAt(0) - finish.charAt(0)) == Math.abs(start.charAt(1) - finish.charAt(1))));
            case "ладья" :
                return ( start.charAt(0) == finish.charAt(0) ||
                         start.charAt(1) == finish.charAt(1));
            case "ферзь" :
                return ( (start.charAt(0) == finish.charAt(0) ||
                        start.charAt(1) == finish.charAt(1)) ||
                        ( (Math.abs(start.charAt(0) - finish.charAt(0)) == Math.abs(start.charAt(1) - finish.charAt(1)))));
            case "король" :
                return ( Math.abs(start.charAt(0) - finish.charAt(0)) <= 1 ||
                         Math.abs(start.charAt(1) - finish.charAt(1)) <= 1);
        }

        return false;
    }


    /**
     *3.	Входная строка может быть завершена, если можно добавить дополнительные буквы, и никакие буквы не должны быть удалены, чтобы соответствовать слову. Кроме того, порядок букв во входной строке должен быть таким же, как и порядок букв в последнем слове.
     * Создайте функцию, которая, учитывая входную строку, определяет, может ли слово быть завершено.
     */
    private static boolean canComplete (String s1, String s2){

        int j = 0;
        for (int i = 0; i < s2.length();i++){
            if (s1.charAt(j) == s2.charAt(i)) j++;
        }
        return j == s1.length();
    }


    /**
     *4.	Создайте функцию, которая принимает числа в качестве аргументов, складывает их вместе и возвращает произведение цифр до тех пор,
     * пока ответ не станет длиной всего в 1 цифру.
     */
    private static int sumDigProd (int[] ints){
        int[] ans;
        ans = ints;

        while (ans.length > 1 || Integer.toString(ans[0]).length() != 1){

            String sArray = String.valueOf(Arrays.stream(ans).sum());
            int multi = 1;
            for (int i = 0; i < sArray.length(); i++){
                multi *= Integer.parseInt(sArray.charAt(i) + "");
            }

            sArray = Integer.toString(multi);
            ans = new int[sArray.length()];
            for (int i = 0; i < sArray.length(); i++){
                ans[i] = Integer.parseInt(sArray.charAt(i) + "");
            }
        }
        return ans[0];
    }


    /**
     *5.	Напишите функцию, которая выбирает все слова, имеющие все те же гласные (в любом порядке и / или количестве), что и первое слово, включая первое слово.
     */
    private static String[] sameVowelGroup (String[] strings) {
        String[] ans = new String[strings.length];
        ans[0] = strings[0];

        String FirstWord = "";
        for (int i = 0; i < strings[0].length(); i++){
            if (isVowel(strings[0].charAt(i))) FirstWord += strings[0].charAt(i);
        }

        int k = 1;
        for (int j = 1; j < strings.length; j++){

            String sBuffer = "";
            for (int i = 0; i < strings[j].length();i++){
                if (isVowel(strings[j].charAt(i))) sBuffer += strings[j].charAt(i);
            }
            if (sameSymbols(FirstWord, sBuffer)) {
                ans[k] = strings[j];
                k++;
            }

        }

        return ans;
    }
    private static boolean isVowel (char c){
        return c=='a' || c=='e' ||
                c=='i' || c=='o' ||
                c=='u';
    }
    private static boolean sameSymbols (String s1, String s2){
        String sBuffer1 = s1;
        String sBuffer2 = s2;


        for (int i = 0; i < sBuffer1.length(); i++){
            s1 = s1.replaceAll(sBuffer2.charAt(i) + "", "");
            s2 = s2.replaceAll(sBuffer1.charAt(i) + "", "");
        }

        return (s1.isEmpty() && s2.isEmpty());
    }


    /**
     *6.	Создайте функцию, которая принимает число в качестве аргумента и возвращает true, если это число является действительным номером кредитной карты, а в противном случае-false.
     * Номера кредитных карт должны быть длиной от 14 до 19 цифр и проходить тест Луна, описанный ниже:
     * – Удалите последнюю цифру (это"контрольная цифра").
     * – Переверните число.
     * – Удвойте значение каждой цифры в нечетных позициях. Если удвоенное значение имеет более 1 цифры, сложите цифры вместе (например, 8 x 2 = 16 ➞ 1 + 6 = 7).
     * – Добавьте все цифры.
     * – Вычтите последнюю цифру суммы (из шага 4) из 10. Результат должен быть равен контрольной цифре из Шага 1.
     */
    private static boolean validateCard (String s){
        if (s.length() < 14 || s.length() > 19) return false;

        // Step 1: check digit
        int controlNumber = Integer.parseInt(s.charAt(s.length() - 1) + "");
        s = s.substring(0, s.length() - 1);

        // Step 2: num reversed
        String sBuffer = "";
        for (int i = s.length() - 1; i >= 0; i--){
            sBuffer += s.charAt(i);
        }
        s = sBuffer;

        // Step 3: digit array after selective doubling
        int iBuffer;
        String sBuffer2 = "";
        for (int i = 0; i < s.length(); i += 2){
            iBuffer = Integer.parseInt(s.charAt(i) + "") * 2;

            while (iBuffer > 9){
                sBuffer = Integer.toString(iBuffer);

                iBuffer = Integer.parseInt(sBuffer.charAt(0) + "");
                iBuffer += Integer.parseInt(sBuffer.charAt(1) + "");
            }
            sBuffer2 = s.substring(0, i) + (char) (iBuffer + '0') + s.substring(i+1);
            s = sBuffer2;
        }

        // Step 4: sum
        iBuffer = 0;
        for (int i = 0; i < s.length(); i++){
            iBuffer += Integer.parseInt(s.charAt(i) + "");
        }

        // Step 5
        sBuffer = Integer.toString(iBuffer);
        return 10 - Integer.parseInt(sBuffer.charAt(sBuffer.length() - 1) + "") == controlNumber;
    }


    /**
     *7.	Напишите функцию, которая принимает положительное целое число от 0 до 999 включительно и возвращает строковое представление этого целого числа, написанное на английском языке
     */
    private static String numToEng (int i){
        String sNumber = Integer.toString(i);

        String ans = "";

        if (i <= 20) return smallNumToEng(i);
        if (i <= 99) return smallNumToEng(Integer.parseInt(sNumber.charAt(0) + "") * 10) + " " + smallNumToEng(Integer.parseInt(sNumber.charAt(1) + ""));

        return smallNumToEng(Integer.parseInt(sNumber.charAt(0) + "")) + " hundred " + smallNumToEng(Integer.parseInt(sNumber.charAt(1) + "") * 10) + " " + smallNumToEng(Integer.parseInt(sNumber.charAt(2) + ""));
    }
    private static String smallNumToEng (int i){
        if (i == 0) return "zero";
        else if (i == 1) return "one";
        else if (i == 2) return "two";
        else if (i == 3) return "three";
        else if (i == 4) return "four";
        else if (i == 5) return "five";
        else if (i == 6) return "six";
        else if (i == 7) return "seven";
        else if (i == 8) return "eight";
        else if (i == 9) return "nine";
        else if (i == 10) return "ten";
        else if (i == 11) return "eleven";
        else if (i == 12) return "twelve";
        else if (i == 13) return "thirteen";
        else if (i == 14) return "fourteen";
        else if (i == 15) return "fifteen";
        else if (i == 16) return "sixteen";
        else if (i == 17) return "seventeen";
        else if (i == 18) return "eighteen";
        else if (i == 19) return "nineteen";
        else if (i == 20) return "twenty";
        else if (i == 30) return "thirty";
        else if (i == 40) return "forty";
        else if (i == 50) return "fifty";
        else if (i == 60) return "sixty";
        else if (i == 70) return "seventy";
        else if (i == 80) return "eighty";
        else if (i == 90) return "ninety";

        return "ERROR";
    }


    /**
     *  8.	Хеш-алгоритмы легко сделать одним способом, но по существу невозможно сделать наоборот. Например, если вы хешируете что-то простое, например, password123, это даст вам длинный код, уникальный для этого слова или фразы. В идеале, нет способа сделать это в обратном порядке. Вы не можете взять хеш-код и вернуться к слову или фразе, с которых вы начали.
     * Создайте функцию, которая возвращает безопасный хеш SHA-256 для данной строки. Хеш должен быть отформатирован в виде шестнадцатеричной цифры.
     */
    private static String getSha256Hash (String s){
        return bytesToHex(s.getBytes());
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


    /**
     *9.	Напишите функцию, которая принимает строку и возвращает строку с правильным регистром для заголовков символов в серии "Игра престолов".
     * Слова and, the, of и in должны быть строчными. Все остальные слова должны иметь первый символ в верхнем регистре, а остальные-в Нижнем.
     */
    private static String correctTitle (String s){
        s = s.toLowerCase();
        String[] words = sentenceToWords(s);

        for (int i = 0; i < words.length; i++){
            String word = words[i];
            if (!word.equals("and") && !word.equals("the") && !word.equals("of") && !word.equals("in"))
                s = s.replace(word, (word.charAt(0) + "").toUpperCase() + word.substring(1));

        }

        return s;

    }

    /**
     * Принимает строку, возвращает массив слов этой строки
     */
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
     *10.	Как указано в онлайн-энциклопедии целочисленных последовательностей:
     * Гексагональная решетка - это привычная двумерная решетка, в которой каждая точка имеет 6 соседей.
     * Центрированное шестиугольное число - это центрированное фигурное число, представляющее шестиугольник с точкой в центре и всеми другими точками, окружающими центральную точку в шестиугольной решетке.
     *
     * Illustration of initial terms:
     * .
     * .                                 o o o o
     * .                   o o o        o o o o o
     * .         o o      o o o o      o o o o o o
     * .   o    o o o    o o o o o    o o o o o o o
     * .         o o      o o o o      o o o o o o
     * .                   o o o        o o o o o
     * .                                 o o o o
     * .
     * .   1      7          19             37
     * .
     *
     * Напишите функцию, которая принимает целое число n и возвращает "недопустимое", если n не является центрированным шестиугольным числом или его иллюстрацией в виде многострочной прямоугольной строки в противном случае.
     */
    private static String hexLattice (int i1){
        StringBuilder ans = new StringBuilder();
        int HexRadius = 1;

        int BufferOf_i = i1 ;
        while (BufferOf_i > 1){
            BufferOf_i -= HexRadius * 6;
            HexRadius++;
        }
        if (BufferOf_i <= 0) return "недопустимое";

        for (int i = 1; i <= HexRadius * 2 - 1; i++){

            ans.append(" ".repeat(Math.abs(HexRadius - i) + 1));
            ans.append("o ".repeat( HexRadius * 2 - Math.abs(HexRadius - i) ));
            ans.append("\n");
        }

        return ans.toString();
    }


}
