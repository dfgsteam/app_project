package bauernhof.preset;

/**
 * An instance of PlayerGUIAccess is allowed to be given to the {@link PlayerType#HUMAN HUMAN} {@link Player} via its constructor.
 *
 * <p>
 * PlayerGUIAccess allows the human player to make their move.
 * </p>
 * <p>
 * Your GUI should implement this interface.
 * </p>
 */
public interface PlayerGUIAccess {
	/**
	 * Instructs the GUI to ask for the current human player to make their move.
	 *
	 * @return The move the human made.
	 */
	public Move requestMoveFromCurrentHumanPlayer();
}
