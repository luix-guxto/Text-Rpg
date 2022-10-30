package br.moves;


public class Atacks {
    private final int pontosDeUsoMax;
    private int pontosDeUso;
    private final String nomeAtaque;
    private final int dano;

    public Atacks(int pontosDeUsoMax, String nomeAtaque, int dano){
        this.pontosDeUso = this.pontosDeUsoMax = pontosDeUsoMax;
        this.nomeAtaque = nomeAtaque;
        this.dano=dano;
    }

    @Override
    public String toString() {
        return "Atacks{" +
                "pontosDeUsoMax=" + pontosDeUsoMax +
                ", pontosDeUso=" + pontosDeUso +
                ", nomeAtaque='" + nomeAtaque + '\'' +
                ", dano=" + dano +
                '}';
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
        return nomeAtaque;
    }

    public int getDano() {
        return dano;
    }

}
