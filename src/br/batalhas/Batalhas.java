package br.batalhas;

import br.Game;
import br.inimigos.Inimigo;
import br.pixelfonte.Fontes;
import br.player.Jogador;
import br.states.StateManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Batalhas {

    private static boolean atttt = false, dan = false;

    private static String[] options = {"ATAQUES", "MAGICAS", "MOCHILA", "DESISTIR"};
    private static int choicc = 0, optt = 0, corrr = 0;
    private static int danoPlayer, danoInimigo;

    private static String usedPlayer;
    private static boolean critPlayer, lossPlayer, critInimigo, lossInimigo;
    private static Color corzin;


    public static void init(){
        atttt = false;
        corzin = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
        options = new String[]{"ATAQUES", "MAGICAS", "MOCHILA", "DESISTIR"};
        usedPlayer = null;
        critPlayer= lossPlayer= critInimigo= lossInimigo = false;
        choicc = optt = corrr = 0;
    }

    private static void iniAtac(){
        danoInimigo = Inimigo.ataque();
        atttt = Inimigo.temUso();
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
        dan = true;
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
        dan=false;
        dan=Jogador.magDano();
        if(dan){
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
                if(optt==1||optt==2){
                    options = new String[]{"ATAQUES", "MAGICAS", "MOCHILA", "DESISTIR"};
                    optt=0;
                }
            }
            if (cod == KeyEvent.VK_LEFT || cod == KeyEvent.VK_A) {
                choicc--;
                if (choicc < 0) {
                    choicc = options.length - 1;
                }
            }
            if (cod == KeyEvent.VK_RIGHT || cod == KeyEvent.VK_D) {
                choicc++;
                if (choicc >= options.length) {
                    choicc = 0;
                }
            }
            if (cod == KeyEvent.VK_UP || cod == KeyEvent.VK_W) {
                choicc -= 2;
                if (choicc < 0) {
                    choicc += 4;
                }
            }
            if (cod == KeyEvent.VK_DOWN || cod == KeyEvent.VK_S) {
                choicc += 2;
                if (choicc >= options.length) {
                    choicc = choicc - options.length;
                }
            }
        }


        if(cod== KeyEvent.VK_ENTER){

            if(optt == 0){
                switch (choicc){
                    case 0:
                        options=Jogador.nomeAtaques();
                        optt=1;
                        return;
                    case 1:
                        options=Jogador.nomeMagicas();
                        optt=2;
                        choicc=0;
                        return;
                    case 2:
                        choicc=0;
                        StateManager.setState(StateManager.PAUSE);
                        return;
                    case 3:
                        choicc=0;
                        optt=8;
                        return;
                }
            }

            if(optt == 1){
                if(Jogador.getAtacks()[choicc]!=null&&Jogador.getAtacks()[choicc].getPontosDeUso()>0){
                    Jogador.setMove(choicc);
                    usedPlayer=Jogador.getAtack().getNomeAtaque();
                    optt=3;
                    choicc=0;
                    return;
                }
            }

            if(optt == 2){
                if(Jogador.getMagicas()[choicc]!=null&&Jogador.getMagicas()[choicc].getPontosDeUso()>0){
                    Jogador.setMove(choicc);
                    usedPlayer=Jogador.getMagica().getNomeMagica();
                    optt=4;
                    choicc=0;
                    return;
                }
            }

            if(optt == 3){
                atacar();
                optt=5;
                return;
            }

            if(optt == 4){
                magica();
                optt = 5;
                return;
            }

            if(optt == 5){

                if(Inimigo.getVida()<=0){
                optt = 7;
                return;
                }
                iniAtac();
                optt=6;
                return;
            }

            if(optt == 6){
                if(Jogador.getVida()<=0){
                    optt=8;
                    return;
                }
                options=new String[]{"ATAQUES", "MAGICAS", "MOCHILA", "DESISTIR"};
                optt = 0;
                return;
            }

            if(optt == 7){
                StateManager.setState(StateManager.PREBATALHA);
                return;
            }

            if(optt == 8){
                StateManager.setState(StateManager.GAME_OVER);
            }
        }
    }


    public static void render(Graphics g){

        corrr++;
        if(corrr>=20){
            corrr=0;
            corzin=new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
        }
        g.setColor(corzin); // cor borda
        int yy = 420;
        int yyy = 500;
        g.fillRect(0,yy,Game.WIDTH, Game.HIGHT-yy);
        g.setColor(new Color(255, 255, 255)); // cor fundo
        g.fillRect(10,yy+10,Game.WIDTH-20,Game.HIGHT-yy-20);
        Color corTexto = new Color(0,0,0); // cor texto
        Color corSelec = new Color(255, 0, 0); // cor selecionado
        g.setFont(Fontes.PIXEL.deriveFont(Font.BOLD, 18));

        // Ataque, Magica, Mochila, Desistir
        if(optt == 0||optt == 2||optt == 1) {
            for (int i = 0; i < options.length; i++) {
                g.setColor(corTexto);
                if (choicc == i) {
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

                if(optt==1||optt==2){
                    g.setFont(Fontes.PIXEL.deriveFont(Font.PLAIN, 14));
                }
                g.drawString(options[i], x, y);
                g.setFont(Fontes.PIXEL.deriveFont(Font.BOLD, 18));
                g.setColor(new Color(2, 52, 180));
                g.fillRect(430,yy+10,Game.WIDTH-430-10,Game.HIGHT-yy-20);
                g.setColor(corzin);
                g.fillRect(430,yy,10,Game.HIGHT-yy);

                g.setColor(Color.WHITE);
                if(optt==0){
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

                    if(optt == 1){
                        g.drawString("ATAQUES", 525,470);
                        g.drawString("Dano.  "+Jogador.getDano(choicc, true),450,600);
                    }else{
                        g.drawString("MAGIAS", 525,470);
                        if(!Jogador.isDanMag(choicc)){
                            g.drawString("Recuperar "+Jogador.getDano(choicc, false)+" de vida",450,600);
                        }else{
                            g.drawString("Dano.  "+Jogador.getDano(choicc, false),450,600);
                        }
                    }

                    g.drawString("Pontos de uso. "+Jogador.getPontosUso(choicc, optt==1)+" ! "+Jogador.getPontosUsoMax(choicc, optt==1),450,650);

                }
            }
        }

        //Voltar
        if(optt == 1 || optt == 2){
            g.setColor(Color.RED);
            g.setFont(Fontes.PIXEL.deriveFont(Font.PLAIN, 18));
            g.drawString("[ESC]_[BACKSPACE] __ VOLTAR", 20,Game.HIGHT-20);
        }


        g.setFont(Fontes.PIXEL.deriveFont(Font.BOLD, 22));
        // player usou
        if(optt == 3 || optt==4){
            g.setColor(corTexto);
            g.drawString(Jogador.getNome()+" usou "+usedPlayer,50,yyy);

            // Continue
            g.setColor(corTexto);
            g.drawString("[ENTER]...", 500,yyy+150);
        }

        // player ataque
        if(optt == 5 && dan){
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
        } else if (!dan && optt==5) {
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
        if(optt == 6){
            g.setColor(corTexto);

            if(atttt) {
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
        if(optt == 7){
            g.setColor(corTexto);

            g.drawString(Inimigo.getNome()+" morreu!", 50,yyy);
            g.drawString("Voce ganhou "+Inimigo.getXp()+" de experiencia.", 50,yyy+50);

            // Continue
            g.setColor(corTexto);
            g.drawString("[ENTER]...", 500,yyy+150);
        }

        // morreu
        if(optt == 8){
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
