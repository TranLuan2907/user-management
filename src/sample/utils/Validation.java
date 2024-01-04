/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author 
 */
public class Validation {

    private static final Scanner in = new Scanner(System.in);

    public static int getAnInteger(int lowerBound, int upperBound, String inputMessage, String errorMessage) {
        int temp, n;
        if (lowerBound > upperBound) {
            temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }

        while (true) {
            try {
                System.out.print(inputMessage.toUpperCase() + ": ");
                n = Integer.parseInt(in.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.err.println(errorMessage.toUpperCase() + "!");
            }
        }

    }

    public static String getString(String inputMessage, String errorMessage) {
        String n;
        while (true) {
            System.out.print(inputMessage.toUpperCase() + ": ");
            n = in.nextLine().trim().toUpperCase();
            if (n.isEmpty()) {
                System.err.println(errorMessage.toUpperCase() + "!");
            } else {
                return n;
            }
        }
    }

    public static String getName(String inputMessage, String errorMessage) {
        String n;
        while (true) {
            System.out.print(inputMessage.toUpperCase() + ": ");
            n = in.nextLine().trim();
            if (n.isEmpty() || n.length() < 5 || n.contains(" ")) {
                System.err.println(errorMessage.toUpperCase() + "!");
            } else {
                return n;
            }
        }
    }

    public static String getPassWord(String inputMessage, String errorMessage) {
        String n;
        while (true) {
            System.out.print(inputMessage.toUpperCase() + ": ");
            n = in.nextLine().trim();
            if (n.isEmpty() || n.length() < 6 || n.contains(" ")) {
                System.err.println(errorMessage.toUpperCase() + "!");
            } else {
                return n;
            }
        }
    }

    public static String readUpdatedString(String prompt) {
        String input;
        System.out.print(prompt.toUpperCase() + ": ");
        input = in.nextLine().trim().toUpperCase();
        return input;
    }

    public static double getADouble(String inputMessage, String errorMessage, double upperBound, double lowerBound) {
        double n, temp;
        if (lowerBound > upperBound) {
            temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }

        while (true) {
            try {
                System.out.print(inputMessage.toUpperCase() + ": ");
                n = Double.parseDouble(in.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.err.println(errorMessage.toUpperCase() + "!");
            }
        }
    }

    public static double getADouble(String inputMessage, String errorMessage) {
        double n;
        while (true) {
            try {
                System.out.print(inputMessage.toUpperCase() + ": ");
                n = Double.parseDouble(in.nextLine());
                return n;
            } catch (NumberFormatException e) {
                System.err.println(errorMessage.toUpperCase() + "!");
            }
        }
    }

    public static boolean parseBoolean(String inputMessage, String errorMessage) {
        while (true) {
            System.out.print(inputMessage.toUpperCase() + "? ");
            String input = in.nextLine().trim().toUpperCase();
            if (input.equals("Y") || input.equals("N")) {
                return (input.equals("Y"));
            } else {
                System.err.println(errorMessage.toUpperCase() + "!");
            }
        }
    }

    public static String normalizeDateStr(String dateStr) {
        String result = dateStr.replaceAll("[\\s]+", "");
        result = result.replaceAll("[./-]+", "-");
        return result;
    }

    public static Date parseDate(String inputStr, String dateFormat) {
        inputStr = normalizeDateStr(inputStr);
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        try {
            return formatter.parse(inputStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String toString(Date date, String dateFormat) {
        if (date == null) {
            return "";
        }
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }

    public static int getPart(Date d, int calendarPart) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(d);
        return cal.get(calendarPart);
    }

    public static String readStr(String prompt, String errorMessage, String pattern) {
        String input;
        while (true) {
            System.out.print(prompt.toUpperCase() + ": ");
            input = in.nextLine().trim().toUpperCase();
            if (input.matches(pattern)) {
                return input;
            } else {
                System.err.println(errorMessage.toUpperCase() + "!");
            }
        }
    }
    
     public static String readEmail(String prompt, String errorMessage, String pattern) {
        String input;
        while (true) {
            System.out.print(prompt.toUpperCase() + ": ");
            input = in.nextLine().trim().toLowerCase();
            if (input.matches(pattern)) {
                return input;
            } else {
                System.err.println(errorMessage.toUpperCase() + "!");
            }
        }
    }

    public static Date readDate(String prompt, String errorMessage, String dateFormat) {
        String input;
        Date d;
        while (true) {
            System.out.print(prompt.toUpperCase() + ": ");
            input = in.nextLine().trim().toUpperCase();
            d = parseDate(input, dateFormat);
            if (d != null) {
                return d;
            } else {
                System.err.println(errorMessage.toUpperCase() + "!");
            }
        }
    }

    public static Date readDateAfter(String prompt, String errorMessage, String dateFormat, Date markerDate) {
        String input;
        Date d;
        boolean isValid;
        while (true) {
            System.out.print(prompt.toUpperCase() + ": ");
            input = in.nextLine().trim().toUpperCase();
            d = parseDate(input, dateFormat);
            isValid = (d != null && d.after(markerDate));
            if (isValid) {
                return d;
            } else {
                System.err.println(errorMessage.toUpperCase() + "!");
            }
        }
    }

    public static Date readDateBetween(String prompt, String errorMessage, String format, Date min, Date max) {
        String input;
        Date date;
        boolean isValid;
        while (true) {
            System.out.print(prompt.toUpperCase() + ": ");
            input = in.nextLine().trim().toUpperCase();
            date = parseDate(input, format);
            isValid = (date != null && date.after(min) && date.before(max));
            if (isValid) {
                return date;
            } else {
                System.err.println(errorMessage.toUpperCase() + "!");
            }
        }
    }

    public static Date readDateBefore(String prompt, String errorMessage, String dateFormat, Date markerDate) {
        String input;
        Date d;
        boolean isValid;
        while (true) {
            System.out.print(prompt.toUpperCase() + ": ");
            input = in.nextLine().trim().toUpperCase();
            d = parseDate(input, dateFormat);
            isValid = (d != null && d.before(markerDate));
            if (isValid) {
                return d;
            } else {
                System.err.println(errorMessage.toUpperCase() + "!");
            }
        }
    }

    public static String generateCode(String prefix, int length, int curNumber) {
        String formatStr = "%0" + length + "d";
        return prefix + String.format(formatStr, curNumber);
    }

    public static int getNumberInCode(String code, String prefix) {
        return Integer.parseInt(code.substring(prefix.length()));
    }

    public static int getNumberInCode(String code, int prefixLength) {
        return Integer.parseInt(code.substring(prefixLength));
    }
}
