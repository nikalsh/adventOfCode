package day2.b;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author nikalsh
 */
public class CharDiff {

    private File file = new File("src/day2/b/input.txt/");
    private Scanner sc;

    public CharDiff() throws FileNotFoundException {

        sc = new Scanner(file);

        List<String> IDS = new ArrayList<>();
        String res = "";
        int diffIndex = -1;
        int diff = 0;

        while (sc.hasNext()) {

            IDS.add(sc.nextLine());

        }

        List<String> compareList = IDS;
        OUTER:
        for (int i = 0; i < IDS.size(); i++) {

            INNER:
            for (int j = 0; j < compareList.size(); j++) {
                diff = IDS.get(i).length();

                for (int k = 0; k < IDS.get(i).length(); k++) {

                    if (IDS.get(i).equals(compareList.get(j))) {
                        System.out.println("same, skipping..");
                        continue INNER;
                    }

                    if (IDS.get(i).charAt(k) == compareList.get(j).charAt(k)) {
                        diff--;

                        if (diff == 1) {
                            System.out.println("found two words 1 diff char");

                            res = IDS.get(i);
                            System.out.println(diffIndex);

                            res = res.substring(0, diffIndex)
                                    + res.substring(diffIndex + 1, res.length());

                            break OUTER;
                        }

                    } else {
                        diffIndex = k;

                    }

                }

            }

        }

        System.out.println(res);

    }

    public static void main(String[] args) throws FileNotFoundException {
        CharDiff cf = new CharDiff();

    }

}
