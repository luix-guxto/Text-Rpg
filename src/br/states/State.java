package br.states;
import java.awt.*;
public interface State {
    void init();
    void update();
    void render(Graphics g) throws Exception;
    void KeyPress(int cod);
    void KeyReleased(int cod);
}
