package day2.a;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author nikalsh
 */
public class Checksum {

    private File file = new File("src/day2/a/input.txt/");
    private Scanner sc;
    private int threes = 0;
    private int twos = 0;

    public Checksum() throws FileNotFoundException {
        sc = new Scanner(file);
        String ID;
        Map<Character, Integer> duplicateFinder = null;

        while (sc.hasNext()) {
            boolean foundTwos = false;
            boolean foundThrees = false;
            
            ID = sc.nextLine();
            duplicateFinder = new HashMap<>();

            for (int i = 0; i < ID.length(); i++) {
                Character curr = ID.charAt(i);

                duplicateFinder.put(curr, (duplicateFinder.get(curr) == null ? 1
                        : duplicateFinder.get(curr) + 1));
            }

            for (int i = 0; i < ID.length(); i++) {
                Character curr = ID.charAt(i);

                if (!foundThrees && duplicateFinder.get(curr) == 3) {
                    foundThrees = true;
                    threes++;
                }

                if (!foundTwos && duplicateFinder.get(curr) == 2) {
                    foundTwos = true;
                    twos++;
                }

            }

        }
        int res = twos * threes;
        System.out.println(res);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Checksum cs = new Checksum();
    }
}
