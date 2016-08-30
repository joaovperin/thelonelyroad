/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.perin.tnj.graphics;

/**
 * Color 3
 *
 * @author 0199831
 */
public class Color3 {

    /**
     * Color default
     */
    public static final Color3 DEFAULT_COLOR = new Color3();

    /**
     * R, G e B
     */
    private final float r, g, b;

    /**
     * Construtor padrão
     */
    public Color3() {
        this.r = 0.5f;
        this.g = 0.5f;
        this.b = 0.5f;
    }

    /**
     * Construtor
     *
     * @param r
     * @param g
     * @param b
     */
    public Color3(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Retorna uma color 3 a partir de inteiros
     *
     * @param r
     * @param g
     * @param b
     * @return
     */
    public static Color3 fromInt(int r, int g, int b) {
        return new Color3((float)r / 255, (float)g / 255, (float)b / 255);
    }

    /**
     * Retorna a componente Red da cor
     *
     * @return float
     */
    public float getR() {
        return r;
    }

    /**
     * Retorna a componente Green da cor
     *
     * @return float
     */
    public float getG() {
        return g;
    }

    /**
     * Retorna a componente Blue da cor
     *
     * @return float
     */
    public float getB() {
        return b;
    }

}
