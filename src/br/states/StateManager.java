package br.states;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StateManager implements KeyListener {
    // total de estados existentes
    private static final int numberStates = 13;

    // array dos stados de jogo
    public static State[] states = new State[numberStates];

    // estado atual do jogo
    public static int currentState = 0;

    // numero do estado do jogo
    public  static final int
            MENU = 0,
            MENU1=1,
            RANKING=2,
            CREDITS=3,
            SELECT_SAVE=4,
            CREATE_PLAYER=5,
            HISTORIA=6,
            PREBATALHA=7,
            BATALHA=8,
            PAUSE=9,
            POSBATALHA=10,
            GAME_OVER=11,
            RANKING_ONLINE=12;

    public StateManager(){
        states[MENU]=new Menu();
        states[MENU1]=new Menu1();
        states[RANKING]=new Ranking();
        states[CREDITS]=new Credits();
        states[SELECT_SAVE]=new SelectSave();
        states[CREATE_PLAYER]=new CreatePlayer();
        states[HISTORIA]=new Historia();
        states[PREBATALHA]=new PreBatalha();
        states[BATALHA]=new Batalha();
        states[PAUSE]=new Pause();
        states[POSBATALHA]=new PosBatalha();
        states[GAME_OVER]=new GameOver();
        states[RANKING_ONLINE]=new RankingOnline();
        states[PREBATALHA].initFonte();
    }

    public static void setState(int state) {
        currentState = state;
        states[currentState].init();
    }

    public static State getStade() {
        return states[currentState];
    }

    public void update()            { states[currentState].update();  }
    public void render(Graphics g) throws Exception { states[currentState].render(g); }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)  { states[currentState].KeyPress(e.getKeyCode()); }

    @Override
    public void keyReleased(KeyEvent e) { states[currentState].KeyReleased(e.getKeyCode()); }
}
