// Hashan Kurukulasuriya 20221026 w1953615

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();

        List<List<String>> map = Dijkstra.readFile(new File("src/test.txt")); // Map in CW spec
//        List<List<String>> map = Dijkstra.readFile(new File("src/examples/maze30_5.txt"));
//        List<List<String>> map = Dijkstra.readFile(new File("src/benchmark_series/puzzle_2560.txt"));
        List<Integer[]> answer = Dijkstra.solve(map);

        Dijkstra.displayPath(answer);

        double elapsedTime = (System.currentTimeMillis() - startTime) / 1000.0;
        System.out.println("\nElapsed time: " + elapsedTime + " seconds");
    }
}
