package br.inimigos.bau;

import br.inimigos.Enemy;
import br.sprites.ImageLoader;
import br.sprites.SpriteSheet;

import java.awt.image.BufferedImage;

public final class Pp implements Enemy {
    BufferedImage sprite;

    public Pp(){
        try {
            sprite = new SpriteSheet(new ImageLoader().loadImage("/sprites/pp.png")).getSprite(31,40,799,576);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public String getNome() {
        return "Pontos de Poder";
    }

    @Override
    public BufferedImage[] getSprite() {
        return new BufferedImage[]{sprite,sprite,sprite};
    }

    @Override
    public double atack() {
        return 0;
    }

    @Override
    public double getLife() {
        return 0;
    }

    @Override
    public double getMaxLife() {
        return 0;
    }

    @Override
    public void setDamage(double dano) {

    }

    @Override
    public int getXp() {
        return 0;
    }

    @Override
    public String getNomeAtaque() {
        return null;
    }

    @Override
    public boolean temPontos() {
        return false;
    }

    @Override
    public int getNivel() {
        return 0;
    }

    @Override
    public double getEscala() {
        return 0;
    }
}
