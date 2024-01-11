package utility;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
@SuppressWarnings("unused")
public class InputHandling {
    static Scanner scanner = new Scanner(System.in);

    public static Integer integerInput() {
        int number;
        while (true) {
            try {
                number = scanner.nextInt();
                scanner.nextLine();
                return number;
            } catch (InputMismatchException in) {
                scanner.nextLine();
                System.out.println("PLEASE ENTER VALID NUMBER !");
            }
        }
    }

    public static Double doubleInput() {
        double number;
        while (true) {
            try {
                number = scanner.nextInt();
                scanner.nextLine();
                return number;
            } catch (InputMismatchException in) {
                scanner.nextLine();
                System.out.println("PLEASE ENTER VALID NUMBER !");
            }
        }
    }

    public static String stringInput() {
                return scanner.next();
    }

    public static String nameInput() {
        String name = scanner.next();
        while (!Validation.isValidName(name)){
            System.out.println("Please Enter Valid Name(only alphabet)");
            name = scanner.next();
        }
    return name;
    }

    public static boolean booleanInput() {
        int bool = scanner.nextInt();
            scanner.nextLine();
            while (bool != 1 && bool != 2) {
                System.out.println("Please Choose Correctly:");
                bool = InputHandling.integerInput();
            }
            return bool ==1;
        }


        public static int switchInput(int first , int last){
            int num = InputHandling.integerInput();
            while (num>last ||num<first) {
                System.out.println("Please choose from above!");
                num = InputHandling.integerInput();
            }
            return num;
        }
    public static String dateInput() {
        System.out.println("Year:");
        int year = InputHandling.integerInput();
        while (year > 2023 || year < 1900) {
            System.out.println("Please Enter A Year Between 1900 and 2023!");
            System.out.println("Year:");
            year = InputHandling.integerInput();
        }
        System.out.println("Month:");
        int month = InputHandling.integerInput();
        while (month > 12 || month < 1) {
            System.out.println("Please Enter A Month Between 1 and 12!");
            System.out.println("Month:");
            month = InputHandling.integerInput();
        }
        String strMonth = String.valueOf(month);
        if (month<10){
            strMonth = "0"+month;
        }
        System.out.println("Day:");
        int day = InputHandling.integerInput();
        if (month == 2) {
            while (day > 28 || day < 1) {
                System.out.println("Please Enter A Day Between 1 and 28!");
                System.out.println("Day:");
                day = InputHandling.integerInput();
            }
        }else if (month == 11 || month==4 || month==6 || month==9) {
            while (day > 30 || day < 1) {
                System.out.println("Please Enter A Day Between 1 and 30!");
                System.out.println("Day:");
                day = InputHandling.integerInput();
            }
        }else {
            while (day > 31 || day < 1) {
                System.out.println("Please Enter A Day Between 1 and 31!");
                System.out.println("Day:");
                day = InputHandling.integerInput();
            }
        }String strDay = String.valueOf(day);
        if (day<10){
            strDay = "0"+day;
        }

        return ""+year+"-"+strMonth+"-"+strDay;
    }


    public static LocalDate stringToDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           return LocalDate.parse(string, formatter);
    }

    public static String dateToString(LocalDate localDate){
        return localDate.toString();
    }

    public static String timeToString(LocalTime localTime){
        return localTime.toString();
    }


    }
