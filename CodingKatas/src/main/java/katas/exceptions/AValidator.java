package katas.exceptions;

public class AValidator {

    public void validate(String inputString) throws ValidationException { // throws ValidationException {
        if(inputString==null || inputString.isEmpty())
            throw new ValidationException("input string is empty");
        if(inputString.contains("x"))
            throw new ValidationException("char x not allowed");
    }
}
