package br.player;

import br.Game;
import br.moves.Atacks;
import br.moves.Magicas;
import br.saves.LoadGame;
import br.sprites.ImageLoader;
import br.sprites.SpriteSheet;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Elfo implements Player{
    private final Atacks[] atacks = new Atacks[4];
    private final Magicas[] magicas = new Magicas[4];
    private BufferedImage[] spritesBossLive, spritesBossDead;
    private int xp, level, xpToUp;
    private String unlockedMove;
    private int danoBase, danoArma, lvlArma;
    int life, maxLife;
    private boolean bossDead;

    public Elfo() {
        try {
            ImageLoader loader = new ImageLoader();
            BufferedImage image = loader.loadImage("/sprites/elfo.png");
            spritesBossLive = new BufferedImage[4];
            spritesBossLive[0]=new SpriteSheet(image).getSprite(1,151,16,24);
            spritesBossLive[1]=new SpriteSheet(image).getSprite(1,176,16,24);
            spritesBossLive[2]=new SpriteSheet(image).getSprite(1,201,16,24);
            spritesBossLive[3]=new SpriteSheet(image).getSprite(1,226,16,24);
            spritesBossDead = new BufferedImage[4];
            spritesBossDead[0]=new SpriteSheet(image).getSprite(1,51,16,24);
            spritesBossDead[1]=new SpriteSheet(image).getSprite(1,76,16,24);
            spritesBossDead[2]=new SpriteSheet(image).getSprite(1,101,16,24);
            spritesBossDead[3]=new SpriteSheet(image).getSprite(1,126,16,24);

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
            this.xpToUp+=new Random().nextInt(level)*10;
            danoBase += new Random().nextInt(3)+1;
            maxLife += new Random().nextInt(7)+1;
            life = maxLife;
            System.out.println("Level up!");
        }
    }

    @Override
    public void setXp(int xp) {
        this.xp=xp;
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
            danoArma += new Random().nextInt(3)+1;
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
        return this.xp;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getXpToUp() {
        return this.xpToUp;
    }

    @Override
    public String getNome() {
        return LoadGame.getNome(Game.numSave);
    }

    @Override
    public boolean unlockMove() {
        if(level >= 5 && atacks[2] == null){
                atacks[2] = new Atacks(5, "Chuva de Flechas", 20);
                unlockedMove = "Chuva de Flechas";
                return true;
        }
        else if(level >= 10 && magicas[1] == null){
                magicas[1] = new Magicas(1, "Flecha de Raios", 1, 30);
                unlockedMove = "Flecha de Raios";
               return true;
        }
        else if (level >= 20 && atacks[3] == null) {
                atacks[3] = new Atacks(1, "Flecha Flamejante", 30);
                unlockedMove = "Flecha Flamejante";
                return true;
        }
        else if (level >= 30 && magicas[2] == null) {
                magicas[2] = new Magicas(5, "Flecha de Gelo", 1, 30);
                unlockedMove = "Flecha de Gelo";
                return true;
        }
        else if (level >= 50 && magicas[3] == null) {
                magicas[3] = new Magicas(1, "Flecha Elemental", 1, 40);
                unlockedMove = "Flecha Elemental";
                return true;
        }
        else{
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
        if(create) {
            bossDead = false;
            danoBase=0;
            danoArma=0;
            lvlArma=0;
            life=maxLife=100;
            xp = 0;
            xpToUp = 100;
            level = 1;
            atacks[0] = new Atacks(25, "Atirar Flecha",10);
            atacks[1] = new Atacks(50, "Bater com o Arco",5);
            magicas[0] = new Magicas(5, "Recovery", 2, 20);
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
        return 0;
    }
}
