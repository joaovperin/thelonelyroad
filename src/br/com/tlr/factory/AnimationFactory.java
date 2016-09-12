/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tlr.factory;

import br.com.tlr.encapsulation.Animacoes;
import br.com.tlr.forbidden.Animacao;
import br.com.tlr.encapsulation.AnimationEnum;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 0199831
 */
public class AnimationFactory extends AbstractFactory {

    /** Diretório dos Sprites */
    public static final String SPRITES_DIR = "data/sprites/sheets/";

    /**
     * Cria um ArrayList de animações
     *
     * @param name Nome da folha de estilos
     * @param num Número de frames por animação
     * @param args Animações a criar
     * @return ArrayList<>
     * @throws SlickException Problema ao criar animações
     */
    public static Animacoes create(String name, int num, AnimationEnum... args) throws SlickException {
        // Carrega sprites para as animações de movimentos
        SpriteSheet sheet = new SpriteSheet(SPRITES_DIR + name, 32, 48);
        ArrayList<Animacao> list = new ArrayList<>();
        // Percorre argumentos criando animações
        for (AnimationEnum arg : args) {
            list.add(new Animacao(AnimationEnum.UP, num, sheet));
        }
        // Retorna lista criada
        return new Animacoes(list);
    }

}
