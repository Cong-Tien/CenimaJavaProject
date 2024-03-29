package com.lecongtien.cinema.util;

import com.lecongtien.cinema.model.ConfigKey;

import java.util.regex.Pattern;

public class StringUtil {
    private StringUtil(){}

    public static boolean isMatchedValidation(ConfigKey configKey, String cellStringValue){
        if (configKey.getValidation() != null){
            String regexPatter = configKey.getValidation().trim();
            return Pattern.compile(regexPatter).matcher(cellStringValue).matches();
        }
        return true;
    }

    public static String formatSpace (String excelHeader){
        // replace NBSP with white space and remove suspicious characters, work with replaceAll() but not with replace()
        return excelHeader.split("\\r?\\n")[0].replaceAll("\\u00a0", " ").replaceAll("[\\s\\p{Zs}]+", "").replaceAll("[\uFEFF-\uFFFF]", "").trim();
    }
    /**
     * combine 2 string
     *
     * @param s1 String
     * @return String combined
    */
    public static String combineString(String s1, String s2) {
        return s1.trim().concat(s2.trim());
    }

    /**
     * Capitalizes the first letter of the given string.
     *
     * @param s The string to capitalize.
     * @return The capitalized string.
     */
    public static String capitalize(String s) {
        if (s.length() == 0)
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * @param string is the cell value from excel file
     * @return to normal string without unknown symbols
     */
    public static String removeUnknownSymbol(String string) {
        Pattern pattern = Pattern.compile("[%`\"-]");
        return pattern.matcher(string).replaceAll("");
    }
}
