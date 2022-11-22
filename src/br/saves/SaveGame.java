package br.saves;

import br.mochila.Mochila;
import br.moves.Atacks;
import br.moves.Magicas;
import org.json.simple.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("ALL")
public class SaveGame {
    FileWriter save;

    public SaveGame(){
        File file = new File("./saves");
        try {
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e){e.printStackTrace();}
    }
    public void save(JSONObject jsonObject, int numSave){
        try{
            save = new FileWriter("./saves/"+numSave+".json");
            save.write(jsonObject.toJSONString());
            System.out.println("arquivo "+numSave+".json está salvo");
            System.out.println("\n"+jsonObject.toJSONString()+"\n");
            save.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // cria o objeto para salvar e inicia o save
    public void salvarJogo(boolean bossIsDead, int danoArma, int danoBase,int andares, int classe, String nome, int vida, int xp,int xpToUp, int level, Atacks[] atacks, Magicas[] magicas, int numSave, Mochila mochilas){
        // objetos json
        JSONObject obj = new JSONObject();
        JSONObject ataques = new JSONObject();
        JSONObject magics = new JSONObject();
        JSONObject mochila = new JSONObject();
        // criar objeto ataque
        for(int i = 0; i<atacks.length;i++){
            try {
                JSONObject a = new JSONObject();
                a.put("nome", atacks[i].getNomeAtaque());
                a.put("maxPontos", atacks[i].getPontosDeUsoMax());
                a.put("pontos", atacks[i].getPontosDeUso());
                a.put("dano", atacks[i].getDano());
                ataques.put(i, a);
            }catch (Exception ignored){}
        }
        // criar objeto magicas
        for(int i = 0; i<magicas.length;i++){
            try {
                JSONObject a = new JSONObject();
                a.put("nome", magicas[i].getNomeMagica());
                a.put("maxPontos", magicas[i].getPontosDeUsoMax());
                a.put("pontos", magicas[i].getPontosDeUso());
                a.put("tipo", magicas[i].getTipo());
                a.put("dano", magicas[i].getDano());
                magics.put(i, a);
            }catch (Exception ignored){}
        }
        mochila.put("lvlMochila", mochilas.getLvlMochila());
        mochila.put("pocoesPP", mochilas.getPocoesPP());
        mochila.put("pocoesVida", mochilas.getPocoesVida());
        mochila.put("couro", mochilas.getCouro());
        mochila.put("minerios", mochilas.getMinerios());
        mochila.put("flores", mochilas.getFlores());
        mochila.put("ervas", mochilas.getErvas());
        // adicionar para o save
        obj.put("andares", andares);
        obj.put("classe", classe);
        obj.put("nome", nome);
        obj.put("vida", vida);
        obj.put("xp", xp);
        obj.put("xpToUp", xpToUp);
        obj.put("level", level);
        obj.put("ataques", ataques);
        obj.put("magicas", magics);
        obj.put("mochila", mochila);
        obj.put("danoArma", danoArma);
        obj.put("danoBase", danoBase);
        obj.put("bossIsDead", bossIsDead);
        save(obj, numSave);
    }
}
