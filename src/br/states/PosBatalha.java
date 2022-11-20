package br.states;

import br.Game;
import br.inimigos.Inimigo;
import br.mochila.Bag;
import br.pixelfonte.Fontes;
import br.player.Jogador;
import br.saves.SaveGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

@SuppressWarnings("unused")
public class PosBatalha implements State{

    private int xp, lvl, qtdItens, coolDown, xpUpper, xpToUp, xpInicial;
    private boolean unlockedMove, isBau;
    private String unlockedMoveName, nameItem;

    @Override
    public void init() {
        isBau = Inimigo.isInimigo();
        unlockedMove = false;
        unlockedMoveName = "";
        xpUpper = xpInicial = Jogador.getXp();
        coolDown = 10;
        xpToUp = Jogador.getXpToLvUp();
        xp = Inimigo.getXp();
        Jogador.addXp(xp);
        lvl = 0;
        while (Jogador.checkLvUp()){
            Jogador.lvUp();
            lvl++;
        }
        if(Jogador.unlockMoveOnUp()){
            unlockedMove = true;
            unlockedMoveName = Jogador.getMoveUnlocked();
        }
        int rn = new Random().nextInt(4);
        switch (rn){
            case 0:
                nameItem = "Ervas";
                qtdItens = new Random().nextInt(Jogador.getNivel())+1;
                Bag.mochila.addErvas(qtdItens);
                break;
            case 1:
                nameItem = "Flores";
                qtdItens = new Random().nextInt(Jogador.getNivel())+1;
                Bag.mochila.addFlores(qtdItens);
                break;
            case 2:
                nameItem = "Minerios";
                qtdItens = new Random().nextInt(Jogador.getNivel())+1;
                Bag.mochila.addMinerios(qtdItens);
                break;
            case 3:
                nameItem = "Couros";
                qtdItens = new Random().nextInt(Jogador.getNivel())+1;
                Bag.mochila.addCouro(qtdItens);
                break;
        }
        Jogador.addAndares();
        SaveGame sv = new SaveGame();
        sv.salvarJogo(Jogador.getBossIsDead(),Jogador.getDanoArma(), Jogador.getDanoBase(), Jogador.getAndares(), Jogador.getClasse(), Jogador.getNome(), Jogador.getVida(), Jogador.getXp(), Jogador.getXpToLvUp(), Jogador.getNivel(), Jogador.getAtacks(), Jogador.getMagicas(), Game.numSave, Bag.mochila);
        Inimigo.newInimigo();
    }

    @Override
    public void update() {
        coolDown--;
        xpUpper++;
        if(xpUpper>=xp+xpInicial){
            xpUpper = xp+xpInicial;
        } else if (xpUpper>=xpToUp){
            xpUpper = xpToUp;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(220, 255, 255));
        g.fillRect(0,0,Game.WIDTH,Game.HIGHT);


        g.setColor(Color.BLACK);
        g.setFont(Fontes.PIXEL.deriveFont(Font.BOLD, 28));
        String title;
        if(Inimigo.isInimigo()){
            title = "Recompensas do andar "+Jogador.getAndares();
        }else{
            title = "Recompensas do bau . Andar "+Jogador.getAndares();
        }
        g.drawString(title, Game.WIDTH/2-g.getFontMetrics().stringWidth(title)/2, 100);
        g.setFont(Fontes.PIXEL.deriveFont(Font.PLAIN, 20));
        // mostra o que ganhou
        int y = 380;
        if(isBau){
            g.drawString("Voce ganhou " + xp + " de XP", 50, y);
            y += 30;
            if (lvl == 1) {
                g.drawString("Voce subiu de nivel uma vez", 50, y);
                y += 30;
            } else if (lvl > 1) {
                g.drawString("Voce subiu de nivel " + lvl + " vezes", 50, y);
                y += 30;
            }
            if (unlockedMove) {
                y += 10;
                g.drawString("Voce aprendeu um novo move!", 50, y);
                y += 30;
                g.setColor(Color.RED);
                g.drawString(unlockedMoveName, 50, y);
                y += 40;
                g.setColor(Color.BLACK);
            }
        }
        g.drawString("Voce ganhou "+qtdItens+" "+nameItem, 50, y);


        //icon player
        int escala = 6;
        int posX = 650-Jogador.getSprite().getWidth()*escala;
        int posY = 400-Jogador.getSprite().getHeight()*escala;
        g.drawImage(Jogador.getSprite(), posX-50, posY, Jogador.getSprite().getWidth()*escala,Jogador.getSprite().getHeight()*escala,null);

        // barra xp
        int tamanho = 200;
        int xpBarra =(int) ((xpUpper*1.0/xpToUp)*tamanho);
        g.setColor(Color.BLACK);
        posX = posX - tamanho/2;
        posY = posY + Jogador.getSprite().getHeight()*escala + 10;
        g.fillRect(posX,posY,tamanho+10,20);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(posX+5,posY+5,tamanho,10);
        g.setColor(Color.GREEN);
        g.fillRect(posX+5,posY+5,xpBarra,10);
        g.setColor(Color.BLACK);
        posX = (posX + tamanho/2)-g.getFontMetrics().stringWidth("XP: "+xpUpper+"/"+xpToUp)/2;
        g.drawString("XP! "+xpUpper+" . "+xpToUp, posX, posY+45);


        // Prosseguir
        g.setFont(Fontes.PIXEL.deriveFont(Font.BOLD, 28));
        g.setColor(Color.BLACK);
        g.drawString("Pressione ENTER para andar...", Game.WIDTH/2-g.getFontMetrics().stringWidth("Pressione ENTER para andar...")/2, 620);
}

    @Override
    public void KeyPress(int cod) {

    }

    @Override
    public void KeyReleased(int cod) {
        if(cod == KeyEvent.VK_ENTER && coolDown <= 0){
            StateManager.setState(StateManager.PREBATALHA);
        }
    }

    @Override
    public void initFonte() {

    }
}
