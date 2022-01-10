package midlab2;

import java.util.ArrayList;
import java.util.Collections;

/**
 * HuffBaseNode class (TO-DO)
 **/
public class HuffTree {
    private HuffNode root;
    ArrayList<Integer> freq;
    ArrayList<HuffNode> tree = new ArrayList<>(); // Stores forest of trees
    ArrayList<HuffNode> leafNodes = new ArrayList<>();

    /** Constructors */
    public HuffTree(ArrayList<Character> chars, ArrayList<Integer> freq) {
        this.freq = freq;
        for(int i = 0; i < chars.size(); i++){
            tree.add(new HuffNode(chars.get(i), freq.get(i)));
        }
        while(tree.size() > 1){
            Collections.sort(tree);
            HuffNode second = tree.remove(1);
            HuffNode first = tree.remove(0);
            int weightSum = first.weight() + second.weight();
            HuffNode parent = new HuffNode('`', weightSum, first, second);
            tree.add(parent);
            root = parent;
        }
        assignCodes(root, "");
    }

    /**
     * assigns the Huffman code of characters
     */
    public void assignCodes(HuffNode current, String s) {
        if (current.isLeaf()) {
            current.setCode(s);
            leafNodes.add(current);
            return;
        }
        assignCodes(current.getLeft(), s + "0");
        assignCodes(current.getRight(), s + "1");
    }

    /**
     * Prints table of values
     */
    public void printTable(){
        System.out.printf("   %-12s|   %-18s|   %-18s %n", "Character", "Huffman Code","Number of Bits");
        for(HuffNode n : leafNodes){
            System.out.printf("   %-12s|   %-18s|   %-18d %n", n.character(), n.getCode(),countBits(n.getCode()));
        }
    }

    /**
     * Returns a String to the tree's String equivalent
     *
     * The char c parameter represents how you would like to output the String
     * 'a' = output huffman code separated by a space
     * otherwise,
     */
    public String toHuffCodeEquivalent(String s, char c) throws Exception {
        StringBuilder sb = new StringBuilder();
        String temp = s;

        try {
            while (!temp.isBlank()) {
                boolean proceed = false;
                char token = temp.charAt(0);
                for (HuffNode node : leafNodes) {
                    if (token == node.character()) {
                        if (c == 'a') {
                            sb.append(node.getCode()).append(" ");
                        } else {
                            sb.append(node.getCode());
                        }
                        proceed = true;
                    }
                }
                temp = temp.substring(1);
                if(!proceed) {
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            throw new Exception();
        }
        return sb.toString();
    }

    /**
     * Returns the String to the tree's String equivalent
     */
    public String toTextEquivalent(String s) throws Exception {
        StringBuilder sb = new StringBuilder();
        String[] token = s.split(" ");
        for (String code : token) {
            boolean proceed = false;
            for (HuffNode node : leafNodes) {
                if (code.equals(node.getCode())) {
                    sb.append(node.character());
                    proceed = true;
                }
            }
            if(!proceed) {
                throw new Exception();
            }
        }
        return sb.toString();
    }

    /**
     * Counts the number of characters in a string
     */
    public int countBits(String s){
        int res = 0;
        for (int i =0 ; i < s.length(); i++){
            res++;
        }
        return res;
    }

    /**
     * Computes the percentage of savings from HuffCode
     */
    public double computePercentageOfSavings(){
        double totalBits = 0;
        double huffBits = 0;

        for (Integer i: freq){
            totalBits += i;
        }
        totalBits *= 7;
        for(HuffNode n : leafNodes){
            huffBits += countBits(n.getCode());
        }

        return ((totalBits-huffBits)/totalBits) * 100;
    }

    /** Returns the root of the tree*/
    public HuffNode root() {
        return root;
    }

    /** Returns the weight of the root */
    public int weight() {
        return root.weight();
    }

    /**
     * Prints all the nodes of the tree (In ascending order)
     **/
    public void inorderTraversal() {
        inorderHelper(root);
    }
    private void inorderHelper(HuffNode node) {
        if (node == null)
            return;
        inorderHelper(node.getLeft());
        System.out.println(node);
        inorderHelper(node.getRight());
    }
}