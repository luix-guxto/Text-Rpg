package br.states;

import br.Game;
import br.background.Fundo;
import br.inimigos.Inimigo;
import br.pixelfonte.Fontes;
import br.saves.LoadGame;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PreBatalha implements State{

    Font fonte, fonte1;
    @Override
    public void init() {
        Fundo.newFundo();
        Inimigo.newInimigo(LoadGame.getNv(Game.numSave));
        fonte = Fontes.PIXEL.deriveFont(Font.PLAIN, 25);
        fonte1 = Fontes.PIXEL.deriveFont(Font.BOLD, 28);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,Game.WIDTH,Game.HIGHT);
        g.setColor(Color.BLACK);
        g.setFont(fonte);
        int y = 50;
        g.drawString("Voce andou a frente,", Game.WIDTH/2-g.getFontMetrics().stringWidth("Voce andou a frente,")/2,y);
        y+=g.getFontMetrics(fonte).getHeight();
        g.drawString("apareceu um novo inimigo", Game.WIDTH/2-g.getFontMetrics().stringWidth("apareceu um novo inimigo")/2,y);

        g.setColor(Color.RED);
        g.setFont(fonte1);

        y+=20;
        y+=g.getFontMetrics(fonte1).getHeight();

        g.drawString(Inimigo.getNome(), Game.WIDTH/2-g.getFontMetrics().stringWidth(Inimigo.getNome())/2,y);

        y+=g.getFontMetrics(fonte1).getHeight();

        Inimigo.icon(g,Game.WIDTH/2-Inimigo.getSprite(2).getWidth()*8/2,y, 8);
        y=600;
        g.setColor(Color.BLACK);
        g.drawString("Pressione ENTER para batalhar...", Game.WIDTH/2-g.getFontMetrics().stringWidth("Pressione ENTER para batalhar...")/2,y);
    }

    @Override
    public void KeyPress(int cod) {

    }

    @Override
    public void KeyReleased(int cod) {

        if(cod == KeyEvent.VK_ENTER){
            StateManager.setState(StateManager.BATALHA);
        }
    }
}
