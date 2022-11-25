package br.utilidades;

import br.Game;
import br.saves.LoadGame;

public class Utilidades {
    public static String calcTime(int i) {
        String tempo;
        int segundos = LoadGame.getTime(i);
        if (segundos < 60) {
            tempo = segundos + "s";
        } else if (segundos < 3600) {
            tempo = segundos / 60 + "m " + segundos % 60 + "s";
        } else if (segundos < 86400) {
            tempo = segundos / 3600 + "h " + (segundos % 3600) / 60 + "m " + segundos % 60 + "s";
        } else {
            tempo = segundos / 86400 + "d " + (segundos % 86400) / 3600 + "h " + (segundos % 3600) / 60 + "m " + segundos % 60 + "s";
        }

        return tempo;
    }

    public static int calTempo() {
        return (int)((Game.lastTime - Game.fistTime)/1000);
    }
}
