package day5.a;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author nikalsh
 */
public class AlchemicalReductionA {

    File file = new File("src/day5/a/input.txt");

    public AlchemicalReductionA() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder("");
        Scanner sc = new Scanner(file);
        int currLength = 0;
        int answer = 0;
        while (sc.hasNext()) {
            sb.append(sc.next());
        }

        OUTER:
        while (true) {
            currLength = sb.length();

            for (int i = 0; i < sb.length(); i++) {
                char currChar = sb.charAt(i);

                if (i + 1 == sb.length()) {
                    break;

                } else {
                    char nextChar = sb.charAt(i + 1);

                    if (Math.abs((int) currChar - (int) nextChar) == 32) {

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
        System.out.println(answer);
    }

    public static void main(String[] args) throws FileNotFoundException {
        AlchemicalReductionA ar = new AlchemicalReductionA();
    }
}
