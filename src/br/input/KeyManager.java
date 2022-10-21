package br.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    private boolean[] keys = new boolean[256];
    public static boolean UP, DOWN, LEFT, RIGHT, ENTER;

    public void update(){
        UP      = keys[KeyEvent.VK_W] || keys [KeyEvent.VK_UP    ];
        DOWN    = keys[KeyEvent.VK_S] || keys [KeyEvent.VK_DOWN  ];
        LEFT    = keys[KeyEvent.VK_A] || keys [KeyEvent.VK_LEFT  ];
        RIGHT   = keys[KeyEvent.VK_D] || keys [KeyEvent.VK_RIGHT ];
        ENTER   = keys[KeyEvent.VK_ENTER];
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() > 256) return;
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() > 256) return;
        keys[e.getKeyCode()] = false;
    }
}
