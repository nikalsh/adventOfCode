package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author nikalsh
 */
public class ResultingFrequency {

    private int res;
    private Scanner sc;
    private int firstOccur = 0;
    private int change;
    private int firstRes = 0;

    public ResultingFrequency(int init) throws FileNotFoundException {
        File file = new File("src/day1/input.txt/");
        res = init;
        Map<Integer, Integer> freqOccur = new HashMap<>();

//freq, occurences
        OUTER:
        while (true) {
            sc = new Scanner(file);

            while (sc.hasNext()) {
                change = sc.nextInt();
                res += change;

                freqOccur.put(res, (freqOccur.get(res) == null ? 1
                        : freqOccur.get(res) + 1));

                if (freqOccur.get(res) > 1) {
                    firstOccur = res;
                    System.out.println("first freq occurence > 1 found");
                    break OUTER;
                }
            }
            if (firstRes == 0) {
                firstRes = res;
            }
        }


        System.out.println("total (init: 0): " + firstRes);
        System.out.println("first occurence: " + firstOccur);
    }

    public static void main(String[] args) throws FileNotFoundException {
        ResultingFrequency rf = new ResultingFrequency(0);
    }
}
