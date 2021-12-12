import java.util.ArrayList;
import java.util.Objects;

public class Cave {
    String name = "";
    Boolean isLarge = false;
    Boolean start = false;
    Boolean end = false;
    ArrayList<Cave> adjacents = new ArrayList<>();
    boolean doubleVisit = false;
    boolean visit = false;

    public void setName(String n) {
        name = n;
        if (Objects.equals(name, "start")) {
            start = true;
        } else if (Objects.equals(name, "end")) {
            end = true;
        } else if (Character.isUpperCase(name.charAt(0))) {
            isLarge = true;
        }
    }

    public void addAdjacent(Cave c) {
        if (!adjacents.contains(c)) {
            adjacents.add(c);
        }
    }

}
