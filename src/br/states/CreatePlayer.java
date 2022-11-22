package br.states;

import br.Game;
import br.fontes.Fontes;
import br.player.Jogador;
import br.sprites.ImageLoader;
import br.sprites.SpriteSheet;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class CreatePlayer implements State{

  boolean wait = false;

  BufferedImage menu, classes;
  BufferedImage[] classe = new BufferedImage[3];
  String[] text= {"Digite seu nome.","Selecione uma classe para jogar."};
  String[] name=new String[16];
  public static String nome;
  public int CLASSE;
  String[] teclado={
          "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
          "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
          "0","1","2","3","4","5","6","7","8","9","0",
          ".",",","?","_","!","[","]","        [ CONFIRMAR ]","                                          [ APAGAR ]"
  };
  int tecl = 0;
  int pross=0;

  boolean cres, pisc;
  int cor, z, pos, y;

  Font font1, font2;

  String[] clases = {"Elfo . Facil", "Mago . Medio", "Guerreiro . Dificil"};

  @Override
  public void init () {
    CLASSE =1;
    pross=0;
    cor = 0;
    z = 0;
    cres = true;
    pisc = true;
    pos = 0;
    name = new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    nome = "";

    try {
      font1 = Fontes.PIXEL.deriveFont(Font.PLAIN, 90);
      font2 = Fontes.PIXEL.deriveFont(Font.PLAIN, 20);
      ImageLoader loader = new ImageLoader();
      menu = loader.loadImage("/sprites/pause.png");
      classes = loader.loadImage("/sprites/classes.png");
      for (int i = 0; i < classe.length; i++) {
        classe[i] = new SpriteSheet(classes).getSprite((i * 32) + 1, 0, 30, 32);
      }
    } catch (Exception ignored) {

    }

  }

  @Override
  public void update () {
    z++;

    if (z == 2) {
        wait=true;
      y++;
      if(y==4){
        y=0;
        pisc=!pisc;
      }
      z=0;
      if(cres){
        cor+=2;
      }else{
        cor-=2;
      }
    }
    if(cor<=0){
      cor = 0;
      cres=true;
    } else if (cor >= 55) {
      cor = 55;
      cres = false;
    }
  }

  @Override
  public void render (Graphics g){

    // BackGround
    g.setColor(new Color(255-cor, 255, 255));
    g.fillRect(0, 0, Game.WIDTH, Game.HIGHT);


    // Icon

      g.setFont(font1);
      g.setColor(Color.BLACK);
      g.drawString("?",25,225);


    // Menu
    g.drawImage(menu, 10, Game.HIGHT / 2 - 200, 680, 376, null);

    // opcoes;
    if(pross == 0){
      int a = 0;
      int b = 0;
      int c = 0;
      for(int i = 0; i<teclado.length;i++){
        if(a==21){
          a=0;
          b++;
          c=0;
        }
        g.setColor(Color.WHITE);
        if(i>=teclado.length-2){
          g.setColor(Color.BLACK);
        }
        if(tecl==i){
          g.setColor(Color.RED);
          if(i>=teclado.length-2){
            g.setColor(new Color(cor*4, 0, 0));
          }
        }
        g.setFont(font2);
        g.drawString(teclado[i],35+(30*c),300+(50*b));
        c++;
        a++;
      }
      for(int i = 0; i<name.length;i++){
        g.setColor(Color.RED);
       try{
         g.setFont(Fontes.PIXEL.deriveFont(Font.PLAIN,25));
       }catch (Exception ignored){

       }
        if(pos == i && pisc){
          g.drawString("_", 110+(30*i),260);
        }else if(pos!=i){
          g.drawString(name[i], 110+(30*i),260);
        }
      }

    }
    else{
      g.setColor(Color.LIGHT_GRAY);
      g.setFont(Fontes.PIXEL.deriveFont(Font.BOLD, 27));
      g.drawString(clases[CLASSE],110,260);
      for(int i = 0; i< classe.length;i++){
        if(CLASSE ==i && pisc){
          g.setColor(Color.RED);
          g.drawRect(50+(i*(30*8)),275,30*4,10+32*4);
        }
        g.drawImage(classe[i],50+(i*(30*8)),280,30*4,32*4,null);
      }
    }

    // textos
    g.setFont(font2);
    g.setColor(Color.WHITE);
    g.drawString("[ esc ] Voltar", 450, 500);
    g.drawString(text[pross], 110, 210);

  }

  @Override
  public void KeyPress ( int cod){
    if(KeyEvent.VK_ENTER == cod){
      wait=false;
    }
  }

  @Override
  public void KeyReleased ( int cod){
    if(pross==0) {
      if(cod==KeyEvent.VK_ENTER && wait){
        wait = false;
        if(tecl==teclado.length-1){
          if(pos>= name.length){
            pos= name.length-1;
          }else{
            pos--;
          }
          if(pos<0){
            pos=0;
          }
          name[pos]="";
        } else if (tecl == teclado.length - 2) {
          if(!name[0].equals("")){
            for (String s : name) {
              nome = nome.concat(s);
            }
            pross = 1;
          }
        }else{
          if(pos== name.length){return;}
          name[pos]=teclado[tecl];
          pos++;
        }
      }
      if (cod == KeyEvent.VK_ESCAPE) {
        StateManager.setState(StateManager.SELECT_SAVE);
      }
      if (cod == KeyEvent.VK_D || cod == KeyEvent.VK_RIGHT) {
        tecl += 1;
        if (tecl >= teclado.length) {
          tecl = 0;
        }
      }
      if (cod == KeyEvent.VK_A || cod == KeyEvent.VK_LEFT) {
        tecl -= 1;
        if (tecl < 0) {
          tecl = teclado.length - 1;
        }
      }
      if (cod == KeyEvent.VK_W || cod == KeyEvent.VK_UP) {
        tecl -= 21;
        if (tecl < 0)
          tecl = 4 * 21 + tecl;
        if (tecl >= teclado.length) {
          tecl = teclado.length - 1;
        }

      }
      if (cod == KeyEvent.VK_S || cod == KeyEvent.VK_DOWN) {
        tecl += 21;
        if (tecl >= teclado.length) {
          tecl = tecl - 4 * 21;
          if(tecl<0){
            tecl=teclado.length-1;
          }
        }
      }
    }
    if(pross==1){
      if(cod==KeyEvent.VK_ENTER && wait){
        Game.CLASSE=CLASSE;
        Jogador.criarJogador(true);
        StateManager.setState(StateManager.HISTORIA);
      }
      if(cod==KeyEvent.VK_A ||cod==KeyEvent.VK_LEFT){
        CLASSE--;
        if(CLASSE <0){
          CLASSE =clases.length-1;
        }
      }
      if(cod==KeyEvent.VK_D ||cod==KeyEvent.VK_RIGHT){
        CLASSE++;
        if(CLASSE >= classe.length){
          CLASSE =0;
        }
      }
      if(cod==KeyEvent.VK_ESCAPE){
        StateManager.setState(StateManager.CREATE_PLAYER);
      }
    }
  }

  @Override
  public void initFonte() {

  }
}