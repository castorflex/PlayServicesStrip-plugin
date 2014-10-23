package com.castorflex.playservicesstrip.plugin

/**
 * Created by castorflex on 10/23/14.
 */
class Utils {

    private Utils() {}

    public static boolean isEmpty(String[] array) {
        array == null || array.length == 0
    }

    public static String toCamelCase(String string) {
        String result = ""
        string.findAll("[^\\W]+") { String word ->
            result += word.capitalize()
        }
        return result
    }
}
