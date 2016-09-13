/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tlr.encapsulation;

import br.com.tlr.forbidden.Animacao;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author 0199831
 */
public class Animacoes {

    /** Animação atual */
    private int current;
    // Array de animações (TORNAR FINAL)
    private final List<Animacao> animacoes;

    /**
     * Construtor que recebe uma lista de animações
     *
     * @param animacoes
     */
    public Animacoes(List<Animacao> animacoes) {
        this.animacoes = animacoes;
    }

    public void setCurrent(AnimationEnum direction) {
        this.current = direction.getCode();
        start();
    }

    /**
     * Carrega as imagens e as animações
     *
     * @param container Container do jogo
     * @throws SlickException Problema no carregamento dos objetos na API
     */
//    @Override
    public void load(GameContainer container) throws SlickException {
        for (Animacao a : animacoes) {
            a.load(container);
        }
    }

    /**
     * Atualiza os frames do jogo
     *
     * @param container Container do jogo
     * @param delta Tempo de atualização
     * @throws SlickException Problema ao atualizar quadros
     */
    public void update(GameContainer container, int delta) throws SlickException {
        getCurrent().update(container, delta);
    }

    /**
     * Renderiza as imagens do jogo
     *
     * @param container Container do jogo
     * @param g Contexto gráfico usado para renderizar o canvas
     * @throws SlickException Problema na renderização de imagens na API
     */
    public void render(GameContainer container, Graphics g) throws SlickException {
        getCurrent().render(container, g);
    }


    public void move(float x, float y){
        getCurrent().move(x, y);
    }

    public void start(){
        getCurrent().getAnimation().setAutoUpdate(true);
    }

    public void stop(){
        getCurrent().getAnimation().setAutoUpdate(false);
    }

    /**
     * Retorna a animação atual
     *
     * @return Animacao
     */
    private Animacao getCurrent() {
        return animacoes.get(current);
    }

}
