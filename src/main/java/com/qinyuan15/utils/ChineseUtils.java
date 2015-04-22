package com.qinyuan15.utils;

import java.io.UnsupportedEncodingException;

/**
 * Tool class to deal with Chinese
 * Created by qinyuan on 15-2-25.
 */
public class ChineseUtils {
    private ChineseUtils() {
    }

    public static String getPhoneticLetter(String chineseString) {
        return getPhoneticLetter(chineseString, true);
    }

    /**
     * Get first phonetic letter of Chinese string
     *
     * @param chineseString Chinese string
     * @param upperCase     return upper case or lower case
     * @return string Contains first phonetic letters of each character
     */
    public static String getPhoneticLetter(String chineseString, boolean upperCase) {
        StringBuilder sb = new StringBuilder();
        try {
            byte[] b = chineseString.getBytes("GBK");
            for (int i = 0; i < b.length; i++) {
                if ((b[i] & 255) > 128) {
                    int char1 = b[i++] & 255;
                    char1 <<= 8;
                    int chart = char1 + (b[i] & 255);
                    sb.append(getPhoneticLetter((char) chart));
                    continue;
                }
                char c = (char) b[i];
                //if (!Character.isJavaIdentifierPart(c))
                //    c = 'A';
                sb.append(c);
            }
            if (upperCase) {
                return sb.toString().toUpperCase();
            } else {
                return sb.toString().toLowerCase();
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get first phonetic letter of Chinese character
     *
     * @param chineseChar Chinese character
     * @return first phonetic letter of chineseChar
     */
    private static char getPhoneticLetter(char chineseChar) {
        int charGBK = (int) chineseChar;
        char result;
        if (charGBK >= 45217 && charGBK <= 45252)
            result = 'A';
        else if (charGBK >= 45253 && charGBK <= 45760)
            result = 'B';
        else if (charGBK >= 45761 && charGBK <= 46317)
            result = 'C';
        else if (charGBK >= 46318 && charGBK <= 46825)
            result = 'D';
        else if (charGBK >= 46826 && charGBK <= 47009)
            result = 'E';
        else if (charGBK >= 47010 && charGBK <= 47296)
            result = 'F';
        else if (charGBK >= 47297 && charGBK <= 47613)
            result = 'G';
        else if (charGBK >= 47614 && charGBK <= 48118)
            result = 'H';
        else if (charGBK >= 48119 && charGBK <= 49061)
            result = 'J';
        else if (charGBK >= 49062 && charGBK <= 49323)
            result = 'K';
        else if (charGBK >= 49324 && charGBK <= 49895)
            result = 'L';
        else if (charGBK >= 49896 && charGBK <= 50370)
            result = 'M';
        else if (charGBK >= 50371 && charGBK <= 50613)
            result = 'N';
        else if (charGBK >= 50614 && charGBK <= 50621)
            result = 'O';
        else if (charGBK >= 50622 && charGBK <= 50905)
            result = 'P';
        else if (charGBK >= 50906 && charGBK <= 51386)
            result = 'Q';
        else if (charGBK >= 51387 && charGBK <= 51445)
            result = 'R';
        else if (charGBK >= 51446 && charGBK <= 52217)
            result = 'S';
        else if (charGBK >= 52218 && charGBK <= 52697)
            result = 'T';
        else if (charGBK >= 52698 && charGBK <= 52979)
            result = 'W';
        else if (charGBK >= 52980 && charGBK <= 53688)
            result = 'X';
        else if (charGBK >= 53689 && charGBK <= 54480)
            result = 'Y';
        else if (charGBK >= 54481 && charGBK <= 55289)
            result = 'Z';
        else
            throw new RuntimeException("unrecognised Chinese character: " + chineseChar);
        return result;
    }
}
