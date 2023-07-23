package bauernhof.app.ui.game.listener;

import bauernhof.app.system.Tournament;
import bauernhof.app.ui.game.UiGame;
import sag.elements.GElement;
import sag.elements.GText;
import sag.events.MouseButtonEvent;
import sag.events.MouseEventListener;
import sag.events.MouseMotionEvent;
import sag.events.MouseWheelEvent;

public class ListenerNextTournament implements MouseEventListener {
    private GText homeHeadline;
    private UiGame uiGame;
    private Tournament tournament;

    public ListenerNextTournament(Tournament tournament, UiGame uiGame, GText homeHeadline) {
        this.uiGame = uiGame;
        this.tournament = tournament;
        this.homeHeadline = homeHeadline;
    }

    @Override
    public void mouseClicked(MouseButtonEvent var1, GElement var2) {
        try {
            tournament.nextTournamentRound();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Handles the mousePressed event for the game button.
     *
     * @param var1 The MouseButtonEvent object representing the mouse press event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mousePressed(MouseButtonEvent var1, GElement var2) {}

    /**
     * Handles the mouseReleased event for the game button.
     *
     * @param var1 The MouseButtonEvent object representing the mouse release event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseReleased(MouseButtonEvent var1, GElement var2) {}

    /**
     * Handles the mouseEntered event for the game button.
     * Increases the scale of the home button and its associated headline.
     *
     * @param var1 The MouseMotionEvent object representing the mouse enter event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseEntered(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1.1f);
        this.homeHeadline.setScale(1.1f);
    }

    /**
     * Handles the mouseExited event for the game button.
     * Resets the scale of the home button and its associated headline.
     *
     * @param var1 The MouseMotionEvent object representing the mouse exit event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseExited(MouseMotionEvent var1, GElement var2) {
        var2.setScale(1f);
        this.homeHeadline.setScale(1f);
    }

    /**
     * Handles the mouseMoved event for the game button.
     *
     * @param var1 The MouseMotionEvent object representing the mouse move event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseMoved(MouseMotionEvent var1, GElement var2) {}

    /**
     * Handles the mouseWheelMoved event for the game button.
     *
     * @param var1 The MouseWheelEvent object representing the mouse wheel move event.
     * @param var2 The GElement object that triggered the event.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent var1, GElement var2) {}
}