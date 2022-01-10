package midlab2;
/**
 * HuffNode class
 **/
public class HuffNode implements Comparable<HuffNode> {
    private char character;
    private int weight;
    private HuffNode left;
    private HuffNode right;
    private String code;

    /** Constructors **/
    public HuffNode(char ch, int wt) {
        character = ch;
        weight = wt;
    }
    public HuffNode(char ch, int wt, HuffNode l, HuffNode r) {
        character = ch;
        left = l;
        right = r;
        weight = wt;
    }

    /** Getters **/
    public void setLeft(HuffNode l) {
        left = l;
    }
    public void setRight(HuffNode r) {
        right = r;
    }
    public void setCharacter(char c) {
        character = c;
    }
    public void setWeight(int w) {
        weight = w;
    }
    public void setCode(String code) {
        this.code = code;
    }
    /** Setters **/
    public HuffNode getLeft() {
        return left;
    }
    public HuffNode getRight() {
        return right;
    }
    public char character() {
        return character;
    }
    public int weight() {
        return weight;
    }
    public String getCode() {
        return code;
    }

    /**
     * Returns true if node is a leaf
     **/
    public boolean isLeaf() {
        assert ((left == null) && (right == null)) || ((left != null) && (right != null));
        return (left == null) && (right == null);
    }

    /**
     * Returns node as a String
     **/
    public String toString() {
        return character + " : " +weight ;
    }

    /**
     * Sorts nodes based on weight
     **/
    public int compareTo(HuffNode t) {
        return Integer.compare(weight(), t.weight());
    }
}

