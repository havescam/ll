import java.util.*;

public class GreedyMIS {
    // Задание 18: жадный выбор вершины минимальной степени
    public static Set<Integer> maxIndependentSet(int[][] adjMatrix) {
        int n = adjMatrix.length;
        boolean[] deleted = new boolean[n];
        Set<Integer> result = new HashSet<>();

        while (true) {
            int v = -1;

            // найти вершину v с минимальной степенью среди не удалённых
            int minDeg = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (deleted[i]) continue;
                int deg = 0;
                for (int j = 0; j < n; j++) {
                    if (!deleted[j] && adjMatrix[i][j] == 1) deg++;
                }
                if (deg < minDeg) { minDeg = deg; v = i; }
            }

            if (v == -1) break;          // вершин не осталось
            result.add(v);                // добавляем в независимое множество

            // удалить v и всех её соседей
            deleted[v] = true;
            for (int u = 0; u < n; u++) {
                if (adjMatrix[v][u] == 1) deleted[u] = true;
            }
        }
        return result;
    }

    // Пример запуска
    public static void main(String[] args) {
        int[][] g = {
            {0,1,0,0,1},
            {1,0,1,0,0},
            {0,1,0,1,0},
            {0,0,1,0,1},
            {1,0,0,1,0}
        };
        Set<Integer> mis = maxIndependentSet(g);
        System.out.println("Результат работы: " + mis);
    }
}

Результат работы: [0, 2]
