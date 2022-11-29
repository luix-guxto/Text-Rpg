package br.states;

import java.awt.*;
import java.awt.event.KeyEvent;

public final class RankingOnline implements State {
    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) throws Exception {
        g.drawString("Comming Soon... press esc to return", 100, 100);
    }

    @Override
    public void KeyPress(int cod) {

    }

    @Override
    public void KeyReleased(int cod) {
        if(cod == KeyEvent.VK_ESCAPE){
            StateManager.setState(StateManager.RANKING);
        }
    }

    @Override
    public void initFonte() {

    }
}
