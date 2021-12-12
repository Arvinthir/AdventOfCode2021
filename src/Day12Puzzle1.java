import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class Day12Puzzle1 {

    static ArrayList<ArrayList<String>> paths = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day12.txt");
        Scanner s = new Scanner(inputFile);

        ArrayList<Cave> caves = new ArrayList<>();
        while (s.hasNextLine()) {
            String[] input = s.nextLine().split("-");
            for (String value : input) {
                if (checkCaves(caves, value) == null) {
                    Cave c = new Cave();
                    c.setName(value);
                    caves.add(c);
                }
            }
            checkCaves(caves,input[0]).addAdjacent(checkCaves(caves,input[1]));
            checkCaves(caves,input[1]).addAdjacent(checkCaves(caves,input[0]));
        }

        Stack<String> path = new Stack();
        ArrayList<String> visited = new ArrayList<>();
        Cave start = checkCaves(caves, "start");
        DFS(start, visited, path);

        System.out.println("Paths: " + paths.size());
    }

    static void DFS(Cave current, ArrayList<String> visited, Stack<String> path) {
        //System.out.println("Visiting " + current.name);
        if (!current.isLarge) {
            visited.add(current.name);
        }
        path.push(current.name);
        if (Objects.equals(current.name, "end")) {
            addPath(path);
            return;
        }
        for (Cave c : current.adjacents) {
            if (!visited.contains(c.name)) {
                DFS(c, visited, path);
                visited.remove(c.name);
                path.pop();
            }
        }
    }

    static void addPath(Stack<String> p) {
        ArrayList<String> path = new ArrayList<>();
        path = getStack(p, path);
        paths.add(path);
    }

    static ArrayList<String> getStack(Stack<String> p, ArrayList<String> path) {
        if (p.empty()) {
            return path;
        }
        String s = p.peek();
        p.pop();
        path = getStack(p, path);
        path.add(s);
        p.push(s);
        return path;
    }

    static Cave checkCaves(ArrayList<Cave> caves, String name) {
        for (Cave c: caves) {
            if (Objects.equals(c.name, name)) {
                return c;
            }
        }
        return null;
    }
}
