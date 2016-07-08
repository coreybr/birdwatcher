
public class StringUtils {

    // Searches for `searched' within 'word'. Insensitive to whitespace and casing.
    public static boolean included(String word, String searched) {
        return word.toUpperCase().contains(searched.trim().toUpperCase());
    }

}
