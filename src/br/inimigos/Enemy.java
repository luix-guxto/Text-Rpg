package br.inimigos;

import java.awt.image.BufferedImage;

public interface Enemy {
    String getNome();
    BufferedImage[] getSprite();
    double atack();
    double getLife();
    double getMaxLife();
    void setDamage(double dano);
    int getXp();
    String getNomeAtaque();
    boolean temPontos();
    int getNivel();
    double getEscala();
}
