package br.background;

import br.sprites.ImageLoader;
import br.sprites.SpriteSheet;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Fundo {

    final static int[] x = {
        1,258,515,772,1029,1286,1543,1800,2057,2314
    };
    final static int[] y ={
        1,431,861,1291,1721
    };

     public static BufferedImage fundo;

    public static void newFundo(){
        BufferedImage fundos1 = null, fundos2 = null;
        try{
            fundos1=new ImageLoader().loadImage("/sprites/fundos1.png");
            fundos2=new ImageLoader().loadImage("/sprites/fundos2.png");
        }catch (Exception e){}
        if(new Random().nextInt(2)==1){
            System.out.println(1);
            fundo= new SpriteSheet(fundos1).getSprite(x[new Random().nextInt(x.length)],y[new Random().nextInt(y.length)],256,429);
        }else {
            System.out.println(2);
            fundo= new SpriteSheet(fundos2).getSprite(x[new Random().nextInt(x.length)],y[new Random().nextInt(y.length)],256,429);
        }
    }
}
