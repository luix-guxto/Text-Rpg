package br                                                                      ;

import br.display.Display                                                       ;
import br.states.StateManager                                                   ;
import java.awt.*                                                               ;
import java.awt.image.BufferStrategy                                            ;

public class Game implements Runnable
{

    public static final int             WIDTH       = 700                       ,
                                        HIGHT       = 700                       ;
    public static       int             numSave     = 0                         ,
                                        CLASSE      = 0                         ;

    private       final Display         ds                                      ;
    private       final StateManager    sm                                      ;
    private             Thread          thread                                  ;
    private             boolean         running     = false                     ;
    public static long fistTime;
    public static long lastTime;


    public Game(String[] args)
    {
        fistTime = lastTime = System.currentTimeMillis();
        ds = new Display        ("Text-RPG", WIDTH, HIGHT, "/sprites/icon.png") ;
        sm = new StateManager   ()                                              ;
        ds   .setKeyListener    (sm)                                            ;

        //debug
        if(args.length == 0) {
            StateManager.setState(StateManager.MENU);
        }
        else{
            String opt = args[0].toLowerCase();
            switch (opt){
                case "menu":
                case "m":
                    StateManager.setState(StateManager.MENU1);
                    break;
                case "r":
                case "ranking":
                    StateManager.setState(StateManager.RANKING);
                    break;
                case "c":
                case "credits":
                    StateManager.setState(StateManager.CREDITS);
                    break;
                case "s":
                case "select":
                    StateManager.setState(StateManager.SELECT_SAVE);
                    break;
                default:
                    System.out.println("Invalid option, use -h or help to see the options");
                    System.exit(0);
            }
        }
    }

    public static void initTime() {
        fistTime = System.currentTimeMillis();
        lastTime = System.currentTimeMillis();
    }

    private void render()
    {

        BufferStrategy  bs = ds.getBufferStrategy()                             ;
        if(bs == null)
        {
                  ds.createBufferStrategy()                                     ;
          bs    = ds   .getBufferStrategy()                                     ;
        }

        Graphics        g  = bs.getDrawGraphics()                               ;
        g.clearRect(0, 0, WIDTH, HIGHT)                                         ;

        if      ( StateManager.getStade() != null)
        {
            try
            {
                sm.render(g)                                                    ;
            }
            catch (Exception ignored){}
        }

        g.dispose()                                                             ;
        bs.show()                                                               ;
    }

    private void update()
    {
        if      ( StateManager.getStade() == null)
        {
            return                                                              ;
        }
        sm.update()                                                             ;
    }

    public synchronized void start()
    {
        if      ( thread != null        )
        {
            return                                                      ;
        }
        thread  = new Thread(this)                                              ;
        thread.start()                                                          ;
        running = true                                                          ;
    }

    private synchronized void stop()
    {
        if      ( thread == null        )
        {
            return                                                              ;
        }
        try
        {
            thread.join()                                                       ;
        }
        catch   ( InterruptedException e)
        {
            e.printStackTrace()                                                 ;
        }

    }

   @Override
    public void run()
    {
        start()                                                                 ;
        Launch.carregando()                                                     ;
        int     fps         = 60                                                ;
        double  tpt         = 1000000000 / Double.parseDouble(fps+"")           ;
        double  delta       = 0                                                 ;
        long    now, last   = System.nanoTime()                                 ;
        ds.setVisible(true)                                                     ;

        while (running)
        {

            now     =   System.nanoTime(                            )           ;
            delta   +=  ( now - last ) / tpt                                    ;
            last    =   now                                                     ;

            if(delta >= 1)
            {
                Game.lastTime = System.currentTimeMillis();
                render()                                                        ;
                update()                                                        ;
                delta--                                                         ;
            }
        }
        stop()                                                                  ;
    }

}
