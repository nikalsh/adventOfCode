package day8.a;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author nikalsh
 */
public class MemoryManeuver {

    File file = new File("src/day8/a/input.txt");
    static int index = 0;
    static int sum = 0;
    static List<Integer> input = new ArrayList<>();

    class Node {

        Header header;
        List<Node> childNodes;
        List<Integer> metaData;

        class Header {

            int numOfChildNodes;
            int numOfMetadataEntries;

            public Header(int numOfChildNodes, int numOfMetadataEntries) {

                this.numOfChildNodes = numOfChildNodes;
                this.numOfMetadataEntries = numOfMetadataEntries;
            }

        }

        public Node(int numOfChildNodes, int numOfMetadataEntries) {

            metaData = new ArrayList<>();
            childNodes = new ArrayList<>();
            this.header = new Header(numOfChildNodes, numOfMetadataEntries);

        }

        public void addMeta(int... metadata) {

            for (int i : metadata) {
                metaData.add(i);
            }
        }

        public void addChild(Node node) {
            childNodes.add(node);
        }
    }

    public MemoryManeuver() throws FileNotFoundException {

        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            input.add(sc.nextInt());
        }

        int[] rootHeader = getHeader(input);
        index += rootHeader.length;
        Node rootNode = new Node(rootHeader[0], rootHeader[1]);

        if (rootHeader[0] > 0) {
            for (int i = 0; i < rootHeader[0]; i++) {
                recursivelyAddChildrenTo(rootNode);
            }
        }

        for (int j = 0; j < rootNode.header.numOfMetadataEntries; j++) {
            int meta = input.get(index);
            rootNode.addMeta(meta);
            sum += meta;
            index++;

        }
    }

    public void recursivelyAddChildrenTo(Node root) {

        int[] childHeader = getHeader(input);
        index += childHeader.length;
        Node node = new Node(childHeader[0], childHeader[1]);
        root.addChild(node);

        if (childHeader[0] > 0) {
            for (int i = 0; i < childHeader[0]; i++) {
                recursivelyAddChildrenTo(node);
            }
        }

        for (int i = 0; i < node.header.numOfMetadataEntries; i++) {
            int meta = input.get(index);
            node.addMeta(meta);
            sum += meta;
            index++;
        }
    }

    public int[] getHeader(List<Integer> input) {
        return new int[]{input.get(index), input.get((index + 1))};
    }

    public static void main(String[] args) throws FileNotFoundException {
        MemoryManeuver mm = new MemoryManeuver();
        System.out.println(mm.sum);

    }

}
