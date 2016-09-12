/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tlr.elements;

import br.com.tlr.encapsulation.Animacoes;
import br.com.tlr.forbidden.Animacao;
import java.util.ArrayList;
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

    // Array de animações (TORNAR FINAL)
    protected Animacoes animacoes;
    
    // Animações default   (SUBSTITUIR POR ARRAYLIST)
    protected Animation up;
    protected Animation down;
    protected Animation left;
    protected Animation right;

}
