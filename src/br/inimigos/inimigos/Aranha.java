package br.inimigos.inimigos;

import br.inimigos.Enemy;
import br.moves.Atacks;
import br.sprites.ImageLoader;
import br.sprites.SpriteSheet;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Aranha implements Enemy {

    double life, maxLife;
    Atacks[] ataques;
    String ataqueNome;
    BufferedImage[] sprites = new BufferedImage[3];
    BufferedImage img;
    int atac = 0, pontos,nv;


    public Aranha(int nv){

        try {
            ImageLoader loader = new ImageLoader();
            img = loader.loadImage("/sprites/monstros.png");
            sprites[0] = new SpriteSheet(img).getSprite(1,1,32,30);
            sprites[1] = new SpriteSheet(img).getSprite(33,1,32,30);
            sprites[2] = new SpriteSheet(img).getSprite(65,1,32,30);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.nv = new Random().nextInt(nv)+1;
        life = maxLife = 10 +(this.nv * 10);
        ataques = new Atacks[5];

        //picada, patada, tiro de teia, teia fatal, embrulho venenoso

        ataques[0]=new Atacks(  10,     "Picada"            , 2  + this.nv);
        ataques[1]=new Atacks(  8 ,     "Patada"            , 4  + this.nv);
        ataques[2]=new Atacks(  4 ,     "Tiro de teia"      , 5  + this.nv);
        ataques[3]=new Atacks(  2 ,     "Teia fatal"        , 6  + this.nv);
        ataques[4]=new Atacks(  1 ,     "Embrulho venenoso" , 10 + this.nv);
    }

    @Override
    public String getNome() {
        return "Aranha";
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
        this.life-=dano;
        if(life<0){
            life=0;
        }
    }

    @Override
    public int getXp() {
        return 30+(nv*10);
    }

    @Override
    public String getNomeAtaque() {
        return ataqueNome;
    }

    @Override
    public boolean temPontos() {

        return pontos>0;

    }

    @Override
    public int getNivel() {
        return nv;
    }

    @Override
    public double getEscala() {
        return 2;
    }
}
