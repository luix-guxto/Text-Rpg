# Documentacao

Documentacao do projeto Text RPG com exemplos de uso.

## Pacotes

- [br](#br "br")
- [background](#background "background")
- [batalhas](#batalhas "batalhas")
- [display](#display "display")
- [fontes](#fontes "fontes")
- [inimigos](#inimigos "inimigos")
- [mochila](#mochila "mochila")
- [moves](#moves "moves")
- [player](#player "player")
- [pontuacao](#pontuacao "pontuacao")
- [saves](#saves "saves")
- [sprites](#sprites "sprites")
- [states](#states "states")

## Classes

### br

- [Launch](#launch "Launch")
- [Game](#game "Game")

###### Launch

Classe main do jogo, responsável por iniciar o jogo.

###### Game

Classe principal do jogo, responsável por gerir os estados do jogo.

```java

package br;

import br.states.StateManager;
import br.display.Display;

public class Game implements Runnable {
    // variaveis
    public static final int WIDTH = 700, HEIGHT = 700; // largura e altura da janela
    public static final int numSave, CLASSE; // numero do save e classe do player
    private final Display ds; // display do jogo
    private final StateManager sm; // gerenciador de estados
    private Thread thread; // thread do jogo
    private boolean running; // se o jogo esta rodando
    
    // construtor
    public Game(String[] args){}
    
    // metodos
    private void render(){} // renderiza o jogo
    private void update(){} // atualiza o jogo
    private void stop(){} // para o jogo
    private void start(){} // inicia o jogo
    public void run(){} // execução o jogo
}
```

### background

###### Fundo

Classe responsável por aleatorizar o background do jogo.

```java
package br.background;

import java.awt.image.BufferedImage;

public class Fundo {
    private static final int[] x, y; // localização x e y de cada imagem dos fundos
    public static BufferedImage fundo; // imagem escolhida como fundo
    public static void newFundo(){} // define um novo fundo
}
```

### batalhas

##### Batalhas

Classe responsável por gerir as batalhas do jogo.

```java
package br.batalhas;

public class Batalhas{
    private static boolean inimigoAtacando = false, jogadorAtacando = false; // se o inimigo ou o jogador está atacando
    private static String[] options; // opções do menu
    private static int escolha = 0, tempoBatalha = 0, tempoCor = 0; // opcao escolhida, tempo de batalha e tempo de cor
    private static int danoPlayer, danoInimigo; // dano causado pelo player e inimigo
    private static String usedPlayer; // move usada pelo player
    private static boolean critPlayer, lossPlayer, critInimigo, lossInimigo; // se o ataque do player ou inimigo foi critico ou se o player ou inimigo perdeu o ataque
    private static Color corBorda; // cor da borda da janela
    
    public static void init(){} // inicializa as batalhas
    public static void iniAtac(){} // inicializa o ataque do inimigo
    public static void atacar(){} // ataca o inimigo
    public static void magica(){} // usa uma magia
    public static void teclas(int cod){} // verifica as teclas pressionadas
    public static void render(Graphics g){} // renderiza as batalhas
    
}
```

## display

###### Display

Classe responsável por criar a janela do jogo.

```java
package br.display;

import java.awt.image.BufferStrategy;

public class Display {
    private final JFrame frame; // janela do jogo
    private final Canvas canvas; // canvas do jogo
    // construtor
    public Display(String title, int width, int height, String pathIcon) {}
    
    // retorna o buffer strategy do canvas
    public BufferStrategy getBufferStrategy() {}
    
    // cria o buffer strategy do canvas
    public void createBufferStrategy() {}
    
    // adiciona um key listener ao jframe
    public void setKeyListener(KeyListener keyListener) {}
    
    // fecha a janela
    public void fechar() {}
    
    // define a visibilidade da janela
    public void setVisible(boolean visible) {}
}
```

## fontes

###### Fontes

Classe responsável por carregar as fontes do jogo.

```java
package br.fontes;

import java.awt.*;

public class Fontes {
    public static Font PIXEL, FERRUM, FANTASY; // fontes do jogo
    
    public static void fontInit(){} // inicializa as fontes
}
```

## inimigos

- [Enemy](#enemy "Enemy")
- [Inimigo](#inimigo "Inimigo")

###### Enemy

Interface responsável por definir os métodos que os inimigos devem ter.

```java
package br.inimigos;
import java.awt.image.BufferedImage;

public interface Enemy {
    String getNome(); // retorna o nome do inimigo
    BufferedImage[] getSprite(); // retorna o sprite do inimigo
    int atack(); // retorna o dano causado pelo inimigo
    int getLife(); // retorna a vida do inimigo
    int getMaxLife(); // retorna a vida máxima do inimigo
    void setDamage(int dano); // seta o dano causado pelo inimigo
    int getXp(); // retorna a quantidade de xp que o inimigo da
    String getNomeAtaque(); // retorna o nome do ataque do inimigo
    boolean temPontos(); // retorna se o inimigo tem pontos de utilizaçao do ataque
    int getNivel(); // retorna o nivel do inimigo
}
```

Exemplo de implementação da interface Enemy:
```java
package br.inimigos;

import br.moves.Atacks;

import java.awt.image.BufferedImage;

public class Inimigo implements Enemy{

    public Inimigo(int nv) {}

    @Override
    public String getNome() {}

    @Override
    public BufferedImage[] getSprite() {}

    @Override
    public int atack() {}

    @Override
    public int getLife() {}

    @Override
    public int getMaxLife() {}

    @Override
    public void setDamage(int dano) {}

    @Override
    public int getXp() {}

    @Override
    public String getNomeAtaque() {}

    @Override
    public boolean temPontos() {}

    @Override
    public int getNivel() {}
}
```

###### Inimigo

Classe responsável por gerir os inimigos do jogo.

```java
package br.inimigos;

import java.awt.*;

public class Inimigo {

    public static boolean dano = false; // se o inimigo está recebendo dano
    public static boolean frameCrescendo = false; // se o frame está a crescer
    public static boolean primeiroFrame = false; // se é o primeiro frame
    private static int delayAlteracaoFrame = 0; // delay para alterar o frame
    private static int frameDano = 2; // frame do dano
    private static final int escala = 2; // escala do inimigo
    private static final int locX = 80; // localização x do inimigo
    private static final int locY = 200; // localização y do inimigo
    private static boolean isInimigo; // se é um inimigo ou um Bau
    private static Enemy inimigo; // inimigo
    private static int xx, yy, llargura, aaltura; // localização x, y, largura e altura do quadro do nome do inimigo

    public static void newInimigo() {} // cria um inimigo
    public static void render(Graphics g) {} // renderiza o inimigo
    public static void icon(Graphics g){} // renderiza o icone do inimigo
    public static String getNome() {} // retorna o nome do inimigo
    public static BufferedImage[] getSprite() {} // retorna o sprite do inimigo
    public static int atack() {} // retorna o dano causado pelo inimigo
    public static int damage(int dan) {} // seta o dano que o inimigo recebe
    public static boolean temUso() {} // retorna se o inimigo tem pontos de utilizaçao do ataque
    public static String getNomeAtaque() {} // retorna o nome do ataque do inimigo
    public static int getXp() {} // retorna a quantidade de xp que o inimigo da
    public static int getVida() {} // retorna a vida do inimigo
    public static boolean isInimigo() {} // retorna se é um inimigo ou um Bau
}
```

## mochila

- [Bag](#bag "Bag")
- [Mochila](#mochila "Mochila")

###### Bag

Classe responsavel por carregar e deixar a mochila estatica

```java
package br.mochila;
public class Bag {
    public static Mochila mochila; // mochila do jogador
    public static void initBag(){} // inicializa a mochila, carregando ou criando
}
```

###### Mochila

Classe responsavel por gerir a mochila do jogador

```java
package br.mochila;
public class Mochila{
    public int  ervas, // usado para criar poções de vida
                flores,  // usado para criar poções de PP
                minerios, // usado para melhorar a arma
                couro, // usado para melhorar a mochila
                pocoesVida, // poções de vida
                pocoesPP, // poções de PP
                limitePocoes, // limite de poções que a mochila pode carregar
                lvlMochila; // nivel da mochila
    
    // construtor da mochila
    public Mochila(int ervas, int flores, int minerios, int couro, int pocoesVida, int pocoesPP, int lvlMochila) {} 
    
    public void addErvas(int qtd) {} // adiciona ervas a mochila
    public void addFlores(int qtd) {} // adiciona flores a mochila
    public void addMinerios(int qtd) {} // adiciona minerios a mochila
    public void addCouro(int qtd) {} // adiciona couro a mochila
    public void makePocaoVida() {} // cria uma poção de vida
    public void makePocaoPP() {} // cria uma poção de PP
    public void upMochila() {} // melhora a mochila
    public void upArma() {} // melhora a arma
    
    //getters
    public int getErvas() {}
    public int getFlores() {}
    public int getMinerios() {}
    public int getCouro() {}
    public int getPocoesVida() {}
    public int getPocoesPP() {}
    public int getLimitePocoes() {}
    public int getLvlMochila() {}
    
    // utilitarios
    public void usePocaoVida() {} // usa uma poção de vida
    public void usePocaoPP() {} // usa uma poção de PP
}
```

## moves

- [Atacks](#atacks "Atacks")
- [Magicas](#magicas "Magicas")

###### Atacks

Classe dos ataques

```java
package br.moves;

public class Atacks{
    private final int pontosDeUsoMax; // pontos de uso maximo do ataque
    private int pontosDeUso; // pontos de uso do ataque
    private final String nomeAtaque; // nome do ataque
    private final int dano; // dano do ataque
    
    // construtor do ataque
    public Atacks(String nomeAtaque, int dano, int pontosDeUsoMax) {}
    
    // getters
    public int getPontosDeUsoMax() {}
    public int getPontosDeUso() {}
    public String getNomeAtaque() {}
    public int getDano() {}
    
    // utilitarios
    public void useAtack() {} // usa o ataque
    
    public void setPontosDeUso(int pontos) {} // seta os pontos de uso do ataque
}
```

###### Magicas

Classe das magias

```java
package br.moves;
public class Magicas{
    private final int pontosDeUsoMax; // pontos de uso maximo da magia
    private int pontosDeUso; // pontos de uso da magia
    private final int dano; // dano da magia
    String nomeMagica; // nome da magia
    
    private final int tipo; // tipo da magia
    // tipo 1 = ataque,  2 = recuperação
    
    // construtor da magia
    public Magicas(String nomeMagica, int dano, int pontosDeUsoMax, int tipo) {}
    
    // getters
    public int getPontosDeUsoMax() {}
    public int getPontosDeUso() {}
    public String getNomeMagica() {}
    
    // utilitarios
    public void useMagica() {} // usa a magia
    
    public void setPontosDeUso(int pontos) {} // seta os pontos de uso da magia
}
```

## player

- [Player](#player "Player")
- [Jogador](#jogador "Jogador")

###### Player

Interface que define os metodos para a criacao de classes de jogadores

```java
package br.player;

import br.moves.*;
import java.awt.image.BufferedImage;

public interface Player {

    void setBossIsDead(); // seta que o boss foi derrotado
    void upLevel(); // sobe de nivel
    void setXp(int xp); // seta a quantidade de xp
    void setVida(int vida); // seta a vida
    void armaLvlUp(boolean up); // melhora a arma
    void init(boolean create); // inicializa o jogador
    void recPontoMagica(int rec, int choicc); // recupera pontos de magia
    void recPontoAtaque(int rec, int choicc); // recupera pontos de ataque
    
    boolean getBossIsDead(); // retorna se o boss foi derrotado
    boolean unlockMove(); // desbloqueia um ataque ou magia
    boolean canLvUp(); // retorna se pode subir de nivel
    
    String toString(); // retorna uma string com os dados do jogador
    String getNome(); // retorna o nome do jogador
    String getUnlockedMove(); // retorna o nome do ataque ou magia desbloqueado
    
    int getDanoBonus(); // retorna o dano bonus
    int getDanoArma(); // retorna o dano da arma
    int getDanoBase(); // retorna o dano base
    int useMagDan(int choice); // usa uma magia e retorna o dano
    int useAtack(int choice); // usa um ataque e retorna o dano
    int useMagicas(int choice);  // usa uma magia e retorna a recuperacao de vida
    int getLife(); // retorna a vida 
    int getMaxLife(); // retorna a vida maxima
    int getXp(); // retorna a quantidade de xp 
    int getLevel(); // retorna o nivel 
    int getXpToUp(); // retorna a quantidade de xp necessaria para subir de nivel
    int getLvlArma(); // retorna o nivel da arma
    int getClasse(); // retorna a classe do jogador
    
    Atacks[] getAtacks(); // retorna os ataques
    Magicas[] getMagicas(); // retorna as magias
    
    BufferedImage[] getSpritesBossLive(); // retorna os sprites do boss vivo
    BufferedImage getSprite(); // retorna o sprite do jogador
    
}
```

###### Jogador

Classe que controla o jogador

```java
package br.player;

import br.Game;
import br.mochila.Bag;
import br.moves.Atacks;
import br.moves.Magicas;
import br.fontes.Fontes;
import br.saves.LoadGame;
import br.saves.SaveGame;
import br.states.CreatePlayer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Jogador {
    // atributos
    private static int frameDano =0; // frame do dano
    private static int delayAlteracaoFrame =0; // delay para alterar o frame do dano
    private static int moveEscolhido = 0; // move escolhido
    private static int andares; // andares que o jogador ja andou
    private static final int locY=300; // localizacao y do jogador
    private static final int locX=500; // localizacao x do jogador
    private static final int escala = 4; // escala do jogador
    private static boolean dan = false; // se o jogador está a tomar dano
    private static boolean frameCrescendo = true; // se o frame do dano está a crescer
    private static Player player; // jogador
    private static String moveUnlocked; // move desbloqueado
    
    // getters
    public static Atacks getAtack() {} 
    public static Magicas getMagica() {}
    public static String getMoveUnlocked() {}
    public static String[] nomeAtaques(){}
    public static Atacks[] getAtacks(){}
    public static Magicas[] getMagicas(){}
    public static String[] nomeMagicas(){}
    public static BufferedImage getSprite(){}
    public static int getVida() {}
    public static int getPontosUso(int choce, boolean atac) {}
    public static int getPontosUsoMax(int choicc, boolean b) {}
    public static int getDano(int choicc, boolean b) {}
    public static int getVidaMax() {}
    public static Player getPlayer() {}
    public static int getNivel() {}
    public static String getNome() {}
    public static int getXp() {}
    public static int getXpToLvUp() {}
    public static int getClasse() {}
    public static int getDanoBase(){}
    public static int getDanoArma(){}
    public static int getAndares() {}
    public static boolean getBossIsDead() {}
    public static boolean isDanMag(int choicc) {}
    public static int getLvlArma() {}
    public static boolean checkLvUp(){}

    //utilitarios
    public static void criarJogador(boolean create){}
    public static boolean unlockMoveOnUp(){}
    public static void setMove(int choice){}
    public static int atacar(){}
    public static void receberDano (int dano){}
    public static boolean magDano() {}
    public static int useMagDan() {}
    public static int useMag() {}
    public static void recVida(int vida) {}
    public static void recPonto(boolean atac, int rec, int choicc) {}
    public static void upArma(boolean upArma) {}
    public static void addXp(int xp){}
    public static void lvUp(){}
    public static void addAndares() {}

    // render
    public static void icon(Graphics g, int x, int y, int escala){}
    public static void render (Graphics g){}
}

```

## pontuacao

###### Pontuacao

Classe que controla as pontuações

```java
package br.pontuacao;

@SuppressWarnings("all")
public class Pontuacao implements Comparable {

    // atributos
    String nome; // nome do jogador
    int score; // pontuação do jogador
    String data; // data da pontuação
    int classe; // classe do jogador
    int nivel; // nivel do jogador
    
    // Construtor
    public Pontuacao(String nome, int score, String data, int classe, int nivel) {}
    
    // getters
    public String getNome() {} // retorna o nome do jogador
    public int getScore() {} // retorna a pontuação do jogador
    public String getData() {} // retorna a data da pontuação
    public int getClasse() {} // retorna a classe do jogador
    public int getNivel() {} // retorna o nivel do jogador
    
    // utilitarios
    public int compareTo(Object p) {} // compara a pontuação com outra pontuação
    public String toString() {} // retorna a pontuação em string
}
```

## saves

- [Delete](#delete)
- [LoadGame](#loadgame)
- [SaveGame](#savegame)
- [RankingLocal](#rankinglocal)

###### Delete
 
Classe que apaga o ficheiro de save

```java
package br.saves;

import org.json.simple.JSONObject;

public class Delete {
    // utilitarios
    public void deleteSave(int numSave){} // apaga o ficheiro de save
}
```

###### LoadGame

Classe que carrega o jogo

```java
package br.saves;

import br.mochila.Mochila;
import br.moves.Atacks;
import br.moves.Magicas;
import org.json.simple.JSONObject;

public class LoadGame {

    private static JSONObject obj; // objeto json do file carregado

    // atualiza o objeto json do file carregado
    private static void load(int numSave){}
    
    // carrega os dados
    public static String getNome(int numSave){} // retorna o nome do jogador
    public static int getClasse(int i) {} // retorna a classe do jogador
    public static int getNv(int i) {} // retorna o nivel do jogador
    public static Atacks[] getAtacks(int numSave) {} // retorna os ataques do jogador
    public static Magicas[] getMagicas(int numSave) {} // retorna as magias do jogador
    public static int getLife(int i) {} // retorna a vida do jogador
    public static int getMaxLife(int numSave) {} // retorna a vida maxima do jogador
    public static Mochila getMochila(int numSave) {} // retorna a mochila do jogador
    public static int getXp(int numSave) {} // retorna a xp do jogador
    public static int getAndares(int numSave) {} // retorna os andares do jogador
    public static int getXpToUp(int numSave) {} // retorna a xp necessaria para upar
    public static int getDanoBase(int numSave) {} // retorna o dano base do jogador
    public static int getDanoArma(int numSave) {} // retorna o dano da arma do jogador
    public static boolean getBossIsDead(int numSave) {} // retorna se o boss está morto
}
```

###### SaveGame

Classe que guarda o jogo

```java
package br.saves;

import java.io.FileWriter;

@SuppressWarnings("ALL")
public class SaveGame {
    FileWriter save; // file writer do ficheiro de save

    public SaveGame() {} // construtor

    // salva o jogo
    public void save(JSONObject jsonObject, int numSave) {} 

    // cria o objeto para salvar e inicia o save
    public void salvarJogo(boolean bossIsDead, int danoArma, int danoBase, int andares, int classe, String nome, int vida, int xp, int xpToUp, int level, Atacks[] atacks, Magicas[] magicas, int numSave, Mochila mochilas) {}
}

```

###### RankingLocal

Classe que controla o ranking local

```java
package br.saves;

import org.json.simple.JSONArray;

public class RankingLocal {
    
    // construtor
    public RankingLocal(){}
    
    // utilitarios
    public void salvarRanking(JSONObject objJson) { } // salva o ranking
    public JSONArray loadRanking() {} // carrega o ranking
    public void clearRanking() {} // apaga o ranking
}
```

## sprites

- [ImageLoader](#imageloader)
- [SpriteSheet](#spritesheet)

###### ImageLoader

Classe que carrega as imagens

```java
package br.sprites;

public class ImageLoader {
    
    // retorna uma BufferedImage a partir de um caminho de arquivo
    public BufferedImage loadImage(String path) {}
}
```

###### SpriteSheet

Classe que corta sprites a partir de uma BufferedImage

```java
package br.sprites;
import 	java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage image; // imagem a ser cortada
	public 	SpriteSheet(BufferedImage image) {} // construtor que carrega a imagem a ser cortada
	public 	BufferedImage getSprite	(int x, int y, int width, int heigth) {} // retorna um sprite cortado
}
```

## states

- [State](#state)
- [StateManager](#statemanager)

###### State

Interface para a criação de estados do game

```java
package br.states;
import java.awt.*;
public interface State {

    // inicia a tela
    void init();

    // atualiza a tela
    void update();

    // renderiza a tela
    void render(Graphics g) throws Exception;

    // tecla precionada - retorno constante
    void KeyPress(int cod);

    // tecla desprecionada - unico retorno
    void KeyReleased(int cod);

    // inicia as fontes (usado somente no pre batalha
    void initFonte();
}
```

###### StateManager

Classe que gerencia os estados do game

```java
package br.states;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StateManager implements KeyListener {
    // total de estados existentes
    private static final int numberStates = 13;

    // array dos stados de jogo
    public static State[] states = new State[numberStates];

    // estado atual do jogo
    public static int currentState = 0;

    // numero do estado do jogo
    public  static final int
            MENU = 0, // menu
            MENU1=1, // menu 1
            RANKING=2, // ranking
            CREDITS=3, // creditos
            SELECT_SAVE=4, // seleção de save
            CREATE_PLAYER=5, // criação de jogador
            HISTORIA=6, // historia
            PREBATALHA=7, // pre batalha
            BATALHA=8, // batalha
            PAUSE=9, // pause
            POSBATALHA=10, // pos batalha
            GAME_OVER=11, // game over
            RANKING_ONLINE=12; // ranking online

    public StateManager(){} // construtor

    public static void setState(int state) {} // seta o estado atual do jogo
    public static State getStade() {} // retorna o estado atual do jogo
    public void update() {} // atualiza o estado atual do jogo
    public void render(Graphics g) {} // renderiza o estado atual do jogo
    
    // tecla precionada
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {} // tecla precionada

    @Override
    public void keyReleased(KeyEvent e) {} // tecla desprecionada
}
```

##### Exemplo de criação de um estado do game

```java
package br.states;

import java.awt.*;

public class Estado implements State{
    
    @Override
    public void init() {} // inicia o estado

    @Override
    public void update() {} // atualiza o estado

    @Override
    public void render(Graphics g) {} // renderiza o estado

    @Override
    public void KeyPress(int cod) {} // tecla precionada

    @Override
    public void KeyReleased(int cod) {} // tecla desprecionada

    @Override
    public void initFonte() {} // inicia as fontes
}
```

## Atalhos

- [Pacotes](#pacotes)
- [README](../README.md)
