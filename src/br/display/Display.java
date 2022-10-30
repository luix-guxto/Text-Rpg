package br      .display                                                        ;
import  br      .sprites.ImageLoader                                            ;
import  java    .awt    .*                                                      ;
import  java    .awt    .event.KeyListener                                      ;
import  java    .awt    .image.BufferStrategy                                   ;
import  javax   .swing  .*                                                      ;
public class Display
{
    private final JFrame    jframe                                              ;
    private final Canvas    canvas                                              ;

    public                  Display(String  titulo                              ,
                                    int     width                               ,
                                    int     heigth                              ,
                                    String  path
                                                            )
    {
        canvas              =
                                        new Canvas()                            ;
        canvas  .setPreferredSize   (
                                        new Dimension(width, heigth         )
                                    )                                           ;
        canvas  .setMaximumSize     (
                                        new Dimension(width, heigth         )
                                    )                                           ;
        canvas  .setMinimumSize     (
                                        new Dimension(width, heigth         )
                                    )                                           ;
        jframe              =           new JFrame(titulo                   )   ;
        canvas  .setFocusable       (false);
        ImageLoader loader  =            new ImageLoader()                      ;
        try
        {
        jframe  .setIconImage               (loader.loadImage(path)       ) ;
        }
        catch (Exception e)
        {
            e   .printStackTrace()                                          ;
        }
        jframe  .add                        (canvas                         )   ;
        jframe  .pack()                                                         ;
        jframe  .setDefaultCloseOperation   (WindowConstants.EXIT_ON_CLOSE  )   ;
        jframe  .setResizable               (false                          )   ;
        jframe  .setLocationRelativeTo      (null                           )   ;
        jframe  .setVisible                 (false                          )   ;
    }

    public BufferStrategy   getBufferStrategy   (           )
    {
        return
        canvas  .getBufferStrategy()                                            ;
    }

    public void             createBufferStrategy(           )
    {
        canvas  .createBufferStrategy(3)                                        ;
    }

    public void             setKeyListener  (KeyListener kl )
    {
        jframe  .addKeyListener(kl)                                             ;
    }

    @SuppressWarnings("unused")
    public void             fechar          (               )
    {
        jframe  .dispose()                                                      ;
    }

    public void             setVisible      (boolean visible)
    {
        jframe  .setVisible(visible)                                            ;
    }
}
