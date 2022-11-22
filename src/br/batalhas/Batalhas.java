package br.batalhas;

import br.Game;
import br.inimigos.Inimigo;
import br.fontes.Fontes;
import br.player.Jogador;
import br.states.StateManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Batalhas {

    private static boolean inimigoAtacando = false, jogadorAtacando = false;
    private static String[] options = {"ATAQUES", "MAGICAS", "MOCHILA", "DESISTIR"};
    private static int escolha = 0, tempoBatalha = 0, tempoCor = 0;
    private static int danoPlayer, danoInimigo;
    private static String usedPlayer;
    private static boolean critPlayer, lossPlayer, critInimigo, lossInimigo;
    private static Color corBorda;


    public static void init(){
        inimigoAtacando = false;
        corBorda = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
        options = new String[]{"ATAQUES", "MAGICAS", "MOCHILA", "DESISTIR"};
        usedPlayer = null;
        critPlayer= lossPlayer= critInimigo= lossInimigo = false;
        escolha = tempoBatalha = tempoCor = 0;
    }
    private static void iniAtac(){
        danoInimigo = Inimigo.ataque();
        inimigoAtacando = Inimigo.temUso();
        int chance = new Random().nextInt(100);
        if(chance>80){
            critInimigo=true;
            lossInimigo=false;
            danoInimigo*=2;
            Jogador.receberDano(danoInimigo);
        }else if(chance<10){
            critInimigo=false;
            lossInimigo=true;
            danoInimigo/=2;
            Jogador.receberDano(danoInimigo);
        }else{
            lossInimigo=critInimigo=false;
            Jogador.receberDano(danoInimigo);
        }
    }
    private static void atacar(){
        jogadorAtacando = true;
        danoPlayer=Jogador.atacar();
        int chance = new Random().nextInt(100);
        if(chance>80){
            critPlayer=true;
            lossPlayer=false;
            danoPlayer*=2;
            Inimigo.damage(danoPlayer);
        }else if(chance<10){
            critPlayer=false;
            lossPlayer=true;
            danoPlayer/=2;
            Inimigo.damage(danoPlayer);
        }else{
            lossPlayer=critPlayer=false;
            Inimigo.damage(danoPlayer);
        }
    }
    private static void magica(){
        jogadorAtacando =false;
        jogadorAtacando =Jogador.magDano();
        if(jogadorAtacando){
            danoPlayer=Jogador.useMagDan();
            int chance = new Random().nextInt(100);
            if(chance>80){
                critPlayer=true;
                lossPlayer=false;
                danoPlayer*=2;
                Inimigo.damage(danoPlayer);
            }else if(chance<10){
                critPlayer=false;
                lossPlayer=true;
                danoPlayer/=2;
                Inimigo.damage(danoPlayer);
            }else{
                lossPlayer=critPlayer=false;
                Inimigo.damage(danoPlayer);
            }
        }
        else{
            danoPlayer=Jogador.useMag();
            int chance = new Random().nextInt(100);
            if(chance>80){
                critPlayer=true;
                lossPlayer=false;
                danoPlayer*=2;
                Jogador.recVida(danoPlayer);
            }else if(chance<10){
                critPlayer=false;
                lossPlayer=true;
                danoPlayer/=2;
                Jogador.recVida(danoPlayer);
            }else{
                lossPlayer=critPlayer=false;
                Jogador.recVida(danoPlayer);
            }
        }
    }
    public static void teclas(int cod){

        /*Movimentação opcoes */ {
            if(cod== KeyEvent.VK_ESCAPE||cod==KeyEvent.VK_BACK_SPACE){
                if(tempoBatalha ==1|| tempoBatalha ==2){
                    options = new String[]{"ATAQUES", "MAGICAS", "MOCHILA", "DESISTIR"};
                    tempoBatalha =0;
                }
            }
            if (cod == KeyEvent.VK_LEFT || cod == KeyEvent.VK_A) {
                escolha--;
                if (escolha < 0) {
                    escolha = options.length - 1;
                }
            }
            if (cod == KeyEvent.VK_RIGHT || cod == KeyEvent.VK_D) {
                escolha++;
                if (escolha >= options.length) {
                    escolha = 0;
                }
            }
            if (cod == KeyEvent.VK_UP || cod == KeyEvent.VK_W) {
                escolha -= 2;
                if (escolha < 0) {
                    escolha += 4;
                }
            }
            if (cod == KeyEvent.VK_DOWN || cod == KeyEvent.VK_S) {
                escolha += 2;
                if (escolha >= options.length) {
                    escolha = escolha - options.length;
                }
            }
        }


        if(cod== KeyEvent.VK_ENTER){

            if(tempoBatalha == 0){
                switch (escolha){
                    case 0:
                        options=Jogador.nomeAtaques();
                        tempoBatalha =1;
                        return;
                    case 1:
                        options=Jogador.nomeMagicas();
                        tempoBatalha =2;
                        escolha =0;
                        return;
                    case 2:
                        escolha =0;
                        StateManager.setState(StateManager.PAUSE);
                        return;
                    case 3:
                        escolha =0;
                        Jogador.receberDano(Jogador.getVida());
                        tempoBatalha =8;
                        return;
                }
            }

            if(tempoBatalha == 1){
                if(Jogador.getAtacks()[escolha]!=null&&Jogador.getAtacks()[escolha].getPontosDeUso()>0){
                    Jogador.setMove(escolha);
                    usedPlayer=Jogador.getAtack().getNomeAtaque();
                    tempoBatalha =3;
                    escolha =0;
                    return;
                }
            }

            if(tempoBatalha == 2){
                if(Jogador.getMagicas()[escolha]!=null&&Jogador.getMagicas()[escolha].getPontosDeUso()>0){
                    Jogador.setMove(escolha);
                    usedPlayer=Jogador.getMagica().getNomeMagica();
                    tempoBatalha =4;
                    escolha =0;
                    return;
                }
            }

            if(tempoBatalha == 3){
                atacar();
                tempoBatalha =5;
                return;
            }

            if(tempoBatalha == 4){
                magica();
                tempoBatalha = 5;
                return;
            }

            if(tempoBatalha == 5){

                if(Inimigo.getVida()<=0){
                tempoBatalha = 7;
                return;
                }
                iniAtac();
                tempoBatalha =6;
                return;
            }

            if(tempoBatalha == 6){
                if(Jogador.getVida()<=0){
                    tempoBatalha =8;
                    return;
                }
                options=new String[]{"ATAQUES", "MAGICAS", "MOCHILA", "DESISTIR"};
                tempoBatalha = 0;
                return;
            }

            if(tempoBatalha == 7){
                StateManager.setState(StateManager.POSBATALHA);
                return;
            }

            if(tempoBatalha == 8){
                StateManager.setState(StateManager.GAME_OVER);
            }
        }
    }
    public static void render(Graphics g){

        tempoCor++;
        if(tempoCor >=20){
            tempoCor =0;
            corBorda =new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
        }
        g.setColor(corBorda); // cor borda
        int yy = 420;
        int yyy = 500;
        g.fillRect(0,yy,Game.WIDTH, Game.HIGHT-yy);
        g.setColor(new Color(255, 255, 255)); // cor fundo
        g.fillRect(10,yy+10,Game.WIDTH-20,Game.HIGHT-yy-20);
        Color corTexto = new Color(0,0,0); // cor texto
        Color corSelec = new Color(255, 0, 0); // cor selecionado
        g.setFont(Fontes.PIXEL.deriveFont(Font.BOLD, 18));

        // Ataque, Magica, Mochila, Desistir
        if(tempoBatalha == 0|| tempoBatalha == 2|| tempoBatalha == 1) {
            for (int i = 0; i < options.length; i++) {
                g.setColor(corTexto);
                if (escolha == i) {
                    g.setColor(corSelec);
                }
                int y = 500;
                int x = 30;
                if (i == 1) {
                    x = 230;
                }
                if (i == 2) {
                    y = 600;
                }
                if (i == 3) {
                    y = 600;
                    x = 230;
                }

                if(tempoBatalha ==1|| tempoBatalha ==2){
                    g.setFont(Fontes.PIXEL.deriveFont(Font.PLAIN, 14));
                }
                g.drawString(options[i], x, y);
                g.setFont(Fontes.PIXEL.deriveFont(Font.BOLD, 18));
                g.setColor(new Color(2, 52, 180));
                g.fillRect(430,yy+10,Game.WIDTH-430-10,Game.HIGHT-yy-20);
                g.setColor(corBorda);
                g.fillRect(430,yy,10,Game.HIGHT-yy);

                g.setColor(Color.WHITE);
                if(tempoBatalha ==0){
                    g.drawString("Selecione o que", 470,yy + 40);
                    g.drawString("deseja fazer", 470+(g.getFontMetrics().stringWidth("Selecione o que")/2)-g.getFontMetrics().stringWidth("deseja fazer")/2,yy + 65);

                    String clases = null;

                    switch (Game.CLASSE){
                        case 0:
                            clases = "Elfo";
                            break;
                        case 1:
                            clases = "Mago";
                            break;
                        case 2:
                            clases = "Guerreiro";
                            break;
                    }

                    g.drawString("CLASSE.   "+clases,460,yy+190);
                    g.drawString("NIVEL.      "+Jogador.getNivel(), 460, yy+220);
                    g.drawString("VIDA.         "+Jogador.getVida()+" ! "+Jogador.getVidaMax(), 460,yy+250);

                } else {

                    if(tempoBatalha == 1){
                        g.drawString("ATAQUES", 525,470);
                        g.drawString("Dano.  "+Jogador.getDano(escolha, true),450,600);
                    }else{
                        g.drawString("MAGIAS", 525,470);
                        if(!Jogador.isDanMag(escolha)){
                            g.drawString("Recuperar "+Jogador.getDano(escolha, false)+" de vida",450,600);
                        }else{
                            g.drawString("Dano.  "+Jogador.getDano(escolha, false),450,600);
                        }
                    }

                    g.drawString("Pontos de uso. "+Jogador.getPontosUso(escolha, tempoBatalha ==1)+" ! "+Jogador.getPontosUsoMax(escolha, tempoBatalha ==1),450,650);

                }
            }
        }

        //Voltar
        if(tempoBatalha == 1 || tempoBatalha == 2){
            g.setColor(Color.RED);
            g.setFont(Fontes.PIXEL.deriveFont(Font.PLAIN, 18));
            g.drawString("[ESC]_[BACKSPACE] __ VOLTAR", 20,Game.HIGHT-20);
        }


        g.setFont(Fontes.PIXEL.deriveFont(Font.BOLD, 22));
        // player usou
        if(tempoBatalha == 3 || tempoBatalha ==4){
            g.setColor(corTexto);
            g.drawString(Jogador.getNome()+" usou "+usedPlayer,50,yyy);

            // Continue
            g.setColor(corTexto);
            g.drawString("[ENTER]...", 500,yyy+150);
        }

        // player ataque
        if(tempoBatalha == 5 && jogadorAtacando){
            g.setColor(corTexto);

            if(critPlayer){
                g.drawString(Jogador.getNome()+" causou um critico!",50,yyy);
                g.drawString("causando "+danoPlayer+" de dano,", 50,yyy+50);
                g.drawString("no[A] "+Inimigo.getNome() ,50,yyy+100);
            } else if (lossPlayer) {
                g.drawString(Jogador.getNome()+" falhou no ataque!",50,yyy);
                g.drawString("causando somente "+danoPlayer+" de dano,", 50,yyy+50);
                g.drawString("no[A] "+Inimigo.getNome() ,50,yyy+100);
            } else{
                g.drawString(Jogador.getNome()+" causou "+danoPlayer+" de dano," ,50,yyy);
                g.drawString("no[A] "+Inimigo.getNome() ,50,yyy+50);
            }
            // Continue
            g.setColor(corTexto);
            g.drawString("[ENTER]...", 500,yyy+150);
        } else if (!jogadorAtacando && tempoBatalha ==5) {
            g.setColor(corTexto);

            if(critPlayer){
                g.drawString(Jogador.getNome()+" cura critica!",50,yyy);
                g.drawString("recuperando "+danoPlayer+" de vida", 50,yyy+50);
            } else if (lossPlayer) {
                g.drawString(Jogador.getNome()+" falhou na cura!",50,yyy);
                g.drawString("curando somente "+danoPlayer+" de vida", 50,yyy+50);
            } else{
                g.drawString(Jogador.getNome()+" curou "+danoPlayer+" de vida",50,yyy);
            }


            // Continue
            g.setColor(corTexto);
            g.drawString("[ENTER]...", 500,yyy+150);
        }

        // inimigo ataque
        if(tempoBatalha == 6){
            g.setColor(corTexto);

            if(inimigoAtacando) {
                if (critInimigo) {
                    g.drawString(Inimigo.getNome() + " usou o ataque " + Inimigo.getNomeAtaque(), 50, yyy);
                    g.drawString("Esse ataque causou dano critico de "+danoInimigo, 50, yyy+50);
                } else if (lossInimigo) {
                    g.drawString(Inimigo.getNome() + " usou o ataque " + Inimigo.getNomeAtaque(), 50, yyy);
                    g.drawString("Errou e causou somente o dano de "+danoInimigo, 50, yyy+50);
                } else {
                    g.drawString(Inimigo.getNome() + " usou o ataque " + Inimigo.getNomeAtaque(), 50, yyy);
                    g.drawString("Esse ataque causou dano de "+danoInimigo, 50, yyy+50);
                }
            }else {
                g.drawString(Inimigo.getNome() + " tentou te atacar",50,yyy);
                g.drawString( "mas falhou e causou 0 de dano",50,yyy+50);
            }



            // Continue
            g.setColor(corTexto);
            g.drawString("[ENTER]...", 500,yyy+150);
        }

        // venceu
        if(tempoBatalha == 7){
            g.setColor(corTexto);

            g.drawString(Inimigo.getNome()+" morreu!", 50,yyy);
            g.drawString("Voce ganhou "+Inimigo.getXp()+" de experiencia.", 50,yyy+50);

            // Continue
            g.setColor(corTexto);
            g.drawString("[ENTER]...", 500,yyy+150);
        }

        // morreu
        if(tempoBatalha == 8){
            g.setColor(corSelec);

            g.drawString(Jogador.getNome()+" morreu, acabou tudo...", 50,yyy);

            // Continue
            g.setColor(corTexto);
            g.drawString("[ENTER]...", 500,yyy+150);
        }

        Inimigo.render(g);
        Jogador.render(g);
    }
}
