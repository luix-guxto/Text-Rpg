package br.states;

import br.Game;
import br.background.Fundo;
import br.inimigos.Inimigo;
import br.pixelfonte.Fontes;
import br.player.Jogador;

import java.awt.*;
import java.awt.event.KeyEvent;

@SuppressWarnings("unused")
public class PreBatalha implements State{

    Font fonte, fonte1;

    @Override
    public void initFonte(){
        fonte = Fontes.PIXEL.deriveFont(Font.PLAIN, 25);
        fonte1 = Fontes.PIXEL.deriveFont(Font.BOLD, 28);
        Fundo.newFundo();
    }

    @Override
    public void init() {
        fonte = Fontes.PIXEL.deriveFont(Font.PLAIN, 25);
        fonte1 = Fontes.PIXEL.deriveFont(Font.BOLD, 28);
        Fundo.newFundo();
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(220, 255, 255));
        g.fillRect(0,0,Game.WIDTH,Game.HIGHT);
        g.setColor(Color.BLACK);
        g.setFont(fonte);
        int y = 50;
        g.drawString("Voce subiu um andar,", Game.WIDTH/2-g.getFontMetrics().stringWidth("Voce subiu um andar,")/2,y);
        y+=g.getFontMetrics(fonte).getHeight();
        if(Inimigo.getNome()!=null) {
            if (Inimigo.isInimigo()) {
                g.drawString("apareceu um novo inimigo", Game.WIDTH / 2 - g.getFontMetrics().stringWidth("apareceu um novo inimigo") / 2, y);
            } else {
                g.drawString("que Sorte um Bau", Game.WIDTH / 2 - g.getFontMetrics().stringWidth("que Sorte um Bau") / 2, y);
            }
        }

        g.setColor(Color.RED);
        g.setFont(fonte1);

        y+=20;
        y+=g.getFontMetrics(fonte1).getHeight();

        g.drawString(Inimigo.getNome(), Game.WIDTH/2-g.getFontMetrics().stringWidth(Inimigo.getNome())/2,y);

        y+=g.getFontMetrics(fonte1).getHeight();

        if(Inimigo.isInimigo()){
            Inimigo.icon(g,Game.WIDTH/2-Inimigo.getSprite(2).getWidth()*8/2,y, 8);
        }else{
            g.drawImage(Inimigo.getSprite(2), Game.WIDTH/2-Inimigo.getSprite(2).getWidth()/4,y, Inimigo.getSprite(2).getWidth()/2,Inimigo.getSprite(2).getHeight()/2,null);
        }
        y=600;
        g.setColor(Color.BLACK);
        if(Inimigo.isInimigo()){
            g.drawString("Pressione ENTER para batalhar...", Game.WIDTH/2-g.getFontMetrics().stringWidth("Pressione ENTER para batalhar...")/2,y);
        }else{
            g.drawString("Pressione ENTER para abrir o bau...", Game.WIDTH/2-g.getFontMetrics().stringWidth("Pressione ENTER para abrir o bau...")/2,y);
        }
    }

    @Override
    public void KeyPress(int cod) {

    }

    @Override
    public void KeyReleased(int cod) {

        if(cod == KeyEvent.VK_ENTER){
            if(Inimigo.isInimigo()){
                StateManager.setState(StateManager.BATALHA);
            }else{
                StateManager.setState(StateManager.POSBATALHA);
            }
        }
    }
}
