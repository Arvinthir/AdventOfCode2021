import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day14 {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("inputs/day14.txt");
        Scanner s = new Scanner(inputFile);

        HashMap<String, Long> polymers = new HashMap<>();
        HashMap<String, Character> keys = new HashMap<>();
        HashMap<Character, Long> output = new HashMap<>();

        String start = s.nextLine();

        for (int i = 0; i < start.length(); i++) {
            if (!output.containsKey(start.charAt(i))) {
                output.put(start.charAt(i), (long) 1);
            } else {
                output.put(start.charAt(i), output.get(start.charAt(i)) + 1);
            }
        }

        s.nextLine();

        while(s.hasNextLine()) {
            String[] line = s.nextLine().split(" -> ");
            keys.put(line[0], line[1].charAt(0));
            if (!output.containsKey(line[1].charAt(0))) {
                output.put(line[1].charAt(0), (long) 0);
            }
        }

        keys.forEach((k, v) -> {
            polymers.put(k, (long) 0);
        });

        for (int i = 0; i < start.length()-1; i++) {
            String polymer = Character.toString(start.charAt(i));
            polymer += Character.toString(start.charAt(i+1));
            polymers.put(polymer, polymers.get(polymer) + 1);
        }

        for (int i = 0; i < 40; i++) {
            System.out.println(output);
            HashMap<String, Long> tmpPolymers = new HashMap<>(polymers);
            polymers.forEach((p, n) -> polymers.put(p, (long) 0));
            tmpPolymers.forEach((p, n) -> {
                if (n > 0) {
                    char k = keys.get(p);
                    output.put(k, output.get(k) + n);
                    char left = p.charAt(0);
                    char right = p.charAt(1);
                    String first = String.valueOf(left) + k;
                    String second = String.valueOf(k) + right;
                    polymers.put(first, polymers.get(first) + n);
                    polymers.put(second, polymers.get(second) + n);
                }
            });

        }

        System.out.println();
        System.out.println(output);

        Map.Entry<Character, Long> highest = null;
        Map.Entry<Character, Long> lowest = null;

        for (Map.Entry<Character, Long> entry : output.entrySet()) {
            if (highest == null || entry.getValue() > highest.getValue()) {
                highest = entry;
            }
            if (lowest == null || entry.getValue() < lowest.getValue()) {
                lowest = entry;
            }
        }

        System.out.println("Highest: " + highest);
        System.out.println("Lowest: " + lowest);
        System.out.println("Result: " + (highest.getValue()-lowest.getValue()));

    }
}
