package br.states;

import br.Game;
import br.fontes.Fontes;
import br.sprites.ImageLoader;
import br.sprites.SpriteSheet;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public final class Menu implements State{

    Font font1, font2;
    SpriteSheet img;
    BufferedImage capa;
    boolean enter, cresy, cresa, cresc;
    int x, y,z, a, b, c;

    @Override
    public void init() {
        enter = true;
        x=0;
        y=200;
        z=0;
        a=60;
        b=0;
        c=-40;
        cresy=true;
        cresa=true;
        cresc=false;
        ImageLoader im = new ImageLoader();
        try{
            img = new SpriteSheet(im.loadImage("/sprites/capa.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        capa = img.getSprite(0,0,563, 746);
        font1 = Fontes.FANTASY.deriveFont(Font.PLAIN, 130);
        font2 = Fontes.FERRUM.deriveFont(Font.BOLD, 50);
    }

    @Override
    public void update() {
        if(y<=200){
            cresy=true;
        }else if(y==255){
            cresy=false;
        }
        if(a<=60){
            cresa=true;
        }else if(a>=76){
            cresa=false;
        }
        if(c<=-56){
            cresc=true;
        }else if(c>= -46){
            cresc=false;
        }
        z++;
        if(z==2){
            z=0;
            if (cresy) {
                y++;
            } else {
                y--;
            }

            b++;
            if(b==2) {
                if (cresc) {
                    c++;
                } else {
                    c--;
                }
                b=0;
                if (cresa) {
                    a++;
                } else {
                    a--;
                }
            }
        }


        if(!enter) {
            x++;
        }
        if(x==100) {
            StateManager.setState(StateManager.MENU1);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(y,255,255));
        if(!enter){
            g.setColor(new Color(255-x, 255-x, 255-x));
        }
        g.fillRect(0,0, Game.WIDTH, Game.HIGHT);
        g.drawImage(capa, a,c, 564, 746, null);

            g.setColor(new Color(40+(x*2), 40+(x*2), 40+(x*2)));

        g.setFont(font1);
        g.drawString("TEXT-RPG", (Game.WIDTH / 2) - g.getFontMetrics().stringWidth("TEXT-RPG") / 2, 250);
        g.fillRect((Game.WIDTH / 2) - g.getFontMetrics().stringWidth("TEXT-RPG") / 2, 130, g.getFontMetrics().stringWidth("TEXT-RPG"), 7);
        g.fillRect((Game.WIDTH / 2) - g.getFontMetrics().stringWidth("TEXT-RPG") / 2, 255, g.getFontMetrics().stringWidth("TEXT-RPG"), 7);
        g.setFont(font2);
        g.drawString("Pressione ENTER para continuar...", (Game.WIDTH / 2) - g.getFontMetrics().stringWidth("Pressione ENTER para continuar...") / 2, 600);

    }

    @Override
    public void KeyPress(int cod) {

    }

    @Override
    public void KeyReleased(int cod) {
        if(cod== KeyEvent.VK_ENTER){
            enter=false;
        }
    }

    @Override
    public void initFonte() {

    }
}
