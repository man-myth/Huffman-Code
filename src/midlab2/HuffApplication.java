package midlab2;
import java.util.*;
import java.util.ArrayList;

public class HuffApplication {
    static Scanner kbd = new Scanner(System.in);

    /**
     * Main Method
     **/
    public static void main(String[] args) {
        int choice;
        String str;
        HuffTree tree = null;

        System.out.println("This is the Huffman Coding Application!");
        do {
            try {
                System.out.println("\nMenu ");
                System.out.println("--------------------------------");
                System.out.println("1. Input Series of Characters");
                System.out.println("2. Convert Text to Huffman Code");
                System.out.println("3. Convert Huffman Code to Text");
                System.out.println("4. Exit");
                System.out.println("--------------------------------");
                choice = enterChoice(1,4);
                switch (choice) {
                    case 1 -> {
                        System.out.println("\nPlease input a Series of Characters.");
                        System.out.print("Enter a phrase/sentence/paragraph: ");
                        str = kbd.nextLine();
                        TextUtility tu = new TextUtility(str);
                        ArrayList<Character> chars = tu.getCharacters();
                        ArrayList<Integer> freq = tu.getFrequency();
                        System.out.println();
                        tree = new HuffTree(chars, freq);
                        tree.printTable();
                        System.out.printf("%n%s%.2f%n", "% Savings = ", tree.computePercentageOfSavings());
                        System.out.printf("%s%s%n", "Huffman Code = ", tree.toHuffCodeEquivalent((str), 'x'));
                        System.out.printf("%s%s%n", "Text = ", tree.toTextEquivalent(tree.toHuffCodeEquivalent((str), 'a')));
                        System.out.println();
                        System.out.println("Huffman Tree (In order):");
                        tree.inorderTraversal();
                        enterKeyToContinue();
                    }
                    case 2 -> {
                        System.out.println("\nPlease input a Text.");
                        System.out.print("Enter a text: ");
                        str = kbd.nextLine();
                        System.out.printf("%s%s%n", "Huffman Code: ", tree.toHuffCodeEquivalent((str), 'x'));
                        enterKeyToContinue();
                    }
                    case 3 -> {
                        System.out.println("\nPlease input a Huffman Code separated by space.");
                        System.out.print("Enter a Huffman Code: ");
                        str = kbd.nextLine();
                        if(!str.contains(" ") && !isCodeValid(tree, str)){
                            System.out.println("Invalid Huffman Code! Make sure the Huffman code is separated by space");
                        } else {
                            System.out.printf("%s%s%n", "Text: ", tree.toTextEquivalent(str));
                        }
                        enterKeyToContinue();
                    }
                    case 4 -> {
                        System.out.println("Closing application...");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid input, ensure to enter a correct choice..");
                }
            } catch (Exception e) {
                System.out.println("There is an error, input cannot be converted. Try it again.");
            }
        } while (true);
    }

    /**
     * Returns true if a single huffman code is valid
     */
    public static boolean isCodeValid(HuffTree t, String s){
        for(HuffNode n : t.leafNodes){
            if (n.getCode().equals(s)){
                return true;
            }
        }
        return false;
    }

    /**
     * Accepts and returns a valid choice
     */
    public static int enterChoice(int min, int max) {
        int temp = 0;
        do {
            try {
                System.out.print("> ");
                temp = Integer.parseInt(kbd.nextLine());
                if (temp < min || temp > max) {
                    System.out.println("Invalid choice. Please ensure that you enter a number from " +
                            min + " to " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, need to enter a number.");
            }
        } while (temp < min || temp > max);
        return (temp);
    }

    /**
     * Waits for user input before continuing
     */
    private static void enterKeyToContinue() {
        System.out.print("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
