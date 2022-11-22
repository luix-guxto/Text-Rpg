package br.states;

import br.Game;
import br.fontes.Fontes;
import br.sprites.ImageLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class Credits implements State{
    // 700 433

    BufferedImage img;
    Font fontess, font1;
    int x, y, z;
    boolean iniciando, cresy;
    String[] text = {   "Esse jogo foi feito na aula do Flavio,",
                        "pois estava entediado.",
                        "E como a materia que ele estava passando",
                        "era orientacao a objetos,",
                        "decidi fazer um projeto",
                        "Orientado a Objetos kkkk.",
                        " ",
                        "Pensado e desenvolvido por",
                        "Luis Augusto . 1221140721",
                        "Projeto nao comercial,",
                        "sua venda e ilegal!",
                        "artes de terceiro,",
                        "todos os creditos aos criadores"};
    @Override
    public void init() {
        iniciando = true;
        cresy = true;
        x=0;
        y=200;
        z=0;

        ImageLoader il = new ImageLoader();
        try{
            img = il.loadImage("/sprites/creditos.png");
        }catch (Exception e){
            e.printStackTrace();
        }
        fontess = Fontes.PIXEL.deriveFont(Font.PLAIN, 20);
        font1 = Fontes.FERRUM.deriveFont(Font.BOLD, 35);
    }

    @Override
    public void update() {
        z++;
        if (z == 2) {
            if(iniciando){
                x+=8;
                if(x>=100){
                    iniciando= false;
                }
            }
            z=0;
        }
        if(y<=200){
            cresy=true;
        }else if(y==255){
            cresy=false;
        }

        if (cresy) {
            y++;
        } else {
            y--;
        }
    }

    @Override
    public void render(Graphics g) {
        if(iniciando) {
            g.setColor(new Color(55 + (x * 2), 55 + (x * 2), 55 + (x * 2)));
            g.fillRect(0, 0, Game.WIDTH, Game.HIGHT);
        }else {
            g.setColor(new Color(y, 255, 255));
            g.fillRect(0,0, Game.WIDTH, Game.HIGHT);
            g.drawImage(img, 0,0,700,433, null);
            g.setFont(font1);
            g.setColor(Color.BLACK);
            g.drawString("<-[esc]", 5,50);
            g.setFont(fontess);

            for(int i = 0; i < text.length; i++) {
                g.setColor(Color.BLACK);
                try{
                    g.drawString(text[i], 20, 400+g.getFontMetrics(fontess).getHeight()*i);
                }catch (Exception ignored){
                }
            }
        }
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

    @Override
    public void initFonte() {

    }
}
