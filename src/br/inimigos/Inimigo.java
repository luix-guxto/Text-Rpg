package br.inimigos;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Inimigo {

    public static boolean dano = false, cres = false;
    private static int z = 0, y = 2;
    private static final int numEnimies = 1;
    private static final int escala = 4;
    private static final int locX = 80;
    private static final int locY = 200;

    private static Enemy inimigo;
    private static Font font;

    public static void newInimigo(int lvlPlayer) {


        try {
            InputStream ip = new BufferedInputStream(new FileInputStream("./recursos/fontes/pixel1.ttf"));
            font = Font.createFont(Font.TRUETYPE_FONT, ip);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Reiniciar variaveis
        dano = false;
        z=0;
        y=2;
        cres=false;

        // Iniciar inimigo

        switch (new Random().nextInt(numEnimies)){
            case 0:
                inimigo = new Aranha(lvlPlayer);
                break;
            default:
                inimigo = new Aranha(lvlPlayer);
                break;
        }

    }

    public static void render(Graphics g){
        if(dano){
            z++;
            if(z==3){
                z=0;
                if(cres){
                    y++;
                }else{
                    y--;
                }
                if(y<0){
                    y=0;
                    cres=true;
                } else if (y>2) {
                    y=2;
                    cres=false;
                    dano=false;
                }
            }
        }else{
            z=0;
            y=2;
        }
        g.setColor(Color.BLACK);
        g.fillRect(locX, locY - 30, inimigo.getSprite()[y].getWidth()*escala,20);
        g.setColor(Color.WHITE);
        g.fillRect(locX+5, locY - 25, inimigo.getSprite()[y].getWidth()*escala-10,10);
        g.setColor(Color.GREEN);

        int lif = ((inimigo.getSprite()[y].getWidth() * escala - 10)* inimigo.getLife())/ inimigo.getMaxLife();



        g.fillRect(locX+5, locY - 25, lif,10);
        g.drawImage(inimigo.getSprite()[y], locX,locY,inimigo.getSprite()[y].getWidth()*escala,inimigo.getSprite()[y].getHeight()*escala,null);
    }

    public static void icon(Graphics g, int x, int y, int escala){
        g.drawImage(inimigo.getSprite()[2],x, y,inimigo.getSprite()[2].getWidth()*escala,inimigo.getSprite()[2].getHeight()*escala,null);
    }
    public static String getNome(){
        return inimigo.getNome();
    }
    public static BufferedImage getSprite(int sprite){
        return inimigo.getSprite()[sprite];
    }
    public static int ataque(){
        int dano=0;
        return dano;
    }

    public static void damage(int dan){
        inimigo.setDamage(dan);
        dano = true;
    }

}
