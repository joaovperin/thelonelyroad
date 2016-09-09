/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tlr.elements;

import static br.com.tlr.elements.Movable.SPRITES_DIR;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 0199831
 */
public class Shot extends Attack {

    /** Nome da animaçao */
    private final String animationName;
    /** Tempo de vida do tiro */
    private final int lifeSpan;
    /** Tiro */
    private Animation tiro;

    /**
     * Contador de tempo do tiro
     */
    private int counter;
    private boolean isAlive;

    /**
     * Construtor da classe que recebe o lifespan
     *
     * @param animationName
     * @param lifeSpan
     */
    public Shot(String animationName, int lifeSpan) {
        this.animationName = animationName;
        this.lifeSpan = lifeSpan;
        this.isAlive = false;
        this.counter = 0;
    }

    /**
     * Carrega as imagens e as animações
     *
     * @param container Container do jogo
     * @throws SlickException Problema no carregamento dos objetos na API
     */
    @Override
    public void load(GameContainer container) throws SlickException {
        // Carrega sprites para as animações de movimentos
        SpriteSheet sheet = new SpriteSheet(SPRITES_DIR + animationName, 32, 48);
        // Carrega frames de animação do character da spritesheet
        tiro = new Animation(sheet, 0, 0, 3, 0, true, 300, true);
    }


    /**
     * Atualiza os frames do jogo
     *
     * @param container Container do jogo
     * @param delta Tempo de atualização
     * @throws SlickException Problema ao atualizar quadros
     */
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    }

    /**
     * Renderiza as imagens do jogo
     *
     * @param container Container do jogo
     * @param g Contexto gráfico usado para renderizar o canvas
     * @throws SlickException Problema na renderização de imagens na API
     */
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    }




    
    /**
     * Ação de tiro -> A IMPLEMENTAR
     *
     * @param delta
     */
    public void shoot(int delta, float x, float y) {
        isAlive = true;
        setX(x);
        setY(y);
        System.out.println("Tiro");
    }

    /**
     * Ação de tiro -> A IMPLEMENTAR
     *
     * @param delta
     */
    public void updateInternal(int fps, int dir) {
        if (isAlive) {
            System.out.println("Delta: " + counter + "  LifeSpan: " + lifeSpan);
//            tiro.draw(getX(), getY());

            switch (dir) {
                case 0:
                    subY(fps * 0.001f);
                    break;
                case 1:
                    addY(fps * 0.001f);
                    break;
                case 2:
                    subX(fps * 0.001f);
                    break;
                case 3:
                    addX(fps * 0.001f);
                    break;
            }
            tiro.draw(getX(), getY());
            if (++counter >= lifeSpan) {
                die();
            }
        }

    }

    private void die() {
        counter = 0;
        isAlive = false;
        System.out.println("Morto");
    }

}
