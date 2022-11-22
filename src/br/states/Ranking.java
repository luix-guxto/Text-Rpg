package br.states;
import br.Game;
import br.fontes.Fontes;
import br.pontuacao.Pontuacao;
import br.saves.RankingLocal;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("unused")
public class Ranking implements State{
    ArrayList<Pontuacao> ranking;
    @Override

    @SuppressWarnings("unchecked")
    public void init() {
        RankingLocal rankingLocal = new RankingLocal();
        ranking = new ArrayList<>();
        JSONArray rankinJson = rankingLocal.loadRanking();
        for (int i = 0; i<rankinJson.size(); i++){
            JSONParser parser = new JSONParser();
            JSONObject obj;
            try {
                obj = (JSONObject) parser.parse(rankinJson.get(i).toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            String nome = (String) obj.get("nome");
            int score = Integer.parseInt(obj.get("score").toString());
            String data = (String) obj.get("data");
            int classe = Integer.parseInt(obj.get("classe").toString());
            int nivel = Integer.parseInt(obj.get("nivel").toString());
            Pontuacao a = new Pontuacao(nome,score,data,classe,nivel);
            ranking.add(a);
        }
        Collections.sort(ranking);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0, Game.WIDTH, Game.HIGHT);
        g.setFont(Fontes.FERRUM.deriveFont(Font.BOLD, 35));
        g.setColor(Color.BLACK);
        g.drawString("<-[esc]", 5,50);

        g.setFont(Fontes.FERRUM.deriveFont(Font.BOLD, 23));
        g.drawString("| NOME |", 50, 100);
        g.drawString("| PONTUACAO |", 250, 100);
        g.drawString("| CLASSE |", 450, 100);
        g.setFont(Fontes.FERRUM.deriveFont(Font.PLAIN, 19));
        for (int i = 0; i < 15; i++) {

            int a = i + 1;
            String nome, pontuacao, classe;
                try{
                        nome = ranking.get(i).getNome() + " ";
                        pontuacao = ranking.get(i).getScore() + " ";
                        classe = ranking.get(i).getClasse() + " ";
                    }catch (Exception e){
                        nome = "...";
                        pontuacao = "...";
                        classe = "...";
                    }
            int xx = 150 +((g.getFontMetrics().getHeight()*i)+10);
            g.drawString(a+". ", 20, xx);
            g.drawString(nome, 50, xx);
            g.drawString(pontuacao, 250, xx);
            g.drawString(classe, 450, xx);
        }

        g.setFont(Fontes.PIXEL.deriveFont(Font.BOLD, 20));
        String str ="Pressione ENTER para abrir ranking online";
        g.drawString(str, Game.WIDTH/2-g.getFontMetrics().stringWidth(str)/2, 600);
        g.setColor(Color.RED);
        String str1 ="Pressione BACKSPACE para limpar o ranking local";
        g.drawString(str1, Game.WIDTH/2-g.getFontMetrics().stringWidth(str1)/2, 600+g.getFontMetrics().getHeight()+5);

    }

    @Override
    public void KeyPress(int cod) {
        
    }

    @Override
    public void KeyReleased(int cod) {
        if(cod == KeyEvent.VK_ESCAPE){
            StateManager.setState(StateManager.MENU1);
        }
        if(cod == KeyEvent.VK_ENTER){
            StateManager.setState(StateManager.RANKING_ONLINE);
        }
        if(cod == KeyEvent.VK_BACK_SPACE){
            RankingLocal rankingLocal = new RankingLocal();
            rankingLocal.clearRanking();
            ranking = new ArrayList<>();
            StateManager.setState(StateManager.RANKING);
        }
    }

    @Override
    public void initFonte() {

    }
}
