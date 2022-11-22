package br.pontuacao;


@SuppressWarnings("all")
public class Pontuacao implements Comparable{

    String nome;
    int score;
    String data;
    int classe;
    int nivel;

    public String getNome() {
        return nome;
    }
    public int getScore() {
        return score;
    }
    public String getData() {
        return data;
    }
    public String getClasse() {
        String clas;
        switch (classe){
            case 0:
                clas = "Elfo";
                break;
            case 1:
                clas = "Mago";
                break;
            case 2:
                clas = "Guerreiro";
                break;
            default:
                clas = "Nao definido";
                break;
        }
        return clas;
    }
    public int getNivel() {
        return nivel;
    }
    public Pontuacao(String nome, int score, String data, int classe, int nivel) {
        this.nome = nome;
        this.score = score;
        this.data = data;
        this.classe = classe;
        this.nivel = nivel;
    }
    @Override
    public String toString(){
        String clas;
        switch (classe){
            case 0:
                clas = "Elfo";
                break;
            case 1:
                clas = "Mago";
                break;
            case 2:
                clas = "Guerreiro";
                break;
            default:
                clas = "Nao definido";
                break;
        }
        return "Nome: "+nome+" Score: "+score+" Data: "+data+" Classe: "+clas+" Nivel: "+nivel;
    }
    @Override
    public int compareTo(Object p) {
        Pontuacao o = (Pontuacao) p;
        if(this.score > o.score) {
            return -1;
        }
        else if(this.score<o.score){
            return 1;
        }
        // se o score for igual
        else{
            if(this.nivel>o.nivel){
                return -1;
            } else if (this.nivel<o.nivel) {
                return 1;
            }
            // se o nivel for igual e o score tambem
            else {
                return Integer.compare(o.classe, this.classe);
            }
        }
    }
}
