package br.inimigos.inimigos;

import br.inimigos.Enemy;
import br.moves.Atacks;
import br.sprites.ImageLoader;
import br.sprites.SpriteSheet;

import java.awt.image.BufferedImage;
import java.util.Random;

public class DemonDog implements Enemy {

    BufferedImage[] sprites = new BufferedImage[3];
    BufferedImage img;
    int nv, life, maxLife, atac, pontos;
    Atacks[] ataques;
    String ataqueNome;

    public DemonDog(int nv) {
        this.nv = new Random().nextInt(nv) + 1;
        life = maxLife = 20 + (this.nv * 20);
        ataques = new Atacks[5];
        try {
            ImageLoader loader = new ImageLoader();
            img = loader.loadImage("/sprites/monstros.png");
            sprites[0] = new SpriteSheet(img).getSprite(646, 130, 32, 40);
            sprites[1] = new SpriteSheet(img).getSprite(646, 170, 32, 40);
            sprites[2] = new SpriteSheet(img).getSprite(646, 210, 32, 40);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //ataques
        ataques[0] = new Atacks(10, "Patada", 4 + this.nv);
        ataques[1] = new Atacks(8, "Rabo giratorio", 8 + this.nv);
        ataques[2] = new Atacks(4, "Garra demoniaca", 10 + this.nv);
        ataques[3] = new Atacks(2, "Uivo das almas", 13 + this.nv * 2);
        ataques[4] = new Atacks(1, "Pacto mortal", 14 + this.nv * 2);
    }

    @Override
    public String getNome() {
        return "Cão Infernal";
    }

    @Override
    public BufferedImage[] getSprite() {
        return sprites;
    }

    @Override
    public double atack() {
        atac = new Random().nextInt(ataques.length);
        ataqueNome = ataques[atac].getNomeAtaque();
        pontos = ataques[atac].getPontosDeUso();
        return ataques[atac].useAtack();
    }

    @Override
    public double getLife() {
        return life;
    }

    @Override
    public double getMaxLife() {
        return maxLife;
    }

    @Override
    public void setDamage(double dano) {
        this.life -= dano;
        if (life < 0) {
            life = 0;
        }
    }

    @Override
    public int getXp() {
        return 60 + (nv*25);
    }
    @Override
    public String getNomeAtaque() {
        return ataqueNome;
    }

    @Override
    public boolean temPontos() {

        return pontos > 0;

    }

    @Override
    public int getNivel() {
        return nv;
    }

    @Override
    public double getEscala() {
        return 2.7;
    }
}