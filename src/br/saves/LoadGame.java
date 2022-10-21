package br.saves;

import br.Game;
import br.moves.Atacks;
import br.moves.Magicas;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class LoadGame {

    private static JSONObject obj;
    private static JSONParser parser;

    private static void load(int numSave){
        parser = new JSONParser();
        try {
            obj = (JSONObject) parser.parse(new FileReader("./saves/"+numSave+".json"));
        } catch (Exception e) {

        }
    }

    public static String getNome(int numSave){
        load(numSave);
        String nome = obj.get("nome").toString();
        return nome;
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
                                    ataque[i].get("nome").toString(),1);//alterar
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
            magicks[i] = new Magicas(Integer.parseInt(magica[i]
                    .get("maxPontos").toString()),
                    magica[i].get("nome").toString());
            magicks[i].setPontosDeUso(Integer.parseInt(magica[i]
                    .get("pontos").toString()));
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
}
