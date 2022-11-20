package br.inimigos;

import br.pixelfonte.Fontes;
import br.player.Jogador;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Inimigo {

    public static boolean dano = false, cres = false, fist = false;
    private static int z = 0, y = 2;
    private static final int escala = 2;
    private static final int locX = 80;
    private static final int locY = 200;
    private static boolean isInimigo;

    private static Enemy inimigo;
    private static int xx, yy, llargura, aaltura;

    public static void newInimigo() {
        System.out.println("Inimigo criado");

        fist = true;

        // Reiniciar variaveis
        dano = false;
        z=0;
        y=2;
        cres=false;

        // Iniciar inimigo
        if(new Random().nextInt(10)==0){
            int rn = new Random().nextInt(3);
            if(rn==0) {
                inimigo = new Bau();
            }
            else if (rn == 1) {
                inimigo = new Med();
            }
            else {
                inimigo = new Pp();
            }
            isInimigo = false;
        }else{
            int rn = new Random().nextInt(Jogador.getNivel());
            if(rn>=50){
                System.out.println("inimigo Boss");
                System.exit(0);
            }else if(rn>=40) {
                System.out.println("Inimigo");
                System.exit(0);
            }
            else {
                inimigo = new Aranha(Jogador.getNivel());
                System.out.println("\nInimigo aranha nv: "+inimigo.getNivel());
            }
            isInimigo=true;
        }

    }

    public static void render(Graphics g){
        g.setFont(Fontes.PIXEL.deriveFont(Font.PLAIN, 20));
        if(fist){
            fist = false;
            xx = locX+((-g.getFontMetrics().stringWidth(inimigo.getNome())+(inimigo.getSprite()[y].getWidth()*escala))/2)-4;
            yy = locY-54;
            llargura = g.getFontMetrics().stringWidth(inimigo.getNome())+5;
            aaltura = g.getFontMetrics(Fontes.PIXEL.deriveFont(Font.PLAIN, 20)).getHeight()+5;
        }
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
        g.fillRect(xx,yy, llargura,aaltura);
        g.setColor(Color.WHITE);
        g.drawString(inimigo.getNome(), locX+((-g.getFontMetrics().stringWidth(inimigo.getNome())+(inimigo.getSprite()[y].getWidth()*escala))/2),locY-35);
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
        return inimigo.atack();
    }

    public static void damage(int dan){
        inimigo.setDamage(dan);
        dano = true;
    }

    public static boolean temUso(){
        return inimigo.temPontos();
    }

    public static String getNomeAtaque() {
        return inimigo.getNomeAtaque();
    }

    public static int getVida() {
        return inimigo.getLife();
    }

    public static int getXp() {
        return inimigo.getXp();
    }

    public static boolean isInimigo() {
        return isInimigo;
    }
}
