package br.player;

import br.Game;
import br.moves.Atacks;
import br.moves.Magicas;
import br.saves.LoadGame;
import java.awt.image.BufferedImage;

public class Guerreiro implements Player{
    String nome;
    Atacks[] atacks;
    Magicas[] magicas;
    public Guerreiro(String nome) {

        this.nome=nome;
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

    }

    @Override
    public void setNewAtack(String nomeAtaque, int pontosDeUso) {

    }

    @Override
    public void setNewMagicas(String nomeMagica, int pontosDeUso) {

    }

    @Override
    public void armaLvlUp() {

    }

    @Override
    public int useMagDan(int choice) {
        return 0;
    }

    @Override
    public int useAtack(int choice) {
        return 0;
    }

    @Override
    public int useMagicas(int choice) {
        return 0;
    }

    @Override
    public int getLife() {
        return 0;
    }

    @Override
    public int getMaxLife() {
        return 0;
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
    public Magicas getMagia(int magia) {
        return magicas[magia];
    }

    @Override
    public BufferedImage[] getSprites() {
        return new BufferedImage[0];
    }

    @Override
    public BufferedImage getSprite() {
        return null;
    }

    @Override
    public void init(boolean create) {
        if(create) {
            atacks = new Atacks[2];
            atacks[0] = new Atacks(20, "Corte Lateral",20);
            atacks[1] = new Atacks(40, "Bater com cabo",5);
            magicas = new Magicas[1];
            magicas[0] = new Magicas(2, "Afiar lamina", 2, 5);
        }else{
            atacks = LoadGame.getAtacks(Game.numSave);
            magicas = LoadGame.getMagicas(Game.numSave);
        }
    }

}
