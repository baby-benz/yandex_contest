# Генерация скобочных последовательностей

## Описание

Дано целое число __n__. Требуется вывести все правильные скобочные последовательности длины __2 ⋅ n__, упорядоченные лексикографически (см. https://ru.wikipedia.org/wiki/Лексикографический_порядок).

В задаче используются только круглые скобки.

Желательно получить решение, которое работает за время, пропорциональное общему количеству правильных скобочных последовательностей в ответе, и при этом использует объём памяти, пропорциональный __n__.

## Формат ввода

Единственная строка входного файла содержит целое число __n__, 0 ≤ __n__ ≤ 11

## Формат вывода

Выходной файл содержит сгенерированные правильные скобочные последовательности, упорядоченные лексикографически.

### Пример 1:

    Ввод:   2
    Вывод:  (())
            ()()

### Пример 2:

    Ввод:   3
    Вывод:  ((()))
            (()())
            (())()
            ()(())
            ()()()

## Ограничения:

| Ограничение         | Значение                         |
| ------------------- | -------------------------------- |
| Ограничение времени |	1 секунда                        |
| Ограничение памяти  | 20Mb                             |
| Ввод                |	стандартный ввод или input.txt   |
| Вывод               | стандартный вывод или output.txt |