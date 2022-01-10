package midlab2;

import java.util.ArrayList;
/**
 * TextUtility class
 *
 * This class returns characters used in a user inputted String as well as each
 * character's frequency in the String
 **/
public class TextUtility {
    private String text; // User inputted String
    private ArrayList<Character> characters = new ArrayList<>(); // List of all characters in the String
    private ArrayList<Integer> frequency = new ArrayList<>(); // List of all frequencies of respective characters in characters ArrayList in the String

    /** Constructors **/
    public TextUtility(String text) {
        this.text = text;
    }

    /**
     * Returns all characters used in the text String
     **/
    public ArrayList<Character> getCharacters() {
        String temp = text;
        while (!temp.isBlank()) {
            char token = temp.charAt(0);
            characters.add(token);
            temp = removeChar(temp, token);
        }
        return characters;
    }

    /**
     * Returns frequencies of characters respective to the character array list
     * ex.
     * [C, D]   (Array list returned by "getCharacters" method)
     * [32, 42] (Array list returned by "getFrequency" method)
     *
     * C's frequency is 32
     * D's frequency is 42
     **/
    public ArrayList<Integer> getFrequency() {
        String temp = text;
        while (!temp.isBlank()) {
            char token = temp.charAt(0);
            frequency.add(countChar(temp, token));
            temp = removeChar(temp, token);
        }
        return frequency;
    }

    /**
     * Returns the frequency a specified character in a String
     **/
    private int countChar(String str, char cChar) {
        int count = 0;
        for(int i=0; i < str.length(); i++) {
            if(str.charAt(i) == cChar)
                count++;
        }
        return count;
    }

    /**
     * Removes every occurrence of a specified character in a String
     **/
    private String removeChar(String str, char rChar) {
        for(int i=0; i < str.length(); i++) {
            if(str.charAt(i) == rChar)
                str = str.replaceAll(String.valueOf(rChar), "");
        }
        return str;
    }
}
