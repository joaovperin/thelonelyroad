/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tlr.elements;

/**
 *
 * @author 0199831
 */
public class Anim {

    /** Coluna atual da animação */
    private int col;
    /** Linha na folha de sprites */
    private final int row;

    /** Número de colunas na folha de sprites */
    public static final int NUM_COL = 4;

    /**
     * Construtor padrão da classe
     *
     * @param row
     */
    public Anim(int row) {
        this.row = row;
    }

    /**
     * Pega a próxima coluna na linha selecionada
     *
     * @return int
     */
    public int next() {
        if (++col >= NUM_COL) {
            col = 0;
        }
        return col + (row * NUM_COL);
    }
    

}
