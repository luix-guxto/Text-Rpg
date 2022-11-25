package br.inimigos;

import br.fontes.Fontes;
import br.inimigos.bau.Bau;
import br.inimigos.bau.Med;
import br.inimigos.bau.Pp;
import br.inimigos.inimigos.Aranha;
import br.inimigos.inimigos.DemonDog;
import br.inimigos.inimigos.FireWolf;
import br.inimigos.inimigos.LeafWolf;
import br.player.Jogador;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Inimigo {
    public static boolean dano = false, frameCrescendo = false, primeiroFrame = false;
    private static int delayAlteracaoFrame = 0, frameDano = 2;
    private static double escala;
    private static final int locX = 150;
    private static final int locY = 200;
    private static boolean isInimigo;
    private static Enemy inimigo;
    private static int xx, yy, llargura, aaltura;

    public static void newInimigo() {

        primeiroFrame = true;

        // Reiniciar variaveis
        dano = false;
        delayAlteracaoFrame =0;
        frameDano =2;
        frameCrescendo =false;

        // Iniciar inimigo
        if(new Random().nextInt(6)==0){
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
            if(new Random().nextInt(2)==0 && Jogador.getNivel()>=50) {
                System.out.println("boss");
            }else{

                int rn = new Random().nextInt(Jogador.getNivel());

                if (rn >= 50) {
                    System.out.println("inimigo Boss");
                    System.exit(0);
                }
                else if (rn >= 10) {
                    inimigo = new DemonDog(Jogador.getNivel());
                }
                else if (rn >= 5) {
                    inimigo = new FireWolf(Jogador.getNivel());
                }
                else if (rn >= 2) {
                    inimigo = new LeafWolf(Jogador.getNivel());
                }
                else {
                    inimigo = new Aranha(Jogador.getNivel());
                }
                isInimigo = true;
            }
        }
        escala = inimigo.getEscala();
        System.out.println(inimigo.getNome()+": "+inimigo.getNivel());

    }
    public static void render(Graphics g){
        g.setFont(Fontes.PIXEL.deriveFont(Font.PLAIN, 20));
        g.setColor(Color.darkGray);
        g.fillOval(locX-5, (int) (locY+(inimigo.getSprite()[frameDano].getHeight()*escala)-30), (int) (inimigo.getSprite()[frameDano].getWidth()*escala+10), 35);

        if(primeiroFrame){
            primeiroFrame = false;
            String lang = inimigo.getNome();
            if(lang.length()<("Vida: "+inimigo.getLife()+"/"+inimigo.getMaxLife()).length()){
                lang = "Vida: "+inimigo.getLife()+"/"+inimigo.getMaxLife();
            }
            xx = (int) (locX+((-g.getFontMetrics().stringWidth(lang)+(inimigo.getSprite()[frameDano].getWidth()*escala))/2)-4);
            yy = locY-54-g.getFontMetrics(Fontes.PIXEL.deriveFont(Font.PLAIN, 20)).getHeight()-10;

            llargura = g.getFontMetrics().stringWidth(lang)+5;
            aaltura = 2*(g.getFontMetrics(Fontes.PIXEL.deriveFont(Font.PLAIN, 20)).getHeight()+5);
        }
        if(dano){
            delayAlteracaoFrame++;
            if(delayAlteracaoFrame ==3){
                delayAlteracaoFrame =0;
                if(frameCrescendo){
                    frameDano++;
                }else{
                    frameDano--;
                }
                if(frameDano <0){
                    frameDano =0;
                    frameCrescendo =true;
                } else if (frameDano >2) {
                    frameDano =2;
                    frameCrescendo =false;
                    dano=false;
                }
            }
        }else{
            delayAlteracaoFrame =0;
            frameDano =2;
        }

        g.setColor(Color.BLACK);
        g.fillRect(xx,yy, llargura,aaltura);
        g.setColor(Color.WHITE);
        g.drawString("Vida: "+inimigo.getLife()+"/"+inimigo.getMaxLife(), (int) (locX+((-g.getFontMetrics().stringWidth("Vida: "+inimigo.getLife()+"/"+inimigo.getMaxLife())+(inimigo.getSprite()[frameDano].getWidth()*escala))/2)), locY-60);
        g.drawString(inimigo.getNome(), (int) (locX+((-g.getFontMetrics().stringWidth(inimigo.getNome())+(inimigo.getSprite()[frameDano].getWidth()*escala))/2)),locY-35);
        g.setColor(Color.BLACK);

        g.fillRect(locX, locY - 30, (int) (inimigo.getSprite()[frameDano].getWidth()*escala),20);
        g.setColor(Color.WHITE);
        g.fillRect(locX+5, locY - 25, (int) (inimigo.getSprite()[frameDano].getWidth()*escala-10),10);
        g.setColor(Color.GREEN);

        int lif = (int) (((inimigo.getSprite()[frameDano].getWidth() * escala - 10)* inimigo.getLife())/ inimigo.getMaxLife());

        g.fillRect(locX+5, locY - 25, lif,10);
        g.drawImage(inimigo.getSprite()[frameDano], locX,locY, (int) (inimigo.getSprite()[frameDano].getWidth()*escala), (int) (inimigo.getSprite()[frameDano].getHeight()*escala),null);
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
    public static double ataque(){
        return inimigo.atack();
    }
    public static void damage(double dan){
        inimigo.setDamage(dan);
        dano = true;
    }
    public static boolean temUso(){
        return inimigo.temPontos();
    }
    public static String getNomeAtaque() {
        return inimigo.getNomeAtaque();
    }
    public static double getVida() {
        return inimigo.getLife();
    }
    public static int getXp() {
        return inimigo.getXp();
    }
    public static boolean isInimigo() {
        return isInimigo;
    }
}
