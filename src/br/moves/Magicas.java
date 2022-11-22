package br.moves;

public class Magicas {

    private final int pontosDeUsoMax;
    private int pontosDeUso;
    private final int dano;
    String nomeMagica;

    private final int tipo;
    // tipo 1 = ataque,  2 = recuperação

    public int getTipo() {
        return tipo;
    }
    public int getDano() {
        return dano;
    }



    public Magicas(int pontosDeUsoMax, String nomeMagica, int tipo, int dano){
        this.tipo = tipo;
        this.dano = dano;
        pontosDeUso= this.pontosDeUsoMax = pontosDeUsoMax;
        this.nomeMagica = nomeMagica;
    }


    public int getPontosDeUsoMax() {
        return pontosDeUsoMax;
    }
    public int getPontosDeUso() {
        return pontosDeUso;
    }
    public int useMagica() {
        if(pontosDeUso>0) {
            this.pontosDeUso--;
            return dano;
        }else{return 0;}
    }
    public String getNomeMagica() {
        return nomeMagica;
    }
    public void setPontosDeUso(int pontos) {
        this.pontosDeUso+=pontos;
        if(this.pontosDeUso>pontosDeUsoMax){
            this.pontosDeUso=pontosDeUsoMax;
        }
    }
}
