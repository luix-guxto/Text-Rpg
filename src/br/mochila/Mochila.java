package br.mochila;

public class Mochila {

    public int
            ervas, flores,
            minerios, couro,
            pocoesVida, pocoesPP,
            limitePocoes, lvlMochila;

                        // nome //id
    public Mochila(int ervas, //0
                   int flores, //1
                   int minerios, //2
                   int couro, //3
                   int pocoesVida, //4
                   int pocoesPP, //5
                   int lvlMochila) {
        this.ervas = ervas;
        this.flores = flores;
        this.minerios = minerios;
        this.couro = couro;
        this.pocoesVida = pocoesVida;
        this.pocoesPP = pocoesPP;
        this.lvlMochila = lvlMochila;
        limitePocoes = 1 + lvlMochila;
        if(this.pocoesPP> limitePocoes) this.pocoesPP = limitePocoes;
        if(this.pocoesVida> limitePocoes) this.pocoesVida = limitePocoes;
    }

    public void addErvas(int qtd) {
        this.ervas += qtd;
    }

    public void addFlores(int qtd) {
        this.flores += qtd;
    }

    public void addCouro(int qtd) {
        this.couro += qtd;
    }

    public void addMinerios(int qtd) {
        this.minerios += qtd;
    }

    public void makePocaoVida() {
        if(this.ervas >= 5 && this.pocoesVida < limitePocoes) {
            this.ervas -= 5;
            this.pocoesVida++;
        }
    }

    public void makePocaoPP() {
        if(this.flores >= 5 && this.pocoesPP < limitePocoes) {
            this.flores -= 5;
            this.pocoesPP++;
        }
    }

    public void upMochila(){
        if(this.couro >= (lvlMochila-1)*3+5){
            this.couro -= (lvlMochila-1)*3+5;
            this.lvlMochila++;
            limitePocoes = 1 + lvlMochila;
        }
    }

    public boolean upArma(int lvlArma){
        if(this.minerios >= (lvlArma)*3+5){
            this.minerios -= (lvlArma)*3+5;
            return true;
        }else {
            return false;
        }
    }

    // getters
    public int getErvas() {
        return ervas;
    }

    public int getFlores() {
        return flores;
    }

    public int getMinerios() {
        return minerios;
    }

    public int getCouro() {
        return couro;
    }

    public int getPocoesVida() {
        return pocoesVida;
    }

    public int getPocoesPP() {
        return pocoesPP;
    }

    public int getLimitePocoes() {
        return limitePocoes;
    }

    public int getLvlMochila() {
        return lvlMochila;
    }

    @Override
    public String toString() {
        return "Mochila{" +
                "ervas=" + ervas +
                ", flores=" + flores +
                ", minerios=" + minerios +
                ", couro=" + couro +
                ", pocoesVida=" + pocoesVida +
                ", pocoesPP=" + pocoesPP +
                ", limitePocoes=" + limitePocoes +
                ", lvlMochila=" + lvlMochila +
                '}';
    }

    public void usePocaoVida() {
        if(this.pocoesVida > 0) {
            this.pocoesVida--;
        }
    }

    public void usePocaoPP() {
        if(this.pocoesPP > 0) {
            this.pocoesPP--;
        }
    }
}
