/*
 * TheLonelyRoad
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package br.com.tlr.elements;

import java.util.ArrayList;

/**
 * Descrição da classe.
 */
public class Animacoes {

    /** Array de animações */
    private ArrayList<Anim> animacoes;
    /** Número de animações */
    private final int numAnim;

    /**
     * Construtor padrão da classe
     *
     * @param numAnim Número de animações
     */
    public Animacoes(int numAnim) {
        this.numAnim = numAnim;
        createAnimacoes();
    }

    /**
     * Cria animações
     */
    private void createAnimacoes() {
        // Cria animações
        for (int i = 0; i < numAnim; i++) {
            animacoes.add(new Anim(i));
        }

    }

    /**
     * Retorna o número de animações
     *
     * @return animacoes
     */
    public ArrayList<Anim> getAnimacoes() {
        return animacoes;
    }

}
