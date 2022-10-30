package br.background;

// importes
import br.sprites.*;
import java.awt.image.BufferedImage;
import java.util.Random;
public class Fundo {

    // coordenadas para cortar o sprite
    final static int[]
            // X
            x = {
                1,258,515,772,1029,1286,1543,1800,2057,2314
            },

            // Y
            y ={
                1,431,861,1291,1721
            };
    public static BufferedImage fundo; // imagem de retorno do fundo

    // define um novo fundo
    public static void newFundo(){

        // variavel das duas imagens pack de fundos
        BufferedImage fundos1 = null, fundos2 = null;

        // carrega os image pack de fundos
        try{
            fundos1=new ImageLoader().loadImage("/sprites/fundos1.png");
            fundos2=new ImageLoader().loadImage("/sprites/fundos2.png");
        }catch (Exception ignored){}

        // aleatoriza o fundo
        if(new Random().nextInt(2)==1){
            fundo= new SpriteSheet(fundos1).getSprite(x[new Random().nextInt(x.length)],y[new Random().nextInt(y.length)],256,429);
        }
        else {
            fundo= new SpriteSheet(fundos2).getSprite(x[new Random().nextInt(x.length)],y[new Random().nextInt(y.length)],256,429);
        }

    }
}
