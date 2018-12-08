package day8.b;

import day8.a.*;
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
    static int rootValue = 0;
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
        Node root = new Node(rootHeader[0], rootHeader[1]);

        if (rootHeader[0] > 0) {
            for (int i = 0; i < rootHeader[0]; i++) {
                recursivelyAddChildrenTo(root);
            }
        }

        for (int j = 0; j < root.header.numOfMetadataEntries; j++) {
            int meta = input.get(index);
            root.addMeta(meta);
            sum += meta;
            index++;
        }
        
        recursivelyCalculateValueOf(root);
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

    public void recursivelyCalculateValueOf(Node node) {

        if (node.childNodes.size() > 0) {
            
            for (int i = 0; i < node.metaData.size(); i++) {
                try {
                    recursivelyCalculateValueOf(node.childNodes.get((node.metaData.get(i)) - 1));
                } catch (Exception e) {
                    rootValue += 0;
                }
            }

        } else {
            for (int i = 0; i < node.metaData.size(); i++) {
                rootValue += node.metaData.get(i);
            }
        }
    }

    public int[] getHeader(List<Integer> input) {
        return new int[]{input.get(index), input.get((index + 1))};
    }

    public static void main(String[] args) throws FileNotFoundException {
        MemoryManeuver mm = new MemoryManeuver();
        System.out.println(mm.rootValue);
    }

}
