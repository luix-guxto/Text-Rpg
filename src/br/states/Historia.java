package br.states;

import br.Game;
import br.inimigos.Inimigo;
import br.pixelfonte.Fontes;
import br.saves.LoadGame;
import br.sprites.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

@SuppressWarnings("unused")
public class Historia implements State{

    Icon test;
    Font font;
    String[] text = {"HISTORIA","","Um antigo dragao despertou.","E seu dever, como heroi,","eh deter esse terrivel dragao,","ta ferrado kkk."};
    @Override
    public void init() {
        Inimigo.newInimigo(LoadGame.getNv(Game.numSave));
        try {
            font = Fontes.PIXEL.deriveFont(Font.PLAIN, 25);


            ImageLoader loader = new ImageLoader();
            test = new ImageIcon("./recursos/sprites/historia.gif");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g){
        g.setColor(Color.darkGray);
        g.fillRect(0,0, Game.WIDTH,Game.HIGHT);
        g.setColor(Color.WHITE);
        g.fillRect(20,20,Game.WIDTH-40, Game.HIGHT-40);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(20,400,700-40,700-420);
        g.setColor(Color.BLACK);
        g.fillRect((Game.WIDTH/2- test.getIconWidth()/2)-20,((400-20)/2 - test.getIconHeight()/2+20)-20, test.getIconWidth()+40, test.getIconHeight()+40);

        g.fillRect(20,400,700-40,20);
        test.paintIcon(null, g, Game.WIDTH/2- test.getIconWidth()/2,(400-20)/2 - test.getIconHeight()/2+20);

        for(int i = 0;i<text.length;i++) {
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString(text[i], 40, 450 + (i * g.getFontMetrics(font).getHeight()));
        }
        g.setColor(Color.BLACK);
        g.setFont(Fontes.PIXEL.deriveFont(Font.BOLD, 26));

        g.drawString("Pressione ENTER para prosseguir...", 40,650);
    }

    @Override
    public void KeyPress(int cod) {

    }

    @Override
    public void KeyReleased(int cod) {
        if(cod == KeyEvent.VK_ENTER){
            StateManager.setState(StateManager.PREBATALHA);
        }
    }

    @Override
    public void initFonte() {

    }
}
