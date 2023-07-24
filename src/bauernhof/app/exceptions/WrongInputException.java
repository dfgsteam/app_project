package bauernhof.app.exceptions;

/**
 * Exception class for indicating that the input provided is incorrect or invalid.
 * This exception is thrown when there is a problem with the user input.
 * @author Viktor Tevosyan
 */

public class WrongInputException extends Exception {

    /**
     * Constructs a new WrongInputException with a default error message.
     */

    public WrongInputException() {
        super("Wrong input");
    }
}
