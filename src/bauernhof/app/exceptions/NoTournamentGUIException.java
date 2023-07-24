package bauernhof.app.exceptions;

/**
 * Exception class for indicating that there is no TournamentGUI available.
 * This exception is thrown when attempting to start a tournament without a GUI.
 * You can change your settings to enable the TournamentGUI.
 * @author Viktor Tevosyan
 */
public class NoTournamentGUIException extends Exception {

    /**
     * Constructs a new NoTournamentGUIException with a default error message.
     */
    
    public NoTournamentGUIException() {
        super("No TournamentsGUI available, change your settings");
    }
}
