/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tlr.elements;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author JOAO
 */
public class Player extends Character {

    /**
     * Nome da animaçao
     */
    protected final String animationName;
    /**
     * Folha de sprites
     */
    protected SpriteSheet sheet;
    /**
     * Duração de cada animação
     */
    protected final int[] duration;

    /**
     * Animaçao atual do player
     */
    protected Animation player;
    /**
     * Posição X e Y
     */
    private float x = 0f, y = 0f;

    /**
     * Max coordinates
     */
    private final float[] max;

    private int sprite;
    Anim up = new Anim(Anim.DIR.UP.ordinal());
    Anim down = new Anim(Anim.DIR.DOWN.ordinal());
    Anim left = new Anim(Anim.DIR.LEFT.ordinal());
    Anim right = new Anim(Anim.DIR.RIGHT.ordinal());

    /**
     * Construtor padrão de um Player
     *
     * @param animationName
     * @param duration
     * @param max
     */
    public Player(String animationName, int[] duration, float[] max) {
        this.animationName = animationName;
        this.duration = duration;
        this.max = max;
        this.sprite = 1;
    }

    /**
     * Carrega as imagens e as animações
     */
    @Override
    public void load() {
        try {
            // Carrega sprites para as animações de movimentos
            sheet = new SpriteSheet(SPRITES_DIR + animationName, 32, 48);
            player = new Animation();
//            player = new Animation(sheet, 200); //Testar
            player.setAutoUpdate(true);
            // Carrega frames na animação do player
            for (int lin = 0; lin < 4; lin++) {
                for (int col = 0; col < duration.length; col++) {
                    player.addFrame(sheet.getSprite(col, lin), duration[col]);
                }
            }
        } catch (SlickException e) {
            System.out.println("Ex: " + e);
        }
    }

    public void processKeyboard(int delta, float maxX, float maxY) {
        // ME
        if (Keyboard.isKeyDown(Keyboard.KEY_I)) {
            if (getY() <= 0) {
                return;
            }
            setSprite(up.next());
            update(delta);
            subY(delta * 0.1f);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_K)) {
            if (getY() >= maxY) {
                return;
            }
            setSprite(down.next());
            update(delta);
            addY(delta * 0.1f);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_J)) {
            if (getX() <= 0) {
                return;
            }
            setSprite(left.next());
            update(delta);
            subX(delta * 0.1f);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_L)) {
            if (getX() >= maxX) {
                return;
            }
            setSprite(right.next());
            update(delta);
            addX(delta * 0.1f);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
            shoot(delta);
        }
    }
    
    public void shoot(int delta){
        
    }

    public void render() {
        player.setCurrentFrame(sprite);
        player.draw(x, y);
    }

    public void update(int delta) {
        player.update(delta);
    }

    /**
     *
     * GET / SETS
     *
     * @param x
     */
    public void addX(float x) {
        this.x += x;
    }

    public void addY(float y) {
        this.y += y;
    }

    public void subX(float x) {
        this.x -= x;
    }

    public void subY(float y) {
        this.y -= y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setSprite(int sprite) {
        this.sprite = sprite;
    }

}
