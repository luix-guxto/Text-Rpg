package br.player;

import br.Game;
import br.moves.Atacks;
import br.moves.Magicas;
import br.saves.LoadGame;

import java.awt.image.BufferedImage;

public class Mago implements Player{
    Atacks[] atacks;
    Magicas[] magicas;
    private int lvlArma;

    public Mago() {

    }

    @Override
    public boolean canLvUp() {
        return getXp()>=getXpToUp();
    }

    @Override
    public String getUnlockedMove() {
        return null;
    }

    @Override
    public int getClasse() {
        return 1;
    }


    @Override
    public int getDanoBonus() {
        return 0;
    }

    @Override
    public int getDanoArma() {
        return 0;
    }

    @Override
    public int getDanoBase() {
        return 0;
    }

    @Override
    public boolean getBossIsDead() {
        return false;
    }

    @Override
    public void setBossIsDead() {

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
    public int getLvlArma() {
        return lvlArma;
    }
    @Override
    public void armaLvlUp(boolean up) {

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
    public boolean unlockMove() {
        return false;
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
    public BufferedImage[] getSpritesBossLive() {
        return new BufferedImage[0];
    }

    @Override
    public BufferedImage getSprite() {
        return null;
    }

    @Override
    public void init(boolean create) {
        if(create) {
            atacks = new Atacks[1];
            atacks[0] = new Atacks(40, "Bater com Cajado", 5);
            magicas = new Magicas[2];
            magicas[0] = new Magicas(25, "Bola de magia", 1,  20);
            magicas[1] = new Magicas(1, "Recarregar ataques", 2,  5);

        }else{
            atacks = LoadGame.getAtacks(Game.numSave);
            magicas = LoadGame.getMagicas(Game.numSave);
        }
    }

    @Override
    public void recPontoMagica(int rec, int choicc) {

    }

    @Override
    public void recPontoAtaque(int rec, int choicc) {

    }

}
