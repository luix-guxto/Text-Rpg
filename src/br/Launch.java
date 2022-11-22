package br                                                          ;
import  br   .display    .Display                                   ;
import  br   .fontes.Fontes                                    ;
import  br   .sprites    .ImageLoader                               ;
import  java .awt        .*                                         ;
import  java .awt        .image.*                                   ;
import  java .io         .*                                         ;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Launch
{

    private static      Display loading                             ;

    public  static void main(String[] args                      )
    {
        try{
            if(args[0].equals("-h")||args[0].equals("help")){
                System.out.println("menu - m --> open game in menu state\n r - ranking --> open game in ranking state\n c - credits --> open game in credits state\n s - select --> open game in select save state");
                System.exit(0);
            }
        }catch (Exception ignored){}
        Fontes  .fontInit   (                                   )   ;
        loading                 = new Display(  "CARREGANDO..."     ,
                                                408                 ,
                                                450                 ,
                                                "/sprites/icon.png");
        BufferedImage   icon                                        ;
        Font            font    = null                              ;
        ImageLoader     il      = new ImageLoader(              )   ;
        try
        {
            icon = il.loadImage         ("/sprites/icon.png"    )   ;
        } catch                         (IOException e          )
        {
            throw new RuntimeException  (e                      )   ;
        }
        try
        {
            InputStream in = new BufferedInputStream(
            Files.newInputStream(Paths.get("./recursos/fontes/pixel1.ttf"))
                                                                )   ;
            font = Font.createFont      (Font.TRUETYPE_FONT, in )   ;
        } catch (Exception e)
        {
            e.printStackTrace()                                     ;
        }

        loading.setVisible  ( true                              )   ;

        for(int x = 0; x < 50; x++)
        {
            BufferStrategy  buff    = loading.getBufferStrategy()   ;
            if(buff == null)
            {
                            loading       .createBufferStrategy()   ;
                buff    =   loading          .getBufferStrategy()   ;
            }

            Graphics        g       = buff     .getDrawGraphics()   ;

            g   .create     (                                   )   ;
            g   .setColor   ( Color.WHITE                       )   ;
            g   .fillRect   ( 0,     0,  408,    450            )   ;
            g   .drawImage  ( icon,  0,  -15,    408, 408, null )   ;
            assert font     != null                                 ;
            g   .setColor   ( Color .BLACK                      )   ;
            g   .setFont    ( font  .deriveFont(Font.PLAIN, 45) )   ;
            g   .drawString ( "Carregando...", 5, 420           )   ;
            g   .dispose    (                                   )   ;
            buff.show       (                                   )   ;
        }

        Game    game        = new Game(args                     )   ;
        game    .start      (                                   )   ;
    }

    public  static void carregando(                             )
    {
        loading .fechar     (                                   )   ;
    }
}