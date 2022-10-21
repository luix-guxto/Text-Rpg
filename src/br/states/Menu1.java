package br.states;

import br.Game;
import br.input.KeyManager;
import br.pixelfonte.Fontes;
import br.sprites.ImageLoader;
import br.sprites.SpriteSheet;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Menu1 implements State{
    boolean iniciando,alt = true;
    int cor;
    Font font1, font2;
    SpriteSheet img;
    BufferedImage capa;
    boolean cresy, cresa, cresc;
    int x, y,z, a, b, c;



    BufferedImage fundo;

    private final String[] options = {"START", "RANKING", "CREDITOS", "EXIT"};
    private int choice = 0;

    @Override
    public void init() {
        iniciando = true;
        x=0;
        y=200;
        z=0;
        a=60;
        b=0;
        c=-40;
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
        }else if(c>=-46){
            cresc=false;
        }
        ImageLoader im = new ImageLoader();
        try{
            img = new SpriteSheet(im.loadImage("/sprites/menu1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        capa = img.getSprite(0,0,392, 536);
        font1 = Fontes.FANTASY.deriveFont(Font.PLAIN, 130);
        font2 = Fontes.FERRUM.deriveFont(Font.PLAIN, 40);
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
            if(alt){
                cor+=15;
            }else{
                cor-=15;
            }
            if(iniciando){
                x+=8;
                if(x>=100){
                    x=100;
                    iniciando=false;
                }
            }
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
        if(cor<=0){
            alt = true;
            cor = 0;
        }else if(cor>=254){
            cor = 254;
            alt=false;
        }

    }

    @Override
    public void render(Graphics g) {
        if(iniciando) {
            g.setColor(new Color(55 + (x * 2), 55 + (x * 2), 55 + (x * 2)));
            g.fillRect(0, 0, Game.WIDTH, Game.HIGHT);
        }else {
            g.setColor(new Color(y, 255, 255));
            g.fillRect(0, 0, Game.WIDTH, Game.HIGHT);
            g.drawImage(capa, 300, 0, 512, 700, null);

            g.setColor(new Color(0,0,0));

            g.setFont(font1);
            g.drawString("TEXT-RPG", (Game.WIDTH / 2) - g.getFontMetrics().stringWidth("TEXT-RPG") / 2, 250);
            g.fillRect((Game.WIDTH / 2) - g.getFontMetrics().stringWidth("TEXT-RPG") / 2, 130, g.getFontMetrics().stringWidth("TEXT-RPG"), 7);
            g.fillRect((Game.WIDTH / 2) - g.getFontMetrics().stringWidth("TEXT-RPG") / 2, 255, g.getFontMetrics().stringWidth("TEXT-RPG"), 7);
            g.setFont(font2);

            for(int i = 0; i < options.length; i++) {
                g.setColor(Color.BLACK);
                if(i==choice) {
                    g.setColor(new Color(cor, 0, 0));
                }
            g.drawString(options[i], Game.WIDTH / 2 - g.getFontMetrics().stringWidth(options[i]) / 2, Game.HIGHT / 2 + g.getFontMetrics(font2).getHeight() * i);
            }
        }
    }

    @Override
    public void KeyPress(int cod) {

    }

    @Override
    public void KeyReleased(int cod) {
        if(cod == KeyEvent.VK_W||cod == KeyEvent.VK_UP){
            choice--;
            if(choice<0){
                choice=options.length-1;
            }
        }
        if(cod == KeyEvent.VK_S||cod==KeyEvent.VK_DOWN){
            choice++;
            if(choice> options.length-1){
                choice=0;
            }
        }
        if(cod == KeyEvent.VK_ENTER){
            switch (choice){
                case 0:
                    StateManager.setState(StateManager.SELECT_SAVE);
                    break;
                case 1:
                    StateManager.setState(StateManager.RANKING);
                    break;
                case 2:
                    StateManager.setState(StateManager.CREDITS);
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }
    }
}
