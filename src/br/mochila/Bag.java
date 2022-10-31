package br.mochila;

import br.Game;
import br.saves.LoadGame;

public class Bag {

    public static Mochila mochila;

    public static void initBag(boolean create){
        if(create){
            Bag.mochila = new Mochila(0, 0, 0, 0, 1, 1, 1);
        }
        else{
            Bag.mochila = LoadGame.getMochila(Game.numSave);
        }
    }

}
