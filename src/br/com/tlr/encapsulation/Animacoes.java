/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tlr.encapsulation;

import br.com.tlr.forbidden.Animacao;
import java.util.ArrayList;
import org.newdawn.slick.Animation;

/**
 *
 * @author 0199831
 */
public class Animacoes {

    /** Animação atual */
    private int current;
    // Array de animações (TORNAR FINAL)
    private final ArrayList<Animacao> animacoes;

    public Animacoes(ArrayList<Animacao> animacoes) {
        this.animacoes = animacoes;
    }

    public void setCurrent(AnimationEnum direction) {
        this.current = direction.getCode();
    }

    /** 
     * Retorna a animação atual
     * 
     * @return Animacao
     */
    public Animacao getCurrent() {
        return animacoes.get(current);
    }

    public ArrayList<Animacao> getAnimacoes() {
        return animacoes;
    }

}
