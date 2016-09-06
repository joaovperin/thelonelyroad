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

    private int x;
    private final int offset;

    public Anim(int offset) {
        this.offset = offset;
    }

    public int next() {
        if (++x >= 4) {
            x = 0;
        }
        return x + (offset) * 4;
    }

    public enum DIR {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

}
