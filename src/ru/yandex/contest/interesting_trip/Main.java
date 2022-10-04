package ru.yandex.contest.interesting_trip;

import java.util.*;

/*
    1) Считаем все пары координат и добавим их в список
    2) Считаем номера стартового и конечного городов. Если города имеют одинаковые координаты - мы на месте, выводим 0
    3) Создадим граф. Добавим ребра между вершинами, если расстояние между городами (вес ребра) меньше максимального
       расстояния, которое может проехать Петя. Входные координаты городов могут повторяться, поэтому, чтобы не создавать
       петель, нам потребуется проверять, что расстояние между городами > 0 и не добавлять дубликатов ребер, если
       расстояние равно 0
    4) Запустим поиск в ширину из начального города в конечный. Для реализации BFS воспользуемся очередью. Для подсчета
       количества ребер до финальной вершины и для запоминания посещенных вершин воспользуемся специальным массивом:
            а) Для стартовой вершины зададим значение в массиве равным 1 (значение по-умолчанию для int - 0, задавая
               расстояние равным 1, мы можем отслеживать посещение стартовой вершины)
            б) При извлечении очередной вершины из очереди для каждой не посещенной вершины, в которую мы можем попасть
               из текущей, зададим значение в массиве равным значению текущей вершины + 1
    5) При достижении целевого города вернем значение, хранящееся в массиве, минус 1 (для стартового города значение
       равно 1)
    6) Если очередь пуста (мы обошли все вершины), а мы так и не вышли из функции, то возвращаем -1

    Сложность алгоритма - O(n^2) (для нахождения пути в худшем случае необходимо перебрать все пары вершин)
*/

public class Main {
    static class CoordPair {
        private final int x;
        private final int y;

        CoordPair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CoordPair coordPair = (CoordPair) o;
            return x == coordPair.getX() && y == coordPair.getY();
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Graph {
        private final List<List<Integer>> edgeList;

        Graph(int edgeCnt) {
            edgeList = new ArrayList<>();
            for (int i = 0; i < edgeCnt; i++) {
                edgeList.add(new ArrayList<>());
            }
        }

        void add(int src, int dest) {
            edgeList.get(src).add(dest);
        }

        int bfs(int start, int dest) {
            int[] level = new int[edgeList.size()];
            Queue<Integer> queue = new ArrayDeque<>();

            level[start] = 1;
            queue.offer(start);

            while (queue.size() != 0) {
                start = queue.poll();

                if (start == dest) {
                    return level[start] - 1;
                }

                for (int n : edgeList.get(start)) {
                    if (level[n] == 0) {
                        level[n] = level[start] + 1;
                        queue.offer(n);
                    }
                }
            }

            return -1;
        }
    }

    static long calcDist(CoordPair firstNode, CoordPair secondNode) {
        return Math.abs(firstNode.getX() - secondNode.getX()) + Math.abs(firstNode.getY() - secondNode.getY());
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int coordCnt = sc.nextInt();

            List<CoordPair> nodes = new ArrayList<>(coordCnt);

            for (int i = 0; i < coordCnt; i++) {
                nodes.add(new CoordPair(sc.nextInt(), sc.nextInt()));
            }

            long maxDist = sc.nextInt();

            Graph graph = new Graph(coordCnt);

            int start = sc.nextInt() - 1;
            int dest = sc.nextInt() - 1;

            if (nodes.get(start).equals(nodes.get(dest))) {
                System.out.println(0);
                return;
            }

            for (int i = 0; i < coordCnt; i++) {
                CoordPair firstNode = nodes.get(i);

                for (int j = 0; j < coordCnt; j++) {
                    if (i != j) {
                        long dist = calcDist(firstNode, nodes.get(j));
                        if (dist <= maxDist && dist > 0) {
                            graph.add(i, j);
                        }
                    }
                }
            }

            System.out.println(graph.bfs(start, dest));
        }
    }
}
