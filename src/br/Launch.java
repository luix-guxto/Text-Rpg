package br                                                          ;
import  br   .display    .Display                                   ;
import  br   .pixelfonte .Fontes                                    ;
import  br   .sprites    .ImageLoader                               ;
import  java .awt        .*                                         ;
import  java .awt        .image.*                                   ;
import  java .io         .*                                         ;

public class Launch
{

    private static      Display loading                             ;

    public  static void main(String[] args                      )
    {
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
            new FileInputStream ("./recursos/fontes/pixel1.ttf" )
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

        Game    game        = new Game(                         )   ;
        game    .start      (                                   )   ;
    }

    public  static void carregando(                             )
    {
        loading .fechar     (                                   )   ;
    }
}

// cola para WRITE JSON
/* escrever o json
import org.json.simple.JSONObject;

FileWriter writeFile = null;
JSONObject objetoJson = new JSONObject;

    objetoJson.put("chave", "valor");

    try{
        writeFile = new FileWriter("path"+"arquivo.json")
        writeFile.write(objetoJson.toJSONString());
        writeFile.close();
    }catch(Exception e){
    e.printStackTrace();
    }
 */

// cola para READ JSON
/* Ler o Json
*
*   JSONObject objJSON;
*   JSONParser parser = new JSONParser();
*
*   Pessoa pessoa = new Pessoa();
*
*   try{
*       objJSON = (JSONObject) parser.parse(new FileReader("arquivo.json"));
*       String texto = (String) objJSON.get("chave");
*   }catch(Exception e){
*           e.printStackTrace
*           }
*
* */