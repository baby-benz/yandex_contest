package ru.yandex.contest.anagrams;

import java.util.*;

/*
    1) Если на вход поданы строки разных длин, то они не могут быть анаграммами никак - выводим 0, заканчиваем выполнение программы
    2) Составим словарь частот символов первой строки
    3) Таким же образом попробуем составить словарь для второй строки, но с дополнительными проверками:
        а) Следует убедиться, что текущий обрабатываемый символ есть в первом словаре.
           В обратном случае - вывести 0 и закончить выполнение
        б) Также будем следить за тем, чтобы частота текущего символа оказалась не больше частоты этого символа в первом словаре.
           В обратном случае - вывести 0 и закончить выполнение
    4) Если второй словарь успешно составлен, то строки являются анаграммами - выводим 1

    Сложность алгоритма O(n + m), где n - длина первой строки, а m - длина второй
*/

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String string1 = sc.nextLine();
            String string2 = sc.nextLine();

            if (string1.length() == string2.length()) {
                Map<Character, Integer> string1Dict = new HashMap<>();

                for (int i = 0; i < string1.length(); i++) {
                    char curChar = string1.charAt(i);
                    if (string1Dict.containsKey(curChar)) {
                        string1Dict.put(curChar, string1Dict.get(curChar) + 1);
                    } else {
                        string1Dict.put(curChar, 1);
                    }
                }

                Map<Character, Integer> string2Dict = new HashMap<>();

                for (int i = 0; i < string2.length(); i++) {
                    char curChar = string2.charAt(i);
                    if (string1Dict.containsKey(curChar)) {
                        if (string2Dict.containsKey(curChar)) {
                            int curVal = string2Dict.get(curChar);
                            if (curVal < string1Dict.get(curChar)) {
                                string2Dict.put(curChar, curVal + 1);
                            } else {
                                System.out.println(0);
                                return;
                            }
                        } else {
                            string2Dict.put(curChar, 1);
                        }
                    } else {
                        System.out.println(0);
                        return;
                    }
                }

                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
