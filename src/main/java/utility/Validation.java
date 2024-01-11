package utility;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.util.regex.Pattern;
@SuppressWarnings("unused")
public class Validation {
    public static boolean isValidPassword(String password) {
        Pattern pattern =
                Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        return password.matches(pattern.pattern());
    }
    public static boolean isValidEmail (String email) {
        Pattern pattern =
                Pattern.compile("^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$");
        return email.matches(pattern.pattern());

    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern =
                Pattern.compile("^\\d{11}$");
        return phoneNumber.matches(pattern.pattern());
    }

    public static boolean isValidName(String name) {
        Pattern pattern =
                Pattern.compile("^[a-zA-Z ]{2,30}$");
        return name.matches(pattern.pattern());
    }

    public static boolean isValid10DigitNumber(String nationalityCode) {
        Pattern pattern =
                Pattern.compile("^\\d{10}$");
        return nationalityCode.matches(pattern.pattern());
    }

    public static String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
        String pwd = RandomStringUtils.random(8, characters);
        while (!isValidPassword(pwd)) {
            pwd = RandomStringUtils.random(8, characters);
        }
        return pwd;
    }

    public static int validEntryYear() {
        int year = InputHandling.integerInput();
        while (year > 2024||year<2000) {
            System.out.println("Please Enter A Proper Entry Year Before 2025!");
            System.out.println("EntryYear:");
            year = InputHandling.integerInput();
        }
        return year;
    }

    public static int getPresentYear() {
        LocalDate localDate = LocalDate.now();
        return localDate.getYear();
    }

    public static int getPresentMonth() {
        LocalDate localDate = LocalDate.now();
        return localDate.getMonthValue();
    }

    public static boolean isValidCardNumber(String cardNumber) {
        Pattern pattern =
                Pattern.compile("^[0-9]{16}$");
        return cardNumber.matches(pattern.pattern());
    }

    public static boolean isValidCvv2(String cvv2) {
        Pattern pattern =
                Pattern.compile("^[0-9]{3,4}$");
        return cvv2.matches(pattern.pattern());
    }

}
