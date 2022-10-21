package br.batalhas;

import br.inimigos.Inimigo;
import br.player.Jogador;

import java.awt.*;

public class Batalhas {


    public static void init(int lvlPlayer){
    }
    public static void turno(){

    }
    public static void render(Graphics g){

        Inimigo.render(g);
        Jogador.render(g);

    }
}
