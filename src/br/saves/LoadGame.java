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
        try{
            return obj.get("jandjjj").toString();
        }catch (Exception ignored){
            return null;
        }
    }

    public static int getClasse(int i) {
        load(i);
        return Integer.parseInt(obj.get("clajsljlj").toString());
    }

    public static int getNv(int i) {
        load(i);
        return Integer.parseInt(obj.get("je8uhugu").toString());
    }

    public static Atacks[] getAtacks(int numSave) {
        load(numSave);
        JSONObject ataques = (JSONObject) obj.get("879jhdljhf"); // pega o objeto ataque
        JSONObject[] ataque = new JSONObject[ataques.size()];
        Atacks[] atacks = new Atacks[ataques.size()];
        for(int i = 0; i< ataques.size();i++){
            ataque[i] = (JSONObject) ataques.get(""+i);
            atacks[i] = new Atacks(Integer.parseInt(ataque[i]
                                    .get("56dd546d546").toString()), // pontos de uso max
                                    ataque[i].get("fifjhbfhbbh").toString(), // nome
                    Double.parseDouble(ataque[i].get("ldkflyhvf").toString())); // dano
            atacks[i].setPontosDeUso(Integer.parseInt(ataque[i]
                                    .get("798f7ffdfdfge").toString())); // pontos de uso
        }
        return atacks;
    }

    public static Magicas[] getMagicas(int numSave) {
        load(numSave);
        JSONObject magicas = (JSONObject) obj.get("affgfgfghfhhkjlk"); // magicas
        JSONObject[] magica = new JSONObject[magicas.size()];
        Magicas[] magicks = new Magicas[magicas.size()];
        for(int i = 0; i< magicas.size();i++){
            magica[i] = (JSONObject) magicas.get(""+i);
            magicks[i] = new Magicas(Integer.parseInt(magica[i].get("56dd546d546").toString()), // pontos de uso max
                                    magica[i].get("fifjhbfhbbh").toString(), // nome
                                    Integer.parseInt(magica[i].get("iugfoygf").toString()), // tipo
                                    Double.parseDouble(magica[i].get("ldkflyhvf").toString())); // dano
                                    magicks[i].setPontosDeUso(Integer.parseInt(magica[i].get("798f7ffdfdfge").toString())); // pontos de uso
        }
        return magicks;
    }

    public static int getLife(int i) {
        load(i);
        return Integer.parseInt(obj.get("dyudyg").toString());
    }

    public static int getMaxLife(int numSave) {
        load(numSave);
        return Integer.parseInt(obj.get("vafhush").toString());
    }

    public static int getTime(int numSave) {
        load(numSave);
        try{
            return Integer.parseInt(obj.get("syufduyfyf").toString());
        }catch (NullPointerException e){
            return 0;
        }
    }

    public static Mochila getMochila(int numSave) {
        load(numSave);
        JSONObject mochila = (JSONObject) obj.get("ewet465675674");
        return new Mochila(
                Integer.parseInt(mochila.get("dfsdgfgsf").toString()), // ervas
                Integer.parseInt(mochila.get("84dff564f56").toString()), // flores
                Integer.parseInt(mochila.get("dsfg9+6").toString()), // minerios
                Integer.parseInt(mochila.get("68dvsdf564ffd").toString()), // couro
                Integer.parseInt(mochila.get("ayhtyrteat").toString()), // pocoes vida
                Integer.parseInt(mochila.get("fghhgh").toString()), // pocoes pp
                Integer.parseInt(mochila.get("dfghghgdhdgh").toString())); // lvl
    }

    public static int getXp(int numSave) {
        load(numSave);
        return Integer.parseInt(obj.get("jaihifug").toString());
    }

    public static int getAndares(int numSave) {
        load(numSave);
        return Integer.parseInt(obj.get("fddfdsh").toString());
    }

    public static int getXpToUp(int numSave) {
        load(numSave);
        return Integer.parseInt(obj.get("asfddshgdjh").toString());
    }

    public static int getDanoBase(int numSave) {
        load(numSave);
        return Integer.parseInt(obj.get("fgffhfrffhr4err").toString());
    }

    public static int getDanoArma(int numSave) {
        load(numSave);
        return Integer.parseInt(obj.get("dfdstewrtrh").toString());
    }

    public static boolean getBossIsDead(int numSave) {
        load(numSave);
        return Boolean.parseBoolean(obj.get("fhdghdghg").toString());
    }

    public static int getLvlArma(int numSave) {
        load(numSave);
        return Integer.parseInt(obj.get("iugpoiugfp0g27272727").toString());
    }
}
