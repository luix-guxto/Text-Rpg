package br.player;

import br.Game;
import br.mochila.Bag;
import br.moves.Atacks;
import br.moves.Magicas;
import br.fontes.Fontes;
import br.saves.LoadGame;
import br.saves.SaveGame;
import br.states.CreatePlayer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Jogador {
    private static int frameDano =0;
    private static int delayAlteracaoFrame =0;
    private static final int locY=300;
    private static final int locX=500;
    private static final int escala = 4;
    private static boolean dan = false, frameCrescendo = true;
    private static Player player;
    private static int moveEscolhido = 0;
    private static String moveUnlocked;
    private static int andares, tempo;

    public static void criarJogador(boolean create){
        frameDano =0;
        delayAlteracaoFrame =0;
        dan=false;
        frameCrescendo =true;
        int ccc = Game.CLASSE;
        if(create) {
            switch (ccc){
                case 0:
                    player = new Elfo();
                    break;
                case 1:
                    player = new Mago();
                    break;
                case 2:
                    player = new Guerreiro();
                    break;
            }
            tempo = 0;
            player.init(true);
            SaveGame saveGame = new SaveGame();
            Atacks[] atacks = player.getAtacks();
            Magicas[] magicas = player.getMagicas();
            andares = 0;
            Bag.initBag(true);
            saveGame.salvarJogo(player.getMaxLife(), player.getBossIsDead(), player.getDanoArma(), player.getDanoBase(), andares, Game.CLASSE, CreatePlayer.nome, 100, 0, 100, 1, atacks, magicas, Game.numSave, Bag.mochila);
        }else{
            andares = LoadGame.getAndares(Game.numSave);
            switch (ccc) {
                case 0:
                    player = new Elfo();
                    break;
                case 1:
                    player = new Mago();
                    break;
                case 2:
                    player = new Guerreiro();
                    break;
            }
            tempo = LoadGame.getTime(Game.numSave);
            player.init(false);
            Bag.initBag(false);
        }

    }
    public static Atacks getAtack() {
        return player.getAtacks()[moveEscolhido];
    }
    public static Magicas getMagica() {
        return player.getMagicas()[moveEscolhido];
    }
    public static boolean unlockMoveOnUp(){
        boolean canUnlocked = player.unlockMove();
        if (canUnlocked){
            moveUnlocked = player.getUnlockedMove();
        }
        return canUnlocked;
    }
    public static String getMoveUnlocked() {
        return moveUnlocked;
    }
    public static void icon(Graphics g, int x, int y, int escala){
        g.drawImage(player.getSpritesBossLive()[0],x,y,player.getSpritesBossLive()[0].getWidth()*escala,player.getSpritesBossLive()[0].getHeight()*escala,null);
    }
    public static BufferedImage getSprite(){
        return player.getSpritesBossLive()[0];
    }
    public static void setMove(int choice){
        moveEscolhido =choice;
    }
    public static void render (Graphics g){

        if(dan){
            delayAlteracaoFrame++;
            if(delayAlteracaoFrame ==4){
                delayAlteracaoFrame =0;
                if(frameCrescendo){
                    frameDano++;
                }else{
                    frameDano--;
                }
                if(frameDano <0){
                    frameDano =0;
                    frameCrescendo =true;
                    dan=false;
                } else if (frameDano >2) {
                    frameDano =2;
                    frameCrescendo =false;
                }
            }
        }else {
            delayAlteracaoFrame =0;
            frameDano =0;
        }

        g.setColor(Color.darkGray);
        g.fillOval(locX-5, locY+(player.getSpritesBossLive()[frameDano].getHeight()*escala)-25, (player.getSpritesBossLive()[frameDano].getWidth()*escala)+10,35);
        g.setColor(Color.BLACK);

        int viv=130;

        g.setColor(Color.BLACK);
        g.fillRect(locX-(viv/4), locY - 40, viv,20);
        g.setColor(Color.WHITE);
        g.fillRect(locX+5-(viv/4), locY - 35, viv-10,10);
        g.setColor(Color.GREEN);

        int lif = ((viv-10)* player.getLife())/ player.getMaxLife();

        g.fillRect(locX+5-(viv/4), locY - 35, lif,10);
        g.drawImage(player.getSpritesBossLive()[frameDano], locX,locY,player.getSpritesBossLive()[frameDano].getWidth()*escala,player.getSpritesBossLive()[frameDano].getHeight()*escala,null);

        g.setColor(Color.BLACK);
        g.fillRect((locX+(-g.getFontMetrics().stringWidth(player.getNome())+player.getSpritesBossLive()[frameDano].getWidth()*escala)/2)-4,locY-65,g.getFontMetrics().stringWidth(player.getNome())+5,5+g.getFontMetrics(Fontes.PIXEL.deriveFont(Font.PLAIN, 20)).getHeight());
        g.setFont(Fontes.PIXEL.deriveFont(Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString(player.getNome(), locX+(-g.getFontMetrics().stringWidth(player.getNome())+player.getSpritesBossLive()[frameDano].getWidth()*escala)/2,locY-45);

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
    public static double atacar(){
        return player.useAtack(moveEscolhido);
    }
    public static void receberDano (double dano){
        dan = true;
        player.setVida(-dano);
    }
    public static int getVida() {
        return player.getLife();
    }
    public static boolean magDano() {
        return player.getMagicas()[moveEscolhido].getTipo() == 1;
    }
    public static double useMagDan() {
        return player.useMagDan(moveEscolhido);
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
    public static double getDano(int choicc, boolean b) {
        if(b){
            if(player.getAtacks()[choicc]==null){return 0;}
            return player.getAtacks()[choicc].getDano() * player.getDanoBonus();
        }else {
            if(player.getMagicas()[choicc]==null){return 0;}
                return player.getMagicas()[choicc].getDano() * player.getDanoBonus();
        }
    }
    public static boolean isDanMag(int choicc) {
        if(player.getMagicas()[choicc]==null){return false;}
        return player.getMagicas()[choicc].getTipo()==1;
    }
    public static double useMag() {
        return player.useMagicas(moveEscolhido);
    }
    public static void recVida(double vida) {

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
    public static void recPonto(boolean atac, int rec, int choicc) {
        if(atac){
            player.recPontoAtaque(rec,choicc);
        }else {
            player.recPontoMagica(rec,choicc);
        }
    }
    public static void upArma(boolean upArma) {
        player.armaLvlUp(upArma);
    }
    public static int getLvlArma() {
        return player.getLvlArma();
    }
    public static void addXp(int xp){
        player.setXp(player.getXp()+xp);
    }
    public static boolean checkLvUp(){
        return player.canLvUp();
    }
    public static void lvUp(){
        player.upLevel();
    }
    public static int getXp() {
        return player.getXp();
    }
    public static int getXpToLvUp() {
        return player.getXpToUp();
    }
    public static int getClasse() {
        return player.getClasse();
    }
    public static int getDanoBase(){
        return player.getDanoBase();
    }
    public static int getDanoArma(){
        return player.getDanoArma();
    }
    public static int getAndares() {
        return andares;
    }
    public static void addAndares() {
        andares++;
    }
    public static boolean getBossIsDead() {
        return player.getBossIsDead();
    }
    public static int getTempo() {
        return tempo;
    }
}
