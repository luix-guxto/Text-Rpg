package br.moves;

public class Magicas {
    private int pontosDeUsoMax;
    private int pontosDeUso;
    String nomeMagica;

    public Magicas(int pontosDeUsoMax, String nomeMagica){
        pontosDeUso= this.pontosDeUsoMax = pontosDeUsoMax;
        this.nomeMagica = nomeMagica;
    }
    public void setPontosDeUso(int pontos){
        this.pontosDeUso = pontos;
    }

    public int getPontosDeUsoMax() {
        return pontosDeUsoMax;
    }

    public int getPontosDeUso() {
        return pontosDeUso;
    }

    public void useMagica() {
        this.pontosDeUso--;
    }

    public void lvlUp() {
        this.pontosDeUso=pontosDeUsoMax;
    }
    public void recPontos(int pontos) {
        this.pontosDeUso+=pontos;
    }

    public String getNomeMagica() {
        return nomeMagica;
    }
}
