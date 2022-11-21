package br.states;

import br.Game;
import br.pixelfonte.Fontes;
import br.player.Jogador;
import br.saves.Delete;
import br.saves.RankingLocal;
import br.sprites.ImageLoader;
import org.json.simple.JSONObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Calendar;

public class GameOver implements State{
    String scored;
    BufferedImage gameOver;
    int cl;
    @SuppressWarnings("unchecked")
    @Override
    public void init() {
        cl = 10;
        try{
            ImageLoader loader = new ImageLoader();
            gameOver = loader.loadImage("/sprites/gameover.png");
        }catch (Exception e){
            e.printStackTrace();
        }


        Delete delete = new Delete();

        int nv = Jogador.getNivel();
        int xp = Jogador.getXp();
        int bonus = Jogador.getDanoArma()+Jogador.getDanoBase();
        int andar = Jogador.getAndares();
        boolean boss = Jogador.getBossIsDead();
        int score = ((nv-1) * 100) + xp + (bonus * 50) + (andar * 10);
        if(boss){
            score *=2;
        }
        String nome = Jogador.getNome();
        Calendar data = Calendar.getInstance();
        int dia = data.get(Calendar.DAY_OF_MONTH);
        int mes = data.get(Calendar.MONTH);
        int ano = data.get(Calendar.YEAR);
        String dataString = dia+"/"+mes+"/"+ano;
        JSONObject scoreJson = new JSONObject();
        scoreJson.put("nome",nome);
        scoreJson.put("score", score);
        scoreJson.put("data",dataString);
        scoreJson.put("classe",Jogador.getClasse());
        scoreJson.put("nivel",nv);
        RankingLocal ranking = new RankingLocal();
        scored = nome+" Sua pontuacao final foi "+score+" pontos";
        ranking.salvarRanking(scoreJson);
        delete.deleteSave(Game.numSave);
    }

    @Override
    public void update() {
        cl--;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, Game.WIDTH, Game.HIGHT);
        g.drawImage(gameOver, Game.WIDTH/2- gameOver.getWidth()/2, Game.HIGHT/2- gameOver.getHeight()/2, gameOver.getWidth(), gameOver.getHeight(), null);
        g.setColor(Color.WHITE);
        g.setFont(Fontes.PIXEL.deriveFont(Font.PLAIN, 20));
        g.drawString(scored, Game.WIDTH/2-g.getFontMetrics().stringWidth(scored)/2, 100);
        g.setFont(Fontes.PIXEL.deriveFont(Font.BOLD, 25));
        g.drawString("Pressione ENTER ...", Game.WIDTH/2-g.getFontMetrics().stringWidth("Pressione ENTER ...")/2, 150);
    }

    @Override
    public void KeyPress(int cod) {

    }

    @Override
    public void KeyReleased(int cod) {
        if(cod == KeyEvent.VK_ENTER && cl<=0){
            StateManager.setState(StateManager.CREDITS);
        }
    }

    @Override
    public void initFonte() {

    }
}
