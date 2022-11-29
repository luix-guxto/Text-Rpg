package br.inimigos.inimigos;

import br.inimigos.Enemy;
import br.moves.Atacks;
import br.sprites.ImageLoader;
import br.sprites.SpriteSheet;

import java.awt.image.BufferedImage;
import java.util.Random;

public final class FireWolf implements Enemy {

    BufferedImage[] sprites = new BufferedImage[3];
    BufferedImage img;
    double life, maxLife;
    int nv, atac, pontos;
    Atacks[] ataques;
    String ataqueNome;

    public FireWolf(int a) {
        this.nv = new Random().nextInt(a-3) + 4;
        life = maxLife = 20 + (this.nv * 28);
        ataques = new Atacks[5];
        try {
            ImageLoader loader = new ImageLoader();
            img = loader.loadImage("/sprites/monstros.png");
            sprites[0] = new SpriteSheet(img).getSprite(130,388,32,30);
            sprites[1] = new SpriteSheet(img).getSprite(161,388,32,30);
            sprites[2] = new SpriteSheet(img).getSprite(130,418,32,30);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //ataques
        ataques[0] = new Atacks(10, "Patada", 4.5 * this.nv);
        ataques[1] = new Atacks(8, "Rabo giratorio", 5.5 * this.nv);
        ataques[2] = new Atacks(4, "Garra de fogo", 6.5 * this.nv);
        ataques[3] = new Atacks(2, "Uivo de calor", 7.5 * this.nv);
        ataques[4] = new Atacks(1, "Mordida de lava", 8.5 * this.nv);
    }

    @Override
    public String getNome() {
        return "Lobo de Fogo";
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
        return 50 + (nv * 15);
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