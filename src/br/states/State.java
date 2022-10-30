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
