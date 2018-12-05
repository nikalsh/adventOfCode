package day5.b;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author nikalsh
 */
public class AlchemicalReductionB {

    File file = new File("src/day5/a/input.txt");
    Map<Integer, Character> polymerLengthsAfterRemovingUnit;

    public AlchemicalReductionB() throws FileNotFoundException {
        StringBuilder input = new StringBuilder("");
        Scanner sc = new Scanner(file);
        int currLength = 0;
        int answer = 0;
        while (sc.hasNext()) {
            input.append(sc.next());
        }

        polymerLengthsAfterRemovingUnit = new TreeMap<>();

//        removeUnitsFromPolymer(sb);
        StringBuilder asd = new StringBuilder("");

        int min = Integer.MAX_VALUE;

        char i = 'a';
        for (; i < 'z'; i++) {

            int ans = fullyReactPolymers(removeUnitsFromPolymer(input, i));

            min = (ans < min ? ans : min);
        }

        System.out.println(min);
    }

    public StringBuilder removeUnitsFromPolymer(StringBuilder sb, char toRemove) {
        StringBuilder modified = new StringBuilder("");
        char lower = toRemove;
        char upper = (char) (toRemove - 32);

        for (int i = 0; i < sb.length(); i++) {

            if (sb.charAt(i) == lower || sb.charAt(i) == upper) {
                continue;
            }
            modified.append(sb.charAt(i));
        }
        return modified;
    }

    public int fullyReactPolymers(StringBuilder sb) {
        int currLength = 0;
        int answer = 0;

        OUTER:
        while (true) {
            currLength = sb.length();

            for (int i = 0; i < sb.length(); i++) {
                char currChar = sb.charAt(i);

                if (i + 1 == sb.length()) {
                    break;

                } else {
                    char nextChar = sb.charAt(i + 1);

                    if (Math.abs(currChar - nextChar) == 32) {
                        sb.deleteCharAt(i);
                        sb.deleteCharAt(i);
                    }
                }
            }
            if (currLength == sb.length()) {
                answer = sb.length();
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws FileNotFoundException {
        AlchemicalReductionB ar = new AlchemicalReductionB();
    }
}
