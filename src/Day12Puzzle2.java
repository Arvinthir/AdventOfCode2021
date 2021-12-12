import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class Day12Puzzle2 {

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

        System.out.print("Caves: ");
        for (Cave c : caves) {
            System.out.print(c.name+", ");
        }
        System.out.println();

        ArrayList<Cave> smallCaves = new ArrayList<>();
        for (Cave c : caves) {
            if (!c.isLarge && !c.start && !c.end) {
                smallCaves.add(c);
            }
        }

        for (Cave sc : smallCaves) {
            Stack<String> path = new Stack();
            ArrayList<String> visited = new ArrayList<>();
            Cave start = checkCaves(caves, "start");
            sc.doubleVisit = true;
            System.out.println("Double visit: "+ sc.name);
            DFS(start, visited, path);
            sc.doubleVisit = false;
        }

        System.out.println("Paths: " + paths.size());
    }

    static void DFS(Cave current, ArrayList<String> visited, Stack<String> path) {
        //System.out.println("Visiting " + current.name);
        if (!current.isLarge) {
            if (!current.doubleVisit || current.visit) {
                visited.add(current.name);
            } else {
                current.visit = true;
            }
        }
        path.push(current.name);
        if (Objects.equals(current.name, "end")) {
            addPath(path);
            return;
        }
        for (Cave c : current.adjacents) {
            if (!visited.contains(c.name)) {
                DFS(c, visited, path);
                //System.out.println("Out of " + c.name + " back into " + current.name);
                visited.remove(c.name);
                path.pop();
                if (!path.contains(c.name)) {
                    c.visit = false;
                }
            }
        }
    }

    static void addPath(Stack<String> p) {
        ArrayList<String> path = new ArrayList<>();
        path = getStack(p, path);
        if (!paths.contains(path)) {
            paths.add(path);
        }
        //System.out.println(path);
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
