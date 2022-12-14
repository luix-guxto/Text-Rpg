package br.moves;


public class Atacks {
    private final int pontosDeUsoMax;
    private int pontosDeUso;
    private final String nomeAtaque;
    private final double dano;

    public Atacks(int pontosDeUsoMax, String nomeAtaque, double dano){
        this.pontosDeUso = this.pontosDeUsoMax = pontosDeUsoMax;
        this.nomeAtaque = nomeAtaque;
        this.dano=dano;
    }
    public int getPontosDeUsoMax() {
        return pontosDeUsoMax;
    }
    public int getPontosDeUso() {
        return pontosDeUso;
    }
    public double useAtack() {
        if(pontosDeUso>0) {
            this.pontosDeUso--;
            return dano;
        }else{return 0;}
    }
    public void setPontosDeUso(int pontos){
        this.pontosDeUso+=pontos;
        if(this.pontosDeUso>pontosDeUsoMax){
            this.pontosDeUso=pontosDeUsoMax;
        }
    }
    public String getNomeAtaque() {
        return nomeAtaque;
    }
    public double getDano() {
        return dano;
    }

}
