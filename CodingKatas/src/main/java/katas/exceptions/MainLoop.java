package katas.exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainLoop {

    public static void main(String[] args) throws IOException {
        AValidator validator = new AValidator();
        String inputString;
        boolean inputOk = false;
        do {
            inputString = readStringFromUser();
            try {
                validator.validate(inputString);
                inputOk = true;
            } catch (ValidationException validationException) {
                System.out.println(validationException);
                System.out.println("try again!");
            }
        } while (!inputOk);
        System.out.print("Well done");
    }

    private static String readStringFromUser() throws IOException {
        System.out.print(">");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try  {
            return reader.readLine();
        } finally {
            reader.close();
        }

    }
}
