package bauernhof.app.networking;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.PlayerCards;
import bauernhof.app.player.types.Advanced_AI;
import bauernhof.app.player.types.HumanPlayer;
import bauernhof.app.player.types.Random_AI;
import bauernhof.app.player.types.Simple_AI;
import bauernhof.app.system.GameBoard;
import bauernhof.app.ui.game.UiGame;
import bauernhof.preset.*;
import bauernhof.preset.card.Card;
import bauernhof.preset.networking.C2SConnection;
import bauernhof.preset.networking.RemoteException;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * The Client class represents a client-side connection to a remote game server
 * in a networked game of "Bauernhof".
 * This class extends the C2SConnection class, which handles the client-side
 * communication with the server.
 * The Client class is responsible for initializing and managing the player's game state
 * and handling the interactions with the server.
 * @author Florian Will
 */

public class Client extends C2SConnection {
    private Settings settings;
    private AbstractGamePlayer player;

    /**
     * Constructs a new Client object with the given settings, socket, parser, and project name.
     *
     * @param settings      The game settings.
     * @param socket        The socket for the client-server connection.
     * @param parser        The game configuration parser.
     * @param projectName   The name of the project or game.
     * @throws IOException       If an I/O error occurs while establishing the connection.
     * @throws RemoteException   If a remote exception occurs during the communication with the server.
     */

    public Client(final Settings settings, final Socket socket, final GameConfigurationParser parser, final String porjectname) throws IOException, RemoteException {
        super(socket, parser, porjectname);
        this.settings = settings;
        handlePackets();
    }

    /**
     * Handles the game initialization when receiving the game configuration from the server.
     *
     * @param gameConfiguration The game configuration received from the server.
     * @param initialDrawPile   The initial draw pile of cards for the game.
     * @param playerNames       The names of all players in the game.
     * @param playerid          The ID of the current player.
     * @throws Exception        If an error occurs during game initialization.
     */

    @Override
    protected void onInit(GameConfiguration game_configuration, ImmutableList<Card> initialDrawPile, ImmutableList<String> playerNames, int playerid) throws Exception {
        final PlayerCards[] playercards = new PlayerCards[playerNames.size()];
        for (int i = 0; i < playerNames.size(); i++)
            playercards[i] = new PlayerCards();
        settings.playerNames = playerNames;
        final GameBoard game = new GameBoard(playercards, settings, 1, 0, new ArrayList<>(), initialDrawPile, game_configuration);
        switch (settings.playerTypes.get(0)) {
            case HUMAN:
                this.player = new HumanPlayer(settings, playercards[playerid -1], game);
                break;
            case ADVANCED_AI:
                this.player = new Advanced_AI(settings, playercards[playerid - 1], game);
                break;
            case SIMPLE_AI:
                this.player = new Simple_AI(settings, playercards[playerid - 1], game);
                break;
            case RANDOM_AI:
                this.player = new Random_AI(settings, playercards[playerid - 1], game);
                break;
            default:
                break;
        }
        this.player.init(game_configuration, initialDrawPile, playerNames.size(), playerid);
        GameBoard.graphics = new UiGame(game);
    }

    /**
     * Handles the player's move request from the server.
     *
     * @return The move selected by the player.
     * @throws Exception If an error occurs during move selection.
     */

    @Override
    protected Move onRequest() throws Exception {
        final Move move = player.request();
        player.getGameBoard().executeMove(move);
        GameBoard.graphics.update(false);
        return move;
    }

    /**
     * Handles the update received from the server after an opponent's move.
     *
     * @param move The opponent's move.
     * @throws Exception If an error occurs during the update process.
     */

    @Override
    protected void onUpdate(Move move) throws Exception {
        this.player.update(move);
        GameBoard.graphics.update(false);
    }

    /**
     * Retrieves the player's current score from the game.
     *
     * @return The player's current score.
     * @throws Exception If an error occurs while fetching the score.
     */

    @Override
    protected int onGetScore() throws Exception {
        return player.getScore();
    }

    /**
     * Retrieves the player's name from the game.
     *
     * @return The player's name.
     * @throws Exception If an error occurs while fetching the name.
     */

    @Override
    protected String onGetName() throws Exception {
        return player.getName();
    }

    /**
     * Verifies the game state with the scores received from the server.
     *
     * @param immutableList The scores of all players in the game.
     * @throws Exception If an error occurs during game verification.
     */
    
    @Override
    protected void onVerifyGame(ImmutableList<Integer> immutableList) throws Exception {
        GameBoard.graphics.update(true);
        player.verifyGame(immutableList);
    }
}
