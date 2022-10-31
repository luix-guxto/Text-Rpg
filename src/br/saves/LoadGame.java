package br.saves;

import br.mochila.Mochila;
import br.moves.Atacks;
import br.moves.Magicas;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
public class LoadGame {

    private static JSONObject obj;

    private static void load(int numSave){
        JSONParser parser = new JSONParser();
        try {
            obj = (JSONObject) parser.parse(new FileReader("./saves/"+numSave+".json"));
        } catch (Exception ignored) {

        }
    }

    public static String getNome(int numSave){
        load(numSave);
        return obj.get("nome").toString();
    }

    public static int getClasse(int i) {
        load(i);
        return Integer.parseInt(obj.get("classe").toString());
    }

    public static int getNv(int i) {
        load(i);
        return Integer.parseInt(obj.get("level").toString());
    }

    public static Atacks[] getAtacks(int numSave) {
        load(numSave);
        JSONObject ataques = (JSONObject) obj.get("ataques");
        JSONObject[] ataque = new JSONObject[ataques.size()];
        Atacks[] atacks = new Atacks[ataques.size()];
        for(int i = 0; i< ataques.size();i++){
            ataque[i] = (JSONObject) ataques.get(""+i);
            atacks[i] = new Atacks(Integer.parseInt(ataque[i]
                                    .get("maxPontos").toString()),
                                    ataque[i].get("nome").toString(),Integer.parseInt(ataque[i].get("dano").toString()));
            atacks[i].setPontosDeUso(Integer.parseInt(ataque[i]
                                    .get("pontos").toString()));
        }
        return atacks;
    }

    public static Magicas[] getMagicas(int numSave) {
        load(numSave);
        JSONObject magicas = (JSONObject) obj.get("magicas");
        JSONObject[] magica = new JSONObject[magicas.size()];
        Magicas[] magicks = new Magicas[magicas.size()];
        for(int i = 0; i< magicas.size();i++){
            magica[i] = (JSONObject) magicas.get(""+i);
            magicks[i] = new Magicas(Integer.parseInt(magica[i].get("maxPontos").toString()),
                                    magica[i].get("nome").toString(),
                                    Integer.parseInt(magica[i].get("tipo").toString()),
                                    Integer.parseInt(magica[i].get("dano").toString()));
                                    magicks[i].setPontosDeUso(Integer.parseInt(magica[i].get("pontos").toString()));
        }
        return magicks;
    }

    public static int getLife(int i) {
        load(i);
        return Integer.parseInt(obj.get("vida").toString());
    }

    public static int getMaxLife(int numSave) {
        load(numSave);
        int a = Integer.parseInt(obj.get("level").toString());
        return 100+((a-1)*9);
    }

    public static Mochila getMochila(int numSave) {
        load(numSave);
        JSONObject mochila = (JSONObject) obj.get("mochila");
        return new Mochila(
                Integer.parseInt(mochila.get("ervas").toString()),
                Integer.parseInt(mochila.get("flores").toString()),
                Integer.parseInt(mochila.get("minerios").toString()),
                Integer.parseInt(mochila.get("couro").toString()),
                Integer.parseInt(mochila.get("pocoesVida").toString()),
                Integer.parseInt(mochila.get("pocoesPP").toString()),
                Integer.parseInt(mochila.get("lvlMochila").toString()));
    }

}
