package br.saves;
import br.Game;
import br.moves.Atacks;
import br.moves.Magicas;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    private void save(JSONObject jsonObject, int numSave){

        try{
            save = new FileWriter("./saves/"+numSave+".json");
            save.write(jsonObject.toJSONString());
            System.out.println("arquivo "+numSave+".json está salvo");
            save.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // cria o objeto para salvar e inicia o save
    public void salvarJogo(int classe,String nome, int vida, int mana, int xp, int level, Atacks[] atacks, Magicas[] magicas, int numSave){

        // objetos json
        JSONObject obj = new JSONObject();
        JSONObject ataques = new JSONObject();
        JSONObject magics = new JSONObject();

        // criar objeto ataque
        for(int i = 0; i<atacks.length;i++){
            JSONObject a = new JSONObject();
            a.put("nome", atacks[i].getNomeAtaque());
            a.put("maxPontos", atacks[i].getPontosDeUsoMax());
            a.put("pontos", atacks[i].getPontosDeUso());
            ataques.put(i, a);
        }

        // criar objeto magicas
        for(int i = 0; i<magicas.length;i++){
            JSONObject a = new JSONObject();
            a.put("nome", magicas[i].getNomeMagica());
            a.put("maxPontos", magicas[i].getPontosDeUsoMax());
            a.put("pontos", magicas[i].getPontosDeUso());
            magics.put(i, a);
        }

        // adicionar para o save
        obj.put("classe", classe);
        obj.put("nome", nome);
        obj.put("vida", vida);
        obj.put("mana", mana);
        obj.put("xp", xp);
        obj.put("level", level);
        obj.put("ataques", ataques);
        obj.put("magicas", magics);


        save(obj, numSave);
    }
}
