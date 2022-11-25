package br.states;

import br.Game;
import br.inimigos.Inimigo;
import br.fontes.Fontes;
import br.player.Jogador;
import br.saves.Delete;
import br.saves.LoadGame;
import br.sprites.ImageLoader;
import br.sprites.SpriteSheet;
import br.utilidades.Utilidades;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;

@SuppressWarnings("unused")
public class SelectSave implements State{

        BufferedImage menu, classes;
        BufferedImage[] classe = new BufferedImage[3];
        String[] saves = new String[3];
        boolean[] haveSave = new boolean[3];
        int[] clases = new int[3];
        int choice = 0;
        boolean cres;
        int cor, z;

        Font font1, font2;

        File[] files = new File[3];

        @Override
        public void init () {
            cor = 0;
            z = 0;
            cres = true;
        for (int i = 0; i < saves.length; i++) {
            try {
                files[i] = new File("./saves/"+i+".json");
            }catch (Exception e){
                e.printStackTrace();
            }
            if (files[i].exists() && LoadGame.getNome(i)!=null) {
                haveSave[i] = true;
                clases[i] = LoadGame.getClasse(i);
            saves[i] = i + 1 +": "+ LoadGame.getNome(i)+" - Nv: "+LoadGame.getNv(i)+" - Tempo: "+ Utilidades.calcTime(i);
            } else {
                haveSave[i]=false;
                saves[i] = i + 1 + ": Criar um novo jogo.";
            }
        }
        try {
            font1 = Fontes.PIXEL.deriveFont(Font.PLAIN, 90);
            font2 = Fontes.PIXEL.deriveFont(Font.PLAIN, 25);
            ImageLoader loader = new ImageLoader();
            menu = loader.loadImage("/sprites/pause.png");
            classes = loader.loadImage("/sprites/classes.png");
            for (int i = 0; i < classe.length; i++) {
                classe[i] = new SpriteSheet(classes).getSprite((i * 32) + 1, 0, 30, 32);
            }
        } catch (Exception ignored) {

        }

    }

        @Override
        public void update () {
            z++;

            if (z == 2) {
                z=0;
                if(cres){
                    cor+=2;
                }else{
                    cor-=2;
                }
            }
            if(cor<=0){
                cor = 0;
                cres=true;
            } else if (cor >= 55) {
                cor = 55;
                cres = false;
            }
    }

        @Override
        public void render (Graphics g){

        // BackGround
        g.setColor(new Color(255-cor, 255, 255));
        g.fillRect(0, 0, Game.WIDTH, Game.HIGHT);


            // Icon
            if(haveSave[choice]){
                g.setColor(Color.LIGHT_GRAY);
                int numclasse = clases[choice];
                g.fillRect(10,262-110,(int) (30*2.8),(int) (32*2.85));
                g.drawImage(classe[numclasse], 10,264-110, (int) (30*2.8),(int) (32*2.8), null);
            }else{
                g.setFont(font1);
               g.setColor(Color.BLACK);
                g.drawString("?",25,225);
            }

        // Menu
        g.drawImage(menu, 10, Game.HIGHT / 2 - 200, 680, 376, null);

        // Saves
            g.setFont(font2);
        for(int i = 0; i< saves.length;i++){
            g.setColor(Color.white);
            if(i==choice){
                g.setColor(new Color(255, 90+cor*3, 90+cor*3));
            }
            g.drawString(saves[i], 50, 320+(i*30)+g.getFontMetrics().getHeight()*i);

        }

        // texto
        g.setFont(font2);
        g.setColor(Color.WHITE);
        g.drawString("[ esc ] Voltar", 450, 500);
        g.drawString("Selecione um jogo salvo", 110, 210);

        g.setColor(Color.RED);
        g.setFont(Fontes.PIXEL.deriveFont(Font.PLAIN, 20));

        g.drawString("[ backspace ] Apagar Save", 40, 500);
    }

        @Override
        public void KeyPress ( int cod){

    }

        @Override
        public void KeyReleased ( int cod){
            if(cod == KeyEvent.VK_BACK_SPACE){
                if(haveSave[choice]) {
                    Game.numSave = choice;
                    Delete delete = new Delete();
                    delete.deleteSave(choice);
                    StateManager.setState(StateManager.CREATE_PLAYER);
                }
            }
            if(cod == KeyEvent.VK_ESCAPE){
                StateManager.setState(StateManager.MENU1);
            }
        if (cod == KeyEvent.VK_W || cod == KeyEvent.VK_UP) {
            choice--;
            if (choice < 0) {
                choice = saves.length - 1;
            }
        }
        if (cod == KeyEvent.VK_S || cod == KeyEvent.VK_DOWN) {
            choice++;
            if (choice > saves.length - 1) {
                choice = 0;
            }
        }
        if (cod == KeyEvent.VK_ENTER) {
            Game.numSave = choice;
            switch (choice) {
                case 0:
                    if (haveSave[0]) {
                        Game.CLASSE=LoadGame.getClasse(Game.numSave);
                        Jogador.criarJogador(false);
                        Inimigo.newInimigo();
                        Game.fistTime= Game.lastTime  = System.currentTimeMillis();
                        StateManager.setState(StateManager.PREBATALHA);
                    } else {
                        StateManager.setState(StateManager.CREATE_PLAYER);
                    }
                    break;
                case 1:
                    if (haveSave[1]) {
                        Game.CLASSE=LoadGame.getClasse(Game.numSave);
                        Jogador.criarJogador(false);
                        Inimigo.newInimigo();
                        Game.fistTime= Game.lastTime  = System.currentTimeMillis();
                        StateManager.setState(StateManager.PREBATALHA);
                    } else {
                        StateManager.setState(StateManager.CREATE_PLAYER);
                    }
                    break;
                case 2:
                    if (haveSave[2]) {
                        Game.CLASSE=LoadGame.getClasse(Game.numSave);
                        Jogador.criarJogador(false);
                        Inimigo.newInimigo();
                        Game.fistTime= Game.lastTime  = System.currentTimeMillis();
                        StateManager.setState(StateManager.PREBATALHA);
                    } else {
                        StateManager.setState(StateManager.CREATE_PLAYER);
                    }
                    break;
            }
        }
    }

    @Override
    public void initFonte() {

    }
}