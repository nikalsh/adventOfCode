package day4.a;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author nikalsh
 */
public class ReposeRecord {

    class HashMinutes {

        Map<Integer, Integer> minutes;

        public HashMinutes() {

            minutes = new TreeMap<>();
        }

        public HashMinutes add(String startMinute, String endMinute) {

            for (int j = Integer.parseInt(startMinute); j < Integer.parseInt(endMinute); j++) {
                minutes.put(j, (minutes.get(j) == null ? 1 : minutes.get(j) + 1));
            }

            return this;
        }

        public void print() {

            Set set = minutes.entrySet();
            Iterator i = set.iterator();

            while (i.hasNext()) {
                Map.Entry treeMap = (Map.Entry) i.next();
                System.out.print(treeMap.getKey() + ": ");
                System.out.println(treeMap.getValue());
            }
        }

        public int getMostSleepedMinute() {

            Integer max = minutes.values().stream().max(Integer::compare).get();

            for (Entry<Integer, Integer> entry : minutes.entrySet()) {
                if (entry.getValue().equals(max)) {
                    return entry.getKey();
                }
            }
            return 0;
        }
    }

    private File file = new File("src/day4/a/input.txt/");
    Map<String, String> parseMap = new TreeMap<>();

    public ReposeRecord() throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(file);
        Map<String, Integer> IDtoMinutes = new HashMap<>();

        Map<String, HashMinutes> minutesMeasured = new HashMap<>();
        String in = "";
        String currGuard = "";
        int minutesSlept = 0;
        String minuteStart = "";
        String minuteEnd = "";
        int max = Integer.MIN_VALUE;
        String topSleeperID = "";

        while (sc.hasNext()) {

            in = sc.nextLine();
            String date = in.substring(1, 17);
            String toValue = in;
            parseMap.put(date, toValue);

        }

        Set set = parseMap.entrySet();
        Iterator i = set.iterator();

        while (i.hasNext()) {

            Map.Entry treeMap = (Map.Entry) i.next();
            System.out.println(treeMap.getValue());
            in = (String) treeMap.getValue();

            if (in.contains("Guard")) { //new guard shift

                currGuard = in.substring(26, 30);
                System.out.println(currGuard);

            }

            if (in.contains("asleep")) {

                minuteStart = in.substring(15, 17);
            }

            if (in.contains("wakes")) {

                if (currGuard.equals("")) {
                    continue;
                }

                minuteEnd = in.substring(15, 17);
                minutesSlept = ((Integer.parseInt(minuteEnd)) - Integer.parseInt(minuteStart));

                if (minutesMeasured.get(currGuard) == null) {

                    HashMinutes hma = new HashMinutes();
                    minutesMeasured.put(currGuard, hma.add(minuteStart, minuteEnd));

                } else {

                    HashMinutes asd = minutesMeasured.get(currGuard);
                    minutesMeasured.put(currGuard, asd.add(minuteStart, minuteEnd));
                }

                if (!currGuard.equals("")) {

                    IDtoMinutes.put(currGuard, (IDtoMinutes.get(currGuard) == null ? minutesSlept
                            : IDtoMinutes.get(currGuard) + minutesSlept));

                    if (IDtoMinutes.get(currGuard) > max) {

                        max = IDtoMinutes.get(currGuard);
                        topSleeperID = currGuard;
                        System.out.println("new topSleeperID = " + topSleeperID);
                    }
                }
                System.out.println("ID: " + currGuard + " : " + IDtoMinutes.get(currGuard) + " minutes");
            }

        }

        int mostSleepedMinute = minutesMeasured.get(topSleeperID).getMostSleepedMinute();
        System.out.println("most slept minutes: " + topSleeperID + " slept for " + IDtoMinutes.get(topSleeperID) + " minutes");
        System.out.println(mostSleepedMinute);
        System.out.println("answer: " + (mostSleepedMinute * Integer.parseInt(topSleeperID)));
    }

    public static void main(String[] args) throws IOException {
        ReposeRecord msg = new ReposeRecord();
    }
}
