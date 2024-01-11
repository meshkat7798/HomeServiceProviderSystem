package menu;

import utility.InputHandling;

import java.text.ParseException;

@SuppressWarnings("unused")
public class MainMenu {
    protected String userName;
    protected String passWord;

    public void start() throws ParseException {
        String text = """
                *** Welcome ***
                1. SignUp
                2. SignIn
                3. SignIn As Admin
                4. EXIT
                                   
                """;
        System.out.println(text);
        System.out.println("Please choose from above:");
        int input = InputHandling.switchInput(1, 3);
        switch (input) {
            case 1 -> {
                signUp();
            }
            case 2 -> {
                login();
            }
            case 3 -> {
                System.out.println("See You soon :)");
                System.exit(0);
            }
        }


    }


    void login() {

        System.out.println("PLEASE ENTER YOUR USERNAME : ");
        this.userName = InputHandling.stringInput();
        System.out.println("PLEASE ENTER YOUR PASSWORD : ");
        this.passWord = InputHandling.stringInput();
    }

    void signUp(){

    }
}
