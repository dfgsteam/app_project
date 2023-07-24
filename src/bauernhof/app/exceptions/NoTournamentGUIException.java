package bauernhof.app.exceptions;

public class NoTournamentGUIException extends Exception {
    public NoTournamentGUIException() {
        super("No TournamentsGUI available, change your settings");
    }
}
