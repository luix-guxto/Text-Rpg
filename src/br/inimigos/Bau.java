package br.inimigos;

import br.sprites.ImageLoader;
import br.sprites.SpriteSheet;

import java.awt.image.BufferedImage;

public class Bau implements Enemy{
    private BufferedImage sprite;
    public Bau(){
        try {
            sprite = new SpriteSheet(new ImageLoader().loadImage("/sprites/bau.png")).getSprite(31,40,799,576);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String getNome() {
        return "Bau de Itens";
    }

    @Override
    public BufferedImage[] getSprite() {
        return new BufferedImage[]{sprite,sprite,sprite};
    }

    @Override
    public int atack() {
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
    public void setDamage(int dano) {

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
}
