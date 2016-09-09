/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tlr.elements;

import org.newdawn.slick.Animation;

/**
 * Classe responsável pela criação de characters
 *
 * @author JOAO
 */
public abstract class Character extends Movable implements Animable {

    /** Animação atual do character */
    protected Animation character;

    /** Tiro disparado pelo character */
    protected Shot tiro;

    // Animações default   (SUBSTITUIR POR ARRAYLIST)
    protected Animation up;
    protected Animation down;
    protected Animation left;
    protected Animation right;

}
