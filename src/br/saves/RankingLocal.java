package br.saves;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class RankingLocal {
    public RankingLocal(){
        File file = new File("./saves");
        try {
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e){e.printStackTrace();}
    }
    @SuppressWarnings("unchecked")
    public void salvarRanking(JSONObject objJson) {
        JSONArray rankinJson = loadRanking();
        rankinJson.add(objJson);
        try {
            FileWriter save = new FileWriter("./saves/ranking.json");
            save.write(rankinJson.toJSONString());
            System.out.println("arquivo ranking.json está salvo");
            System.out.println("\n"+objJson.toJSONString()+"\n");
            save.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONArray loadRanking() {
        JSONParser parser = new JSONParser();
        JSONArray obj;
        try {
            obj = (JSONArray) parser.parse(new FileReader("./saves/ranking.json"));
        } catch (Exception ignored) {
            obj = new JSONArray();
        }
        return obj;
    }

    public void clearRanking() {
        try {
            FileWriter save = new FileWriter("./saves/ranking.json");
            save.write("[]");
            System.out.println("Ranking limpo");
            save.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
