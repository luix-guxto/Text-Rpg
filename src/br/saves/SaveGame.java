package br.saves;

import br.mochila.Mochila;
import br.moves.Atacks;
import br.moves.Magicas;
import br.player.Jogador;
import br.utilidades.Utilidades;
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
            save.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // cria o objeto para salvar e inicia o save
    public void salvarJogo(int lvlArma, int maxLife, boolean bossIsDead, int danoArma, int danoBase,int andares, int classe, String nome, int vida, int xp,int xpToUp, int level, Atacks[] atacks, Magicas[] magicas, int numSave, Mochila mochilas){
        // objetos json
        JSONObject obj = new JSONObject();
        JSONObject ataques = new JSONObject();
        JSONObject magics = new JSONObject();
        JSONObject mochila = new JSONObject();
        // criar objeto ataque
        for(int i = 0; i<atacks.length;i++){
            try {
                JSONObject a = new JSONObject();
                a.put("fifjhbfhbbh", atacks[i].getNomeAtaque());
                a.put("56dd546d546", atacks[i].getPontosDeUsoMax());
                a.put("798f7ffdfdfge", atacks[i].getPontosDeUso());
                a.put("ldkflyhvf", atacks[i].getDano());
                ataques.put(i, a);
            }catch (Exception ignored){}
        }
        JSONObject atacD = new JSONObject(), atacE = new JSONObject(), atacF = new JSONObject();
        for(int i = 0; i<4;i++){
            try {
                JSONObject a = new JSONObject();
                a.put("fifjhbfhbbh", atacks[i].getNomeAtaque());
                a.put("56dd546d546", atacks[i].getPontosDeUsoMax());
                a.put("798f7ffdfdfge", atacks[i].getPontosDeUso());
                a.put("ldkflyhvf", atacks[i].getDano());
                atacD.put(i, a);
                atacE.put(i, a);
                atacF.put(i, a);
            }catch (Exception ignored){}
        }
        obj.put("uyfiuyf", atacD);
        obj.put("uyfiuyfuyfiuyf", atacE);
        obj.put("uyfiuyfuyfiuyfuyfiuyf", atacF);

        // criar objeto magicas
        for(int i = 0; i<magicas.length;i++){
            try {
                JSONObject a = new JSONObject();
                a.put("fifjhbfhbbh", magicas[i].getNomeMagica());
                a.put("56dd546d546", magicas[i].getPontosDeUsoMax());
                a.put("798f7ffdfdfge", magicas[i].getPontosDeUso());
                a.put("iugfoygf", magicas[i].getTipo());
                a.put("ldkflyhvf", magicas[i].getDano());
                magics.put(i, a);
            }catch (Exception ignored){}
        }

        JSONObject magiD = new JSONObject(), magiE = new JSONObject(), magiF = new JSONObject();
        for(int i = 0; i<4;i++){
            try {
                JSONObject a = new JSONObject();
                a.put("fifjhbfhbbh", magicas[i].getNomeMagica());
                a.put("56dd546d546", magicas[i].getPontosDeUsoMax());
                a.put("798f7ffdfdfge", magicas[i].getPontosDeUso());
                a.put("iugfoygf", magicas[i].getTipo());
                a.put("ldkflyhvf", magicas[i].getDano());
                magiD.put(i, a);
                magiE.put(i, a);
                magiF.put(i, a);
            }catch (Exception ignored){}
        }
        obj.put("gfpiugf", magiE);
        obj.put("gfpiugfgfpiugf", magiD);
        obj.put("gfpiugfgfpiugfgfpiugf", magiF);

        mochila.put("dfghghgdhdgh", mochilas.getLvlMochila());
        mochila.put("fghhgh", mochilas.getPocoesPP());
        mochila.put("ayhtyrteat", mochilas.getPocoesVida());
        mochila.put("68dvsdf564ffd", mochilas.getCouro());
        mochila.put("dsfg9+6", mochilas.getMinerios());
        mochila.put("84dff564f56", mochilas.getFlores());
        mochila.put("dfsdgfgsf", mochilas.getErvas());
        // adicionar para o save
        obj.put("fddfdsh", andares);
        obj.put("clajsljlj", classe);
        obj.put("jandjjj", nome);
        obj.put("dyudyg", vida);
        obj.put("jaihifug", xp);
        obj.put("asfddshgdjh", xpToUp);
        obj.put("je8uhugu", level);
        obj.put("879jhdljhf", ataques);
        obj.put("affgfgfghfhhkjlk", magics);
        obj.put("ewet465675674", mochila);
        obj.put("dfdstewrtrh", danoArma);
        obj.put("fgffhfrffhr4err", danoBase);
        obj.put("vafhush", maxLife);
        obj.put("fhdghdghg", bossIsDead);
        obj.put("uyfiuyfuyf", false);
        obj.put("uyfiuyfuyfiuyfuyfiuyfuyfiuyf", true);
        obj.put("syufduyfyf", (Jogador.getTempo()+Utilidades.calTempo()));
        obj.put("iugpoiugfp0g27272727", lvlArma);

        save(obj, numSave);
    }
}
