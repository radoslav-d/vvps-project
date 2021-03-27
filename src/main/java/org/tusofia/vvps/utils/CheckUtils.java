package org.tusofia.vvps.utils;

public class CheckUtils {

    public static void assertNotNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
