package br;

// importes
import br.display.Display;
import br.states.StateManager;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    // Variaveis
    public static int CLASSE;
    private final Display ds; // janela
    public static final int WIDTH = 700, HIGHT = 700; // largura e altura da janela
    public static int numSave = 0; // numero do save, para o load game
    private Thread thread; // thread do game
    private boolean running = false; // jogo está em execução?
    private final StateManager sm; // telas do jogo


    // Construtor do Game
    public Game(){

        // janela do jogo
        ds = new Display("Text-RPG", WIDTH, HIGHT, "/sprites/icon.png");
        sm = new StateManager(); // telas do jogo, fases e etc

        // adiciona as teclas pressionadas nas telas, na janela, para controle do jogo
        ds.setKeyListener(sm);

        // Seleciona em qual tela o jogo começa
        StateManager.setState(StateManager.SELECT_SAVE);
    }

    // Renderiza os graficos do jogo na janela
    private void render(){

        // Pega ou cria um buffer para os graficos
        BufferStrategy bs = ds.getBufferStrategy();
        if(bs == null){
          ds.createBufferStrategy();
          bs=ds.getBufferStrategy();
        }

        // variavel grafica
        Graphics g = bs.getDrawGraphics();

        // limpeza da tela, dos graficos do frame anterior
        g.clearRect(0, 0, WIDTH, HIGHT);
        if(StateManager.getStade() != null) { try { sm.render(g);/* renderiza os graficos*/ } catch (Exception ignored){ } }

        // mostra os graficos na janela
        g.dispose();
        bs.show();
    }

    // Faz as atualizações do jogo
    private void update(){
        if(StateManager.getStade() == null) return;
        sm.update();
    }

    // começa o jogo em uma Thread, para um bom desempenho paralelo
    public synchronized void start() {
        if (thread != null) { return; }
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    // Finaliza o jogo e a Thread.
    private synchronized void stop() {
        if(thread == null){return;}
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    // execução do jogo // inspirado na unity o tempo de execução
    @Override
    public void run(){

        start(); // inicia o jogo
        Launch.carregando(); // finaliza tela de carregamento

        // limitador de fps
        int fps = 60;
        @SuppressWarnings("IntegerDivisionInFloatingPointContext")
        double tpt = 1000000000 / fps; // tiques por segundo ( a divisão de tempo de exibição por cada frame (limitado ao fds))
        double delta = 0; // variavel que contabiliza se houve o tempo nescessario do tpt

        // tempo de execução, verificar o tempo de inicio e fim do tpt
        long now, last = System.nanoTime();

        // deixa a janela do jogo visivel
        ds.setVisible(true);

        // execução por laço de execução
        while (running){

            now = System.nanoTime(); // define o tempo atual
            delta+= (now-last)/tpt; // acrece o delta, subtraindo o tempo atual, pelo anterior e dividindo pelo tpt, para travar o valor de fps
            last=now; // define o tempo anterior para ser igual ao atual dessa execução

            // verifica se o delta é suficiente para atualização de fps
            if(delta>=1){
                render(); // renderiza a janela
                update(); // atualiza as variaveis do jogo
                delta--; // diminui a um o delta
            }

        }
        stop(); // finaliza o jogo
    }
}
