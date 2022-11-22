package br.inimigos;

import java.awt.image.BufferedImage;

public interface Enemy {
    String getNome();
    BufferedImage[] getSprite();
    int atack();
    int getLife();
    int getMaxLife();
    void setDamage(int dano);
    int getXp();
    String getNomeAtaque();
    boolean temPontos();
    int getNivel();
}
