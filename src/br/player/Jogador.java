package br.player;

import br.Game;
import br.moves.Atacks;
import br.moves.Magicas;
import br.pixelfonte.Fontes;
import br.saves.LoadGame;
import br.saves.SaveGame;
import br.states.CreatePlayer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Jogador {
    private static int y=0;
    private static int z=0;
    private static final int locY=300;
    private static final int locX=500;
    private static final int escala = 4;
    private static boolean dan = false,cres = true;
    private static Player player;

    private static int cchois = 0;

    public static Atacks getAtack() {
        return player.getAtacks()[cchois];
    }

    public static Magicas getMagica() {
        return player.getMagicas()[cchois];
    }

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
            player.init(true);
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
            player.init(false);
        }

    }

    public static void icon(Graphics g, int x, int y, int escala){
        g.drawImage(player.getSprites()[0],x,y,player.getSprites()[0].getWidth()*escala,player.getSprites()[0].getHeight()*escala,null);
    }

    public static BufferedImage getSprite(){
        return player.getSprites()[0];
    }

    public static void setMove(int choice){
        cchois=choice;
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


        g.setColor(Color.BLACK);

        int viv=130;

        g.setColor(Color.BLACK);
        g.fillRect(locX-(viv/4), locY - 40, viv,20);
        g.setColor(Color.WHITE);
        g.fillRect(locX+5-(viv/4), locY - 35, viv-10,10);
        g.setColor(Color.GREEN);

        int lif = ((viv-10)* player.getLife())/ player.getMaxLife();

        g.fillRect(locX+5-(viv/4), locY - 35, lif,10);
        g.drawImage(player.getSprites()[y], locX,locY,player.getSprites()[y].getWidth()*escala,player.getSprites()[y].getHeight()*escala,null);

        g.setColor(Color.BLACK);
        g.fillRect((locX+(-g.getFontMetrics().stringWidth(player.getNome())+player.getSprites()[y].getWidth()*escala)/2)-4,locY-65,g.getFontMetrics().stringWidth(player.getNome())+5,5+g.getFontMetrics(Fontes.PIXEL.deriveFont(Font.PLAIN, 20)).getHeight());
        g.setFont(Fontes.PIXEL.deriveFont(Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString(player.getNome(), locX+(-g.getFontMetrics().stringWidth(player.getNome())+player.getSprites()[y].getWidth()*escala)/2,locY-45);

    }

    public static String[] nomeAtaques(){
        String[] a = new String[4];
        for (int i = 0; i < a.length;i++){
            try{
                a[i] = i+1+". "+player.getAtacks()[i].getNomeAtaque();
            }catch (Exception e){
                a[i] = i+1+". "+"______";
            }
        }
        return a;
    }

    public static Atacks[] getAtacks(){
        return player.getAtacks();
    }

    public static Magicas[] getMagicas(){
        return player.getMagicas();
    }

    public static String[] nomeMagicas(){
        String[] a = new String[4];
        for (int i = 0; i < a.length;i++){
            try{
                a[i] = i+1+". "+player.getMagicas()[i].getNomeMagica();
            }catch (Exception e){
                a[i] = i+1+". "+"______";
            }
        }
        return a;
    }

    public static int atacar(){
        return player.useAtack(cchois);
    }

    public static void receberDano (int dano){
        dan = true;
        player.setVida(-dano);
    }

    public static int getVida() {
        return player.getLife();
    }

    public static boolean magDano() {
        return player.getMagicas()[cchois].getTipo() == 1;
    }

    public static int useMagDan() {
        return player.useMagDan(cchois);
    }

    public static int getPontosUso(int choce, boolean atac) {
        if(atac){
            if(player.getAtacks()[choce]==null){return 0;}
            return player.getAtacks()[choce].getPontosDeUso();
        }else {
            if(player.getMagicas()[choce]==null){return 0;}
            return player.getMagicas()[choce].getPontosDeUso();
        }
    }

    public static int getPontosUsoMax(int choicc, boolean b) {

        if(b){
            if(player.getAtacks()[choicc]==null){return 0;}
            return player.getAtacks()[choicc].getPontosDeUsoMax();
        }else {
            if(player.getMagicas()[choicc]==null){return 0;}
            return player.getMagicas()[choicc].getPontosDeUsoMax();
        }

    }

    public static int getDano(int choicc, boolean b) {
        if(b){
            if(player.getAtacks()[choicc]==null){return 0;}
            return player.getAtacks()[choicc].getDano();
        }else {
            if(player.getMagicas()[choicc]==null){return 0;}
                return player.getMagicas()[choicc].getDano();
        }
    }

    public static boolean isDanMag(int choicc) {
        if(player.getMagicas()[choicc]==null){return false;}
        return player.getMagicas()[choicc].getTipo()==1;
    }

    public static int useMag() {
        return player.useMagicas(cchois);
    }

    public static void recVida(int vida) {

        player.setVida(vida);

    }

    public static int getVidaMax() {
        return player.getMaxLife();
    }

    public static Player getPlayer() {
        return player;
    }

    public static int getNivel() {
        return player.getLevel();
    }

    public static String getNome() {
        return player.getNome();
    }
}
