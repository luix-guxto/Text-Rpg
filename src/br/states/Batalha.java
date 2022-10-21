package br.states;

import br.Game;
import br.background.Fundo;
import br.batalhas.Batalhas;
import br.player.Jogador;

import java.awt.*;

public class Batalha implements State{
    @Override
    public void init() {
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Fundo.fundo,0,0, Game.WIDTH, 1173,null);
        Batalhas.render(g);
    }

    @Override
    public void KeyPress(int cod) {

    }

    @Override
    public void KeyReleased(int cod) {
        //Fundo.newFundo();
        StateManager.setState(StateManager.PREBATALHA);
    }
}
