package main.utility;

import org.springframework.stereotype.Component;


@Component
public class Validator {

    public static String formalizeEmail(String email) {
        return email.trim().toLowerCase();
    }

    public static boolean emailIsValid(String email) {
        return email.matches("^(.+)@(.+)$");
    }

}
