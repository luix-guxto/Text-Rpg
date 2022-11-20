package br.player;

import br.Game;
import br.moves.Atacks;
import br.moves.Magicas;
import br.saves.LoadGame;
import br.sprites.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Mago implements Player{
    private final Atacks[] atacks = new Atacks[4];
    private final Magicas[] magicas = new Magicas[4];
    private BufferedImage[] spritesBossLive, spritesBossDead;
    private int xp, level, xpToUp;
    private String unlockedMove;
    private int danoBase, danoArma, lvlArma;
    int life, maxLife;
    private boolean bossDead;

    public Mago() {
        try {
            ImageLoader loader = new ImageLoader();
            BufferedImage image = loader.loadImage("/sprites/mago.png");
            spritesBossLive = new BufferedImage[4];
            spritesBossLive[0]=image.getSubimage(18,17,16,24);
            spritesBossLive[1]=image.getSubimage(52,17,16,24);
            spritesBossLive[2]=image.getSubimage(69,17,16,24);
            spritesBossLive[3]=image.getSubimage(86,17,16,24);
            spritesBossDead = new BufferedImage[4];
            spritesBossDead[0]=image.getSubimage(1,100,16,24);
            spritesBossDead[1]=image.getSubimage(52,17,16,24);
            spritesBossDead[2]=image.getSubimage(69,17,16,24);
            spritesBossDead[3]=image.getSubimage(86,17,16,24);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getDanoBonus() {
        return danoArma + danoBase;
    }

    @Override
    public int getDanoArma() {
        return danoArma;
    }

    @Override
    public int getDanoBase() {
        return danoBase;
    }

    @Override
    public boolean getBossIsDead() {
        return bossDead;
    }

    @Override
    public void setBossIsDead() {
        bossDead = true;
    }

    @Override
    public void upLevel() {
        if(getXp()>=getXpToUp()){
            setXp(getXp()-getXpToUp());
            this.level++;
            this.xpToUp+=new Random().nextInt(level)*11;
            danoBase += new Random().nextInt(2)+1;
            maxLife += new Random().nextInt(5)+1;
            life = maxLife;
            System.out.println("Level up!");
        }
    }

    @Override
    public void setXp(int xp) {
        this.xp = xp;
    }

    @Override
    public void setVida(int vida) {
        life+=vida;
        if(life<0){
            life=0;
        } else if (life>maxLife) {
            life=maxLife;
        }
    }

    @Override
    public void armaLvlUp(boolean up) {
        if(up){
            lvlArma++;
            danoArma += new Random().nextInt(2)+1;
        }
    }

    @Override
    public int useMagDan(int choice) {
        return magicas[choice].useMagica()+danoArma+danoBase;
    }

    @Override
    public int useAtack(int choice) {
        return atacks[choice].useAtack()+danoArma+danoBase;
    }

    @Override
    public int useMagicas(int choice) {
        return magicas[choice].useMagica()+danoBase+danoArma;
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public int getMaxLife() {
        return maxLife;
    }

    @Override
    public int getXp() {
        return xp;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getXpToUp() {
        return xpToUp;
    }

    @Override
    public String getNome() {
        return LoadGame.getNome(Game.numSave);
    }

    @Override
    public boolean unlockMove() {
        if (level >= 5 && atacks[2] == null){
            atacks[2] = new Atacks(10, "Cajadada de fogo", 20);
            unlockedMove = "Cajadada de fogo";
            return true;
        }
        else if(level >= 10 && magicas[1] == null){
           magicas[1] = new Magicas(5, "Recovery", 2, 20);
           unlockedMove = "Recovery";
           return true;
        }
        else if (level >= 20 && atacks[3] == null) {
            atacks[3] = new Atacks(5, "Cajado elemental", 30);
            unlockedMove = "Cajado elemental";
            return true;
        }
        else if (level >= 30 && magicas[2] == null){
            magicas[2] = new Magicas(5, "Feiche eletrico", 1, 30);
            unlockedMove = "Feiche eletrico";
            return true;
        }
        else if (level >= 50 && magicas[3] == null) {
            magicas[3] = new Magicas(1, "Magica Elemental", 1, 50);
            unlockedMove = "Magica Elemental";
            return true;
        } else{
            return false;
        }
    }

    @Override
    public Atacks[] getAtacks() {
        return atacks;
    }

    @Override
    public Magicas[] getMagicas() {
        return magicas;
    }

    @Override
    public BufferedImage[] getSpritesBossLive() {
        if(bossDead){
            return spritesBossDead;
        }else {
            return spritesBossLive;
        }
    }

    @Override
    public BufferedImage getSprite() {
        return spritesBossLive[0];
    }

    @Override
    public void init(boolean create) {
        if(create){
            bossDead = false;
            danoArma = danoBase = lvlArma = xp = 0;
            level = 1;
            xpToUp = 100;
            maxLife = life = 100;
            unlockedMove = null;
            atacks[0] = new Atacks(50, "Bater com cajado", 5);
            atacks[1] = new Atacks(25, "Pontada com cajado", 10);
            magicas[0] = new Magicas(10, "Bola de fogo", 1, 30);
        }else{
            for (int i = 0; i<4; i++) {
                try{
                    atacks[i] = LoadGame.getAtacks(Game.numSave)[i];
                }catch (Exception e){
                    atacks[i] = null;
                }
            }
            for (int i = 0; i<4; i++) {
                try{
                    magicas[i] = LoadGame.getMagicas(Game.numSave)[i];
                }catch (Exception e){
                    magicas[i] = null;
                }
            }
            life = LoadGame.getLife(Game.numSave);
            maxLife = LoadGame.getMaxLife(Game.numSave);
            level = LoadGame.getNv(Game.numSave);
            xp = LoadGame.getXp(Game.numSave);
            xpToUp = LoadGame.getXpToUp(Game.numSave);
            danoBase = LoadGame.getDanoBase(Game.numSave);
            danoArma = LoadGame.getDanoArma(Game.numSave);
            bossDead = LoadGame.getBossIsDead(Game.numSave);
        }
    }

    @Override
    public void recPontoMagica(int rec, int choicc) {
        magicas[choicc].setPontosDeUso(rec);
    }

    @Override
    public void recPontoAtaque(int rec, int choicc) {
        atacks[choicc].setPontosDeUso(rec);
    }

    @Override
    public int getLvlArma() {
        return lvlArma;
    }

    @Override
    public boolean canLvUp() {
        return getXp()>=getXpToUp();
    }

    @Override
    public String getUnlockedMove() {
        return unlockedMove;
    }

    @Override
    public int getClasse() {
        return 1;
    }
}
