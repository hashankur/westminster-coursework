// Hashan Kurukulasuriya 20221026 w1953615

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dijkstra {

    /**
     * The four cardinal directions represented as 2D integer arrays.
     * Each array represents a direction in the format {rowChange, columnChange}.
     * The directions are: down, up, right, and left respectively.
     */
    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * Reads a grid from a file and returns it as a list of lists of strings.
     * Each string represents a cell in the grid.
     *
     * @param file the file to read the grid from
     * @return the grid as a list of lists of strings
     * @throws FileNotFoundException if the file does not exist
     */
    public static List<List<String>> readFile(File file) throws FileNotFoundException {
        List<List<String>> grid = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("");
            grid.add(Arrays.asList(line));
        }
        return grid;
    }

    /**
     * The method uses Dijkstra's algorithm to find the shortest path.
     * The method returns the path as a list of integer arrays, where each array represents the coordinates of a cell in the path.
     * The coordinates are represented as [row, column].
     *
     * @param map the map of the puzzle
     * @return the shortest path from the start cell to the end cell
     */
    public static List<Integer[]> solve(List<List<String>> map) {
        int rows = map.size();
        int columns = map.get(0).size();
        int startRow = -1;
        int startCol = -1;
        int endCol = -1;
        int endRow = -1;
        boolean[][] visited = new boolean[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (map.get(i).get(j).equals("S")) {
                    startRow = i;
                    startCol = j;
                }
                if (map.get(i).get(j).equals("F")) {
                    endRow = i;
                    endCol = j;
                }
            }
        }

        PriorityQueue<Queue> priorityQueue = new PriorityQueue<>();
        Integer[] arr = new Integer[]{startRow, startCol};
        List<Integer[]> arrayList = new ArrayList<>();
        arrayList.add(arr);
        priorityQueue.add(new Queue(0, startRow, startCol, arrayList));
        visited[startRow][startCol] = true;

        while (!priorityQueue.isEmpty()) {
            Queue queueObj = priorityQueue.remove();
            List<Integer[]> path = queueObj.coordinates;
            if (queueObj.row == endRow && queueObj.column == endCol) {
                return path;
            }

            for (int[] direction : DIRECTIONS) {
                int count = 0;
                int currentRow = queueObj.row;
                int currentColumn = queueObj.column;
                int rowDirection = direction[0];
                int columnDirection = direction[1];

                while (isValidCell(map, currentRow + direction[0], currentColumn + direction[1])) {
                    count += 1;
                    currentRow += rowDirection;
                    currentColumn += columnDirection;

                    if (map.get(currentRow).get(currentColumn).equals("F")) {
                        path.add(new Integer[]{currentRow, currentColumn});
                        return path;
                    }
                }

                if (count > 0) {
                    int newRow = queueObj.row + count * rowDirection;
                    int newColumn = queueObj.column + count * columnDirection;
                    if (!visited[newRow][newColumn]) {
                        List<Integer[]> newPath = new ArrayList<>(path);
                        newPath.add(new Integer[]{newRow, newColumn});
                        priorityQueue.add(new Queue(count, newRow, newColumn, newPath));
                        visited[newRow][newColumn] = true;
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * Checks if a cell is valid.
     * A cell is considered valid if it is within the bounds of the map, and it is not a rock.
     *
     * @param map the map of the puzzle
     * @param row the row coordinate of the cell
     * @param col the column coordinate of the cell
     * @return true if the cell is valid, false otherwise
     */
    private static boolean isValidCell(List<List<String>> map, int row, int col) {
        int rows = map.size();
        int cols = map.get(0).size();
        return 0 <= row && row < rows && 0 <= col && col < cols && !map.get(row).get(col).equals("0");
    }

    /**
     * Displays the path from the start cell to the end cell.
     * Each movement is printed as a line in the format: "{step number}. {direction} ({column}, {row})"
     *
     * @param path the path to display
     */
    public static void displayPath(List<Integer[]> path) {
        if (path.isEmpty()) {
            System.out.println("No path found!");
            return;
        }

        for (int i = 0; i < path.size(); i++) {
            Integer[] current = path.get(i);
            int currentX = current[0];
            int currentY = current[1];

            String direction = "";
            if (i == 0) {
                direction = "Start at";
            } else {
                Integer[] previous = path.get(i - 1);
                int previousX = previous[0];
                int previousY = previous[1];

                if (currentX < previousX) {
                    direction = "Move up to";
                } else if (currentX > previousX) {
                    direction = "Move down to";
                } else if (currentY < previousY) {
                    direction = "Move left to";
                } else {
                    direction = "Move right to";
                }
            }
            System.out.printf("%d. %s (%d, %d)\n", i + 1, direction, currentY + 1, currentX + 1);
        }
        System.out.println((path.size() + 1) + ". Done!");
    }
}

// https://howtodoinjava.com/java/collections/java-priorityqueue/
class Queue implements Comparable<Queue> {
    int distance;
    int row;
    int column;
    List<Integer[]> coordinates;

    public Queue(int distance, int row, int column, List<Integer[]> coordinates) {
        this.distance = distance;
        this.row = row;
        this.column = column;
        this.coordinates = coordinates;
    }

    @Override
    public int compareTo(Queue queue) {
        return Integer.compare(distance, queue.distance);
    }
}
