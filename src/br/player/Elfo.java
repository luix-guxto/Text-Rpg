package br.player;

import br.Game;
import br.moves.Atacks;
import br.moves.Magicas;
import br.saves.LoadGame;
import br.sprites.ImageLoader;
import br.sprites.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Elfo implements Player{
    String nome;
    Atacks[] atacks;
    Magicas[] magicas;
    BufferedImage image;
    BufferedImage[] sprites;

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
    public int useAtack(String nomeAtaque) {
        return 0;
    }

    @Override
    public int useMagicas(String nomeMagica) {
        return 0;
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
        return 0;
    }

    @Override
    public int getXpToUp() {
        return 0;
    }

    @Override
    public String getNome() {
        return null;
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
            life=maxLife=100;
            atacks = new Atacks[2];
            atacks[0] = new Atacks(25, "Atirar Flecha",10);
            atacks[1] = new Atacks(50, "Bater arco",5);
            magicas = new Magicas[1];
            magicas[0] = new Magicas(1, "Catar flechas");
        }else{
            atacks = LoadGame.getAtacks(Game.numSave);
            magicas = LoadGame.getMagicas(Game.numSave);
            life = LoadGame.getLife(Game.numSave);
            maxLife = LoadGame.getMaxLife(Game.numSave);
        }
    }
}
