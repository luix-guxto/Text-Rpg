package br.player;

import br.moves.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Player {

    String toString();

    void setScore(int pontos); // atualizar o score
    void upLevel(); // atualizar o level
    void setXp(int xp); // aumentar xp
    void setVida(int vida); // aumentar ou diminuir vida
    void setNewAtack(String nomeAtaque, int pontosDeUso); // atribuir novo ataque em level up
    void setNewMagicas(String nomeMagica, int pontosDeUso); // atribuir nova magica em level up
    void armaLvlUp(boolean up); // lvl up arma


    int useMagDan(int choice);
    int useAtack(int choice); // gastar o ponto de uso e retornar o valor do dano
    int useMagicas(int choice); // gastar o ponto de uso e retornar o valor do dano

    int getLife(); // retornar a vida do player
    int getMaxLife(); // retornar o valor de vida maxima do player
    int getXp(); // retornar o valor de xp atual
    int getLevel(); // retornar o valor do level do player
    int getXpToUp(); // retornar o valor nescessario para subir de level do player
    String getNome(); // retornar o nome do player

    Atacks[] getAtacks(); // retornar a lista de ataques e a quantidade de pontos de uso (maxima e atual)
    Magicas[] getMagicas();// retornar a lista de magicas e a quantidade de pontos de uso (maxima e atual)

    Magicas getMagia(int magia);

    BufferedImage[] getSprites();
    BufferedImage getSprite();
    void init(boolean create);

    void recPontoMagica(int rec, int choicc);

    void recPontoAtaque(int rec, int choicc);

    int getLvlArma();
}
