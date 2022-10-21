package br;

import br.display.Display;
import br.input.KeyManager;
import br.pixelfonte.Fontes;
import br.states.StateManager;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    public static int CLASSE;

    private final int inicio = StateManager.MENU;

    private final Display ds;
    public static final int WIDTH = 700, HIGHT = 700;
    public static int numSave = 0;
    public Thread thread;
    private boolean running = false;
    private final StateManager sm;
    private final KeyManager km;

    public Game(){

        ds = new Display("Text-RPG", WIDTH, HIGHT, "/sprites/icon.png");
        sm = new StateManager();
        km = new KeyManager();

        ds.setKeyListener(sm);
        ds.setKeyListener(km);
        StateManager.setState(inicio);

    }

    private void render(){
        BufferStrategy bs = ds.getBufferStrategy();
        if(bs == null){
          ds.createBufferStrategy();
          bs=ds.getBufferStrategy();
        }

        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HIGHT);
        if(StateManager.getStade() != null) {
            try {
                sm.render(g);
            } catch (Exception e) {
            }
        }
        g.dispose();
        bs.show();
    }
    private void update(){
        if(StateManager.getStade() == null) return;
        sm.update();
        km.update();
    }
    public synchronized void start() {
        if (thread != null) { return; }
        thread = new Thread(this);
        thread.start();
        running = true;
    }


    public synchronized void stop() {
        if(thread == null){return;}
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        Launch.carregando();
        int fps = 60;
        @SuppressWarnings("IntegerDivisionInFloatingPointContext") double tpt = 1000000000 / fps;
        double delta = 0;
        long now, last = System.nanoTime();
        ds.setVisible(true);
        while (running){
            now = System.nanoTime();
            delta+= (now-last)/tpt;
            last=now;

            if(delta>=1){
                render();
                update();
                delta--;
            }
        }
        stop();
    }
}
