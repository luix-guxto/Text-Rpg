package br.player;

import br.Game;
import br.moves.Atacks;
import br.moves.Magicas;
import br.saves.LoadGame;
import br.sprites.ImageLoader;
import br.sprites.SpriteSheet;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

public class Elfo implements Player{
    String nome;
    Atacks[] atacks = new Atacks[4];
    Magicas[] magicas = new Magicas[4];
    BufferedImage image;
    BufferedImage[] sprites;

    @Override
    public String toString() {
        return "Elfo{" +
                "nome='" + nome + '\'' +
                ", atacks=" + Arrays.toString(atacks) +
                ", magicas=" + Arrays.toString(magicas) +
                ", image=" + image +
                ", sprites=" + Arrays.toString(sprites) +
                ", danoBase=" + danoBase +
                ", danoArma=" + danoArma +
                ", lvlArma=" + lvlArma +
                ", toLvlUpArma=" + toLvlUpArma +
                ", life=" + life +
                ", maxLife=" + maxLife +
                '}';
    }

    private int danoBase, danoArma, lvlArma, toLvlUpArma;

    int life, maxLife;

    public Elfo(String nome) {
        this.nome = nome;
        try {
            ImageLoader loader = new ImageLoader();
            image = loader.loadImage("/sprites/elfo.png");
            sprites = new BufferedImage[4];
            sprites[0]=new SpriteSheet(image).getSprite(1,151,16,24);
            sprites[1]=new SpriteSheet(image).getSprite(1,176,16,24);
            sprites[2]=new SpriteSheet(image).getSprite(1,201,16,24);
            sprites[3]=new SpriteSheet(image).getSprite(1,226,16,24);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void setScore(int pontos) {

    }

    @Override
    public void upLevel() {

    }

    @Override
    public void setXp(int xp) {

    }

    @Override
    public void setVida(int vida) {
        life+=vida;
        if(life<0){
            life=0;
        } else if (life>maxLife) {
            life=maxLife;
        }
    }

    @Override
    public void setNewAtack(String nomeAtaque, int pontosDeUso) {

    }

    @Override
    public void setNewMagicas(String nomeMagica, int pontosDeUso) {

    }

    @Override
    public void armaLvlUp(boolean up) {
        if(up){
            lvlArma++;
            toLvlUpArma = 5 + lvlArma * 3;
            danoArma += new Random().nextInt(5);
        }
    }

    @Override
    public int useMagDan(int choice) {
        return magicas[choice].useMagica()+danoArma+danoBase;
    }

    @Override
    public int useAtack(int choice) {

        return atacks[choice].useAtack()+danoArma+danoBase;
    }

    @Override
    public int useMagicas(int choice) {
        return magicas[choice].useMagica()+danoBase;
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public int getMaxLife() {
        return maxLife;
    }

    @Override
    public int getXp() {
        return 0;
    }

    @Override
    public int getLevel() {
        return LoadGame.getNv(Game.numSave);
    }

    @Override
    public int getXpToUp() {
        return 0;
    }

    @Override
    public String getNome() {
        return LoadGame.getNome(Game.numSave);
    }

    @Override
    public Atacks[] getAtacks() {
        return atacks;
    }

    @Override
    public Magicas[] getMagicas() {
        return magicas;
    }

    @Override
    public Magicas getMagia(int magia) {
        return magicas[magia];
    }

    @Override
    public BufferedImage[] getSprites() {
        return sprites;
    }

    @Override
    public BufferedImage getSprite() {
        return sprites[0];
    }



    @Override
    public void init(boolean create) {
        if(create) {
            danoBase=0;
            danoArma=0;
            lvlArma=0;
            toLvlUpArma=5;
            life=maxLife=100;
            atacks[0] = new Atacks(25, "Atirar Flecha",10);
            atacks[1] = new Atacks(50, "Bater arco",5);
            magicas[0] = new Magicas(1, "Catar flechas", 2, 20);
        }else{
            for (int i = 0; i<4; i++) {
                try{
                    atacks[i] = LoadGame.getAtacks(Game.numSave)[i];
                }catch (Exception e){
                    atacks[i] = null;
                }
            }
            for (int i = 0; i<4; i++) {
                try{
                    magicas[i] = LoadGame.getMagicas(Game.numSave)[i];
                }catch (Exception e){
                    magicas[i] = null;
                }
            }
            life = LoadGame.getLife(Game.numSave);
            maxLife = LoadGame.getMaxLife(Game.numSave);
        }
    }

    @Override
    public void recPontoMagica(int rec, int choicc) {
        magicas[choicc].setPontosDeUso(rec);
    }

    @Override
    public void recPontoAtaque(int rec, int choicc) {
        atacks[choicc].setPontosDeUso(rec);
    }

    @Override
    public int getLvlArma() {
        return lvlArma;
    }
}
