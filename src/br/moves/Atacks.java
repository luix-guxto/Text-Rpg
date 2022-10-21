package br.moves;


public class Atacks {
    private int pontosDeUsoMax;
    private int pontosDeUso;
    private String nomeAtaque;
    private int dano;

    public Atacks(int pontosDeUsoMax, String nomeAtaque, int dano){
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

    public int useAtack() {
        if(pontosDeUso>0) {
            this.pontosDeUso--;
            return dano;
        }else{return 0;}
    }
    public void setPontosDeUso(int pontos){
        this.pontosDeUso = pontos;
    }

    public void lvlUp() {
        this.pontosDeUso=pontosDeUsoMax;
    }
    public void recPontos(int pontos) {
        this.pontosDeUso+=pontos;
    }

    public String getNomeAtaque() {
        if(pontosDeUso>0) return nomeAtaque;
        else return "Ataque sem pontos de Uso.";
    }

}
