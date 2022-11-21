package br.saves;

import org.json.simple.JSONObject;

public class Delete {
    public void deleteSave(int numSave){
        JSONObject obj = new JSONObject();
        SaveGame saveGame = new SaveGame();
        saveGame.save(obj,numSave);
    }
}
