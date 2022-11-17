package br.player;

import br.moves.*;
import java.awt.image.BufferedImage;

public interface Player {

    String toString();
    int getDanoBonus();
    int getDanoArma();
    int getDanoBase();
    boolean getBossIsDead();
    void setBossIsDead();
    void upLevel(); // atualizar o level
    void setXp(int xp); // aumentar xp
    void setVida(int vida); // aumentar ou diminuir vida
    void armaLvlUp(boolean up); // lvl up arma
    int useMagDan(int choice);
    int useAtack(int choice); // gastar o ponto de uso e retornar o valor do dano
    int useMagicas(int choice); // gastar o ponto de uso e retornar o valor do dano
    int getLife(); // retornar a vida do player
    int getMaxLife(); // retornar o valor de vida maxima do ‘player’
    int getXp(); // retornar o valor de xp atual
    int getLevel(); // retornar o valor do level do player
    int getXpToUp(); // retornar o valor nescessario para subir de level do player
    String getNome(); // retornar o nome do player
    boolean unlockMove();
    Atacks[] getAtacks(); // retornar a lista de ataques e a quantidade de pontos de uso (maxima e atual)
    Magicas[] getMagicas();// retornar a lista de mágicas e a quantidade de pontos de uso (maxima e atual)
    BufferedImage[] getSpritesBossLive();
    BufferedImage getSprite();
    void init(boolean create);
    void recPontoMagica(int rec, int choicc);
    void recPontoAtaque(int rec, int choicc);
    int getLvlArma();
    boolean canLvUp();
    String getUnlockedMove();
    int getClasse();
}
