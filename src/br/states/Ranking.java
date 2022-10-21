package br.states;

import br.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Ranking implements State{
    @Override
    public void init() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        g.drawString("Ainda to fazendo, aperta ESC pra voltar", 20, Game.HIGHT/2);
    }

    @Override
    public void KeyPress(int cod) {

    }

    @Override
    public void KeyReleased(int cod) {
        if(cod == KeyEvent.VK_ESCAPE){
            StateManager.setState(StateManager.MENU1);
        }
    }
}
