package br  .fontes;
import  java.awt.*                                                                          ;
import  java.io .*                                                                          ;
@SuppressWarnings("unused")
public class Fontes
{
    public static Font  PIXEL                                                               ,
                        FERRUM                                                              ,
                        FANTASY                                                             ;
    public static void fontInit()
    {
        try
        {
            InputStream ip      = new BufferedInputStream(
                                  new FileInputStream("./recursos/fontes/font_final.otf"))  ;
            InputStream pi      = new BufferedInputStream(
                                  new FileInputStream("./recursos/fontes/ferrum.otf")    )  ;
            InputStream pa      = new BufferedInputStream(
                                  new FileInputStream("./recursos/fontes/pixel1.ttf")    )  ;
                        FERRUM  =     Font.createFont(Font.TRUETYPE_FONT, pi             )  ;
                        FANTASY =     Font.createFont(Font.TRUETYPE_FONT, ip             )  ;
                        PIXEL   =     Font.createFont(Font.TRUETYPE_FONT, pa             )  ;
        }
        catch (Exception e)
        {
            e.printStackTrace()                                                             ;
        }
    }
}
