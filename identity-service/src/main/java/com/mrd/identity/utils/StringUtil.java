package com.mrd.identity.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ducnh
 */
public class StringUtil {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    public static boolean isNullOrEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static boolean validateEmail(String emailStr) {
        if (isNullOrEmpty(emailStr)) {
            return false;
        }
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean checkMobilePhoneNumber(String number) {
        if (number == null) {
            return false;
        }

        boolean result = false;

        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(number);

        if (matcher.matches() && (number.length() == 10 || number.length() == 11)) {
            number = number.substring(0, 2);
            if (number.equals("01") || number.equals("02") || number.equals("08") || number.equals("09")) {
                result = true;
            }
        }

        return result;
    }

    public static boolean checkMobilePhoneNumberNew(String number) {
        if (isNullOrEmpty(number)) {
            return false;
        }
        if(number.startsWith("+84")) number = number.replace("+84", "0");
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(number);

        if (matcher.matches() && (number.length() == 10)) {
            if ("09".equals(number.substring(0, 2)) || Arrays.asList(new String[]{"032", "033", "034", "035", "036", "037", "038", "039", "052", "056", "058", "059", "070", "076", "077", "078", "079", "081", "082", "083", "084", "085", "086", "088", "089"}).contains(number.substring(0, 3))) {
                return true;
            }
        }

        return false;
    }

}
