package br;

// importes
import br.display.Display;
import br.pixelfonte.Fontes;
import br.sprites.ImageLoader;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class Launch {

    // variavel da tela
    private static Display loading;
    public static void main(String[] args) {

        // Carrega as fontes usadas no jogo
        Fontes.fontInit();

        //inicia a tela de carregamento do game
        loading = new Display("CARREGANDO...", 408,450,"/sprites/icon.png");

        // tela de carregamento
        BufferedImage icon; // imagem
        Font font = null; // fonte
        ImageLoader il = new ImageLoader(); // carregador de imagens

        //carrega a imagem
        try{
            icon = il.loadImage("/sprites/icon.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //carega a fonte
        try{
            InputStream in = new BufferedInputStream(new FileInputStream("./recursos/fontes/pixel1.ttf"));
            font = Font.createFont(Font.TRUETYPE_FONT, in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //deixa a tela de carregamento visivel
        loading.setVisible(true);

        //desenho na tela
        for(int x = 0;x<50;x++){

            // pega ou cria um Buffer para os graficos
            BufferStrategy buff = loading.getBufferStrategy();
            if(buff == null){
                loading.createBufferStrategy();
                buff = loading.getBufferStrategy();
            }

            // variavel grafica
            Graphics g = buff.getDrawGraphics();
            g.create();

            // desenho da tela
            g.setColor(Color.WHITE);
            g.fillRect(0,0,408,450);

            // imagem
            g.drawImage(icon, 0,-15,408,408,null);

            assert font != null;

            // Carregando... texto
            g.setColor(Color.BLACK);
            g.setFont(font.deriveFont(Font.PLAIN, 45));
            g.drawString("Carregando...",5,420);

            // mostra os graficos na tela
            g.dispose();
            buff.show();
        }

        // cria a nova variavel game
        Game game = new Game();
        // inicia o game
        game.start();
    }

    // metodo para finalizar a tela de carregamento
    public static void carregando(){
        loading.fechar();
    }
}

// cola para WRITE JSON
/* escrever o json
import org.json.simple.JSONObject;

FileWriter writeFile = null;
JSONObject onjetoJson = new JSONObject;

    onjetoJson.put("chave", "valor");

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