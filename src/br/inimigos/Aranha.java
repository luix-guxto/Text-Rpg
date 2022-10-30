package br.inimigos;

import br.moves.Atacks;
import br.sprites.ImageLoader;
import br.sprites.SpriteSheet;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Aranha implements Enemy{

    int nv;
    int life, maxLife;
    Atacks[] ataques;
    String ataqueNome;
    BufferedImage[] sprites = new BufferedImage[3];
    BufferedImage img;
    int atac = 0, pontos;


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
        life = maxLife = 5 +(this.nv * 2);
        ataques = new Atacks[5];

        //picada, patada, tiro de teia, teia fatal, embrulho venenoso

        ataques[0]=new Atacks(  20,     "Picada"            , 2 + this.nv);
        ataques[1]=new Atacks(  20,     "Patada"            , 2 + this.nv);
        ataques[2]=new Atacks(  10,     "Tiro de teia"      , 4 + ( this.nv * 2));
        ataques[3]=new Atacks(  5 ,     "Teia fatal"        , 6 + ( this.nv * 3));
        ataques[4]=new Atacks(  1 ,     "Embrulho venenoso" , 8 + ( this.nv * 4));
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
    public int atack() {
        atac = new Random().nextInt(ataques.length);
        ataqueNome = ataques[atac].getNomeAtaque();
        pontos = ataques[atac].getPontosDeUso();
        return ataques[atac].useAtack();
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public int getMaxLife() {
        return maxLife;
    }

    @Override
    public void setDamage(int dano) {
        this.life-=dano;
        if(life<0){
            life=0;
        }
    }

    @Override
    public int getXp() {
        return 20 + (this.nv*10);
    }

    @Override
    public String getNomeAtaque() {
        return ataqueNome;
    }

    @Override
    public boolean temPontos() {

        return pontos>0;

    }
}
