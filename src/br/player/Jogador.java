package br.player;

import br.Game;
import br.moves.Atacks;
import br.moves.Magicas;
import br.saves.LoadGame;
import br.saves.SaveGame;
import br.states.CreatePlayer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Jogador {
    static int y=0,z=0, locY=300, locX=500, escala = 4;
    static boolean dan = false,cres = true;
    static Player player;
    public static void criarJogador(boolean create){
        y=0;
        z=0;
        dan=false;
        cres=true;
        int ccc = Game.CLASSE;
        if(create) {
            switch (ccc){
                case 0:
                    player = new Elfo(CreatePlayer.nome);
                    break;
                case 1:
                    player = new Mago(CreatePlayer.nome);
                    break;
                case 2:
                    player = new Guerreiro(CreatePlayer.nome);
                    break;
            }
            player.init(create);
            SaveGame saveGame = new SaveGame();
            Atacks[] atacks = player.getAtacks();
            Magicas[] magicas = player.getMagicas();
            saveGame.salvarJogo(Game.CLASSE, CreatePlayer.nome, 100, 100, 0, 1, atacks, magicas, Game.numSave);
        }else{
            switch (ccc) {
                case 0:
                    player = new Elfo(LoadGame.getNome(Game.numSave));
                    break;
                case 1:
                    player = new Mago(LoadGame.getNome(Game.numSave));
                    break;
                case 2:
                    player = new Guerreiro(LoadGame.getNome(Game.numSave));
                    break;
            }
            player.init(create);
        }

    }

    public static void icon(Graphics g, int x, int y, int escala){
        g.drawImage(player.getSprites()[0],x,y,player.getSprites()[0].getWidth()*escala,player.getSprites()[0].getHeight()*escala,null);
    }

    public static BufferedImage getSprite(){
        return player.getSprites()[0];
    }
    public static void render (Graphics g){

        if(dan){
            z++;
            if(z==4){
                z=0;
                if(cres){
                    y++;
                }else{
                    y--;
                }
                if(y<0){
                    y=0;
                    cres=true;
                    dan=false;
                } else if (y>2) {
                    y=2;
                    cres=false;
                }
            }
        }else {
            z=0;
            y=0;
        }
        int viv=130;
        g.setColor(Color.BLACK);
        g.fillRect(locX-(viv/4), locY - 40, viv,20);
        g.setColor(Color.WHITE);
        g.fillRect(locX+5-(viv/4), locY - 35, viv-10,10);
        g.setColor(Color.GREEN);

        int lif = ((viv-10)* player.getLife())/ player.getMaxLife();

        g.fillRect(locX+5-(viv/4), locY - 35, lif,10);
        g.drawImage(player.getSprites()[y], locX,locY,player.getSprites()[y].getWidth()*escala,player.getSprites()[y].getHeight()*escala,null);


    }

    public static int atacar(boolean ataqueOuMagica, int numMove){
        int dano = 0;
        return dano;
    }

    public static void receberDano (int dano){
        dan = true;
        player.setVida(-dano);
    }
}
