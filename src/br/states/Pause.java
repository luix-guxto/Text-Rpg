package br.states;

import br.Game;
import br.background.Fundo;
import br.mochila.Bag;
import br.pixelfonte.Fontes;
import br.player.Jogador;
import br.sprites.ImageLoader;
import br.sprites.SpriteSheet;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class Pause implements State{

    private final BufferedImage[] classe = new BufferedImage[3];
    private BufferedImage menu, icon;
    private final int classeEscolhida = Game.CLASSE;
    private int choice = 0, opt = 1;
    private String[] options1;
    private String[] options2;
    private final String[] options3 = {"ATAQUES","MAGICAS","VOLTAR"};
    private final String[] options  = new String[5];


    @Override
    public void init() {

        opt = 1;
        choice = 0;
        try {
            ImageLoader loader = new ImageLoader();
            BufferedImage classes = loader.loadImage("/sprites/classes.png");

            for (int i = 0; i<3;i++){
                menu = loader.loadImage("/sprites/pause.png");
                classe[i] = new SpriteSheet(classes).getSprite((i*32)+1,0,30,32);
            }
        }catch (Exception ignored){}
        icon = classe[classeEscolhida];
    }

    @Override
    public void update() {
        options2 = new String[]{
                "Criar pocao de vida "+Bag.mochila.getErvas()+" ! 5",
                "Criar pocao de pp "+Bag.mochila.getFlores()+" ! 5",
                "Melhorar mochila "+Bag.mochila.getCouro()+" ! "+Integer.parseInt((Bag.mochila.getLvlMochila()-1)*3+5+""),
                "Melhorar arma "+Bag.mochila.getMinerios()+" ! "+Integer.parseInt(Jogador.getLvlArma()*3+5+""),
                "Voltar"};
        options1 = new String[]{
                "Usar Pocao de vida "+Bag.mochila.pocoesVida+" ! "+Bag.mochila.getLimitePocoes(),
                "Usar pocao de pp "+Bag.mochila.pocoesPP+" ! "+Bag.mochila.getLimitePocoes(),
                "Criacoes",
                "Sair"};
    }

    @Override
    public void render(Graphics g) {

        String titulo;

        g.setColor(Color.WHITE);
        g.fillRect(0,0,Game.WIDTH,Game.HIGHT);
        g.drawImage(Fundo.fundo,0,0, Game.WIDTH, 1173,null);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(10,262-110,(int) (30*2.8),(int) (32*2.85));
        g.drawImage(icon, 10,264-110, (int) (30*2.8),(int) (32*2.8), null);
        g.drawImage(menu, 10, Game.HIGHT / 2 - 200, 680, 376, null);

        g.setColor(Color.WHITE);
        g.setFont(Fontes.PIXEL.deriveFont(Font.PLAIN, 18));
        if (opt == 1){
            titulo = "PAUSE";
            g.drawString(titulo, 110, 210);
            for(int i = 0; i<options1.length;i++){
                if(i == choice){
                    g.setColor(Color.RED);
                }else{
                    g.setColor(Color.WHITE);
                }
                g.drawString(options1[i], 50, 300 + (i * 30));
            }
        }
        else if (opt == 2){
            titulo = "CRIAR ITENS";
            g.drawString(titulo, 110, 210);
            for(int i = 0; i<options2.length;i++){
                if(i == choice){
                    g.setColor(Color.RED);
                }else{
                    g.setColor(Color.WHITE);
                }
                g.drawString(options2[i], 50, 300 + (i * 30));
            }
        }
        else if (opt == 3){
            titulo = "RECUPERAR PONTOS DE USO";
            g.drawString(titulo, 110, 210);
            for(int i = 0; i<options3.length;i++){
                if(i == choice){
                    g.setColor(Color.RED);
                }else{
                    g.setColor(Color.WHITE);
                }
                g.drawString(options3[i], 50, 300 + (i * 30));
            }
        }
        else{
            titulo = "RECUPERAR PONTOS DE USO";
            g.drawString(titulo, 110, 210);
            for(int i = 0; i<options.length;i++){
                if(i == choice){
                    g.setColor(Color.RED);
                }else{
                    g.setColor(Color.WHITE);
                }
                g.drawString(options[i], 50, 300 + (i * 30));
            }
        }
    }

    @Override
    public void KeyPress(int cod) {

    }

    @Override
    public void KeyReleased(int cod) {

        if(cod == KeyEvent.VK_UP || cod == KeyEvent.VK_W){
            choice--;
            if(choice < 0 && opt==1) choice = options1.length-1;
            if(choice < 0 && opt==2) choice = options2.length-1;
            if(choice < 0 && opt==3) choice = options3.length-1;
            if(choice < 0 && (opt==4||opt==5)) choice = options.length-1;
        }
        if(cod == KeyEvent.VK_DOWN || cod == KeyEvent.VK_S){
            choice++;
            if(choice > options1.length-1 && opt==1) choice = 0;
            if(choice > options2.length-1 && opt==2) choice = 0;
            if(choice > options3.length-1 && opt==3) choice = 0;
            if(choice > options.length-1 && (opt==4||opt==5)) choice = 0;
        }
        if(cod == KeyEvent.VK_ENTER){
                 if(opt==1){
                switch (choice){
                    case 0:
                        if(Jogador.getVida() < Jogador.getVidaMax()){
                            if(Bag.mochila.getPocoesVida() > 0){
                                Jogador.recVida(Bag.mochila.lvlMochila*10);
                                Bag.mochila.usePocaoVida();
                            }
                        }
                        return;
                    case 1:
                        if(Bag.mochila.getPocoesPP() > 0) {
                            choice = 0;
                            opt = 3;
                        }
                        return;

                    case 2:
                        choice=0;
                        opt = 2;
                        return;
                    case 3:
                        StateManager.setState(StateManager.BATALHA);
                }
            }
            else if(opt == 2){
                switch (choice){
                    case 0:
                        if(Bag.mochila.getPocoesVida() < Bag.mochila.getLimitePocoes()){
                                Bag.mochila.makePocaoVida();
                        }
                        return;
                    case 1:
                        if(Bag.mochila.getPocoesPP() < Bag.mochila.getLimitePocoes()){
                            Bag.mochila.makePocaoPP();
                        }
                        return;
                    case 2:
                        Bag.mochila.upMochila();
                        return;
                    case 3:
                        Jogador.upArma(Bag.mochila.upArma(Jogador.getLvlArma()));
                        return;
                    case 4:
                        choice=0;
                        opt = 1;
                }
            }
            else if(opt == 3){
                switch (choice){
                    case 0:
                        for (int i = 0; i<4;i++){
                            options[i] = Jogador.nomeAtaques()[i];
                        }
                        options[4] = "Voltar";
                        choice=0;
                        opt = 4;
                        return;
                    case 1:
                        for (int i = 0; i<4;i++){
                            options[i] = Jogador.nomeMagicas()[i];
                        }
                        options[4] = "Voltar";
                        choice=0;
                        opt = 5;
                        return;
                    case 2:
                        choice=0;
                        opt = 1;
                }

            }
            else if(opt == 4){
                if(choice != 4){
                    if(Jogador.getAtacks()[choice]==null) return;
                    if(choice!=4 && Jogador.getAtacks()[choice].getPontosDeUso()<Jogador.getAtacks()[choice].getPontosDeUsoMax()){
                        Jogador.recPonto(true, Bag.mochila.getLvlMochila() * 2, choice);
                        Bag.mochila.usePocaoPP();
                        choice=0;
                        opt=1;
                    }
                }
                else {
                    choice=0;
                    opt=3;
                }
                 }
            else if(opt == 5){
                     if(choice != 4) {
                         if (Jogador.getMagicas()[choice] == null) return;
                         if (choice != 4 && Jogador.getMagicas()[choice].getPontosDeUso() < Jogador.getMagicas()[choice].getPontosDeUsoMax()) {
                             Jogador.recPonto(false, Bag.mochila.getLvlMochila() * 2, choice);
                             Bag.mochila.usePocaoPP();
                             choice = 0;
                             opt = 1;
                         }
                     }else {
                    choice=0;
                    opt=3;
                }
                 }
        }

    }

    @Override
    public void initFonte() {

    }
}
