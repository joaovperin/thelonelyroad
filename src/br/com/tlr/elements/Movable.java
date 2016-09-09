/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tlr.elements;

/**
 * * Classe abstrata para todos os itens que podem se mover
 * 
 * @author 0199831
 */
public abstract class Movable {
    
    /** Diretório dos Sprites */
    public static final String SPRITES_DIR = "data/sprites/sheets/";
    /** Posição atual do objeto - X e Y */
    private float x = 0f, y = 0f;
    
    /**
     * Incrementa a posição no eixo X
     * 
     * @param x Valor a Incrementar na posição
     */
    protected void addX(float x) {
        this.x += x;
    }

    /**
     * Incrementa a posição no eixo X
     * 
     * @param y Valor a incrementar na posição
     */
    protected void addY(float y) {
        this.y += y;
    }
    
    /**
     * Decrementa a posição no eixo X
     * 
     * @param x Valor a decrementar da posição
     */
    public void subX(float x) {
        this.x -= x;
    }

    /**
     * Decrementa a posição no eixo Y
     * 
     * @param y Valor a decrementar da posição
     */
    public void subY(float y) {
        this.y -= y;
    }

    /**
     * Retorna a posição atual no eixo X
     * 
     * @return float
     */
    public float getX() {
        return x;
    }

    /**
     * Retorna a posição atual no eixo Y
     * 
     * @return float
     */
    public float getY() {
        return y;
    }

    
    /**
     * COMENTAR ---
    */
    
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    
    
}
