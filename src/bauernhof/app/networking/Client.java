package bauernhof.app.networking;

import bauernhof.app.player.AbstractGamePlayer;
import bauernhof.app.player.PlayerCards;
import bauernhof.app.player.types.Advanced_AI;
import bauernhof.app.player.types.HumanPlayer;
import bauernhof.app.player.types.Random_AI;
import bauernhof.app.player.types.Simple_AI;
import bauernhof.app.system.Game;
import bauernhof.app.system.GameBoard;
import bauernhof.app.ui.game.UiGame;
import bauernhof.preset.*;
import bauernhof.preset.card.Card;
import bauernhof.preset.networking.C2SConnection;
import bauernhof.preset.networking.RemoteException;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends C2SConnection {
    private Settings settings;
    private AbstractGamePlayer player;

    public Client(final Settings settings, final Socket socket, final GameConfigurationParser parser, final String porjectname) throws IOException, RemoteException {
        super(socket, parser, porjectname);
        this.settings = settings;
        handlePackets();

    }

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
        }
        System.out.println(initialDrawPile.size());
        this.player.init(game_configuration, initialDrawPile, playerNames.size(), playerid);
        GameBoard.graphics = new UiGame(game_configuration, game);

    }


    @Override
    protected Move onRequest() throws Exception {
        System.out.println("REQUEST");
        final Move move = player.request();
        player.getGameBoard().executeMove(move);
        GameBoard.graphics.update(false);
        return move;
    }

    @Override
    protected void onUpdate(Move move) throws Exception {
        System.out.println("UPDATE");
        this.player.update(move);
        GameBoard.graphics.update(false);
    }

    @Override
    protected int onGetScore() throws Exception {
        return player.getScore();
    }

    @Override
    protected String onGetName() throws Exception {
        return player.getName();
    }

    @Override
    protected void onVerifyGame(ImmutableList<Integer> immutableList) throws Exception {
        GameBoard.graphics.update(true);
        player.verifyGame(immutableList);
    }
}
