package br.player;

import br.Game;
import br.moves.Atacks;
import br.moves.Magicas;
import br.saves.LoadGame;
import br.sprites.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Guerreiro implements Player{

    private final Atacks[] atacks = new Atacks[4];
    private final Magicas[] magicas = new Magicas[4];
    private BufferedImage[] spritesBossLive, spritesBossDead;
    private int xp, level, xpToUp;
    private String unlockedMove;
    private int danoBase, danoArma, lvlArma;
    int life, maxLife;
    private boolean bossDead;

    public Guerreiro(){
        try {
            ImageLoader loader = new ImageLoader();
            BufferedImage image = loader.loadImage("/sprites/guerreiro.png");
            spritesBossLive = new BufferedImage[4];
            spritesBossLive[0]=image.getSubimage(1,76,16,24);
            spritesBossLive[1]=image.getSubimage(152,1,16,24);
            spritesBossLive[2]=image.getSubimage(169,1,16,24);
            spritesBossLive[3]=image.getSubimage(186,1,16,24);
            spritesBossDead = new BufferedImage[4];
            spritesBossDead[0]=image.getSubimage(1,151,16,24);
            spritesBossDead[1]=image.getSubimage(1,226,16,24);
            spritesBossDead[2]=image.getSubimage(118,1,16,24);
            spritesBossDead[3]=image.getSubimage(18,18,16,24);

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
            this.xpToUp+=(new Random().nextInt(level)+1)*20;
            danoBase+=new Random().nextInt(3)+1;
            life = maxLife += new Random().nextInt(40)+1;

            for (Atacks atack : atacks) {
                if (atack != null) {
                    atack.setPontosDeUso(atack.getPontosDeUsoMax());
                }
            }
            for (Magicas magica : magicas) {
                if (magica != null) {
                    magica.setPontosDeUso(magica.getPontosDeUsoMax());
                }
            }
        }
    }

    @Override
    public void setXp(int xp) {
        this.xp=xp;
    }

    @Override
    public void setVida(double vida) {
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
            danoArma++;
        }
    }
    @Override
    public double useMagDan(int choice) {
        return magicas[choice].useMagica()*(danoArma+danoBase);
    }

    @Override
    public double useAtack(int choice) {
        return atacks[choice].useAtack()*(danoArma+danoBase);
    }

    @Override
    public double useMagicas(int choice) {
        return magicas[choice].useMagica()*(danoArma+danoBase);
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
            atacks[2] = new Atacks(5, "Slasher", 5);
            unlockedMove = "Slasher";
            return true;
        }
        else if(level >= 10 && magicas[1] == null){
            magicas[1] = new Magicas(10, "Recovery", 2, 5);
            unlockedMove = "Recovery";
            return true;
        }
        else if (level >= 20 && atacks[3] == null) {
            atacks[3] = new Atacks(3, "Fatiamento", 7.5);
            unlockedMove = "Fatiamento";
            return true;
        }
        else if (level >= 30 && magicas[2] == null) {
            magicas[2] = new Magicas(5, "Espada Congelante", 1, 7.5);
            unlockedMove = "Espada Congelante";
            return true;
        }
        else if (level >= 50 && magicas[3] == null) {
            magicas[3] = new Magicas(3, "Espada Elemental", 1, 12.5);
            unlockedMove = "Espada Elemental";
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
        if(create){
            bossDead = false;
            danoBase = danoArma = lvlArma = xp = 0;
            level = 1;
            xpToUp = 100;
            life = maxLife = 100;
            atacks[0] = new Atacks(15, "Espadada", 2.5);
            atacks[1] = new Atacks(20, "Soco", 1.25);
            magicas[0] = new Magicas(10, "Espada Flamejante", 1, 5);
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
        return 2;
    }
}
