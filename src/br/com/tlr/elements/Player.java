/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tlr.elements;

import static br.com.tlr.elements.Anim.NUM_COL;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author JOAO
 */
public class Player extends Character {

    // http://slick.ninjacave.com/forum/viewtopic.php?t=619  TECLADO SLICK2D
    /** Nome da animaçao */
    protected final String animationName;
    /** Folha de sprites */
    protected SpriteSheet sheet;
    /** Duração de cada animação */
    protected final int[] duration;
    /** Número de frames por sprite */
    protected final int numFrames;

    /** Animação atual do player */
    protected Animation player;
    /** Número do sprite atual */
    private int sprite;
    /** Posição atual do sprite - X e Y */
    private float x = 0f, y = 0f;

    // Array de animações (testar)
    private Animacoes animacoes;

    // Animações default        TODO: Transformar num Array de animações
    private final Anim up = new Anim(0);
    private final Anim down = new Anim(1);
    private final Anim left = new Anim(2);
    private final Anim right = new Anim(3);

    /**
     * Construtor padrão de um Player
     *
     * @param animationName
     * @param numFrames Número de frames por sprite
     */
    public Player(String animationName, int numFrames) {
        this.animationName = animationName;
        this.numFrames = numFrames;
        this.duration = createDuration();
        this.sprite = 1;
        this.player = new Animation();
    }

    /**
     * Cria um Array de duração das animações
     *
     * @return int[]
     */
    private int[] createDuration() {
        // TODO: Implementar criação de Array duração baseado no número de frames
        // Usar: this.numFrames
        return new int[] { 1000, 1000, 1000, 1000 };
    }

    /**
     * Carrega as imagens e as animações
     */
    @Override
    public void load() {
        try {
            // Carrega sprites para as animações de movimentos
            sheet = new SpriteSheet(SPRITES_DIR + animationName, 32, 48);
//            player = new Animation(sheet, 200); //Testar
            player.setAutoUpdate(true);
            // Carrega frames na animação do player
            for (int lin = 0; lin < NUM_COL; lin++) {
                for (int col = 0; col < duration.length; col++) {
                    player.addFrame(sheet.getSprite(col, lin), duration[col]);
                }
            }
            // Instancia Array de animações
            animacoes = new Animacoes(numFrames);
        } catch (SlickException e) {
            System.out.println("Ex: " + e);
        }
    }

    /**
     * Processa o teclado
     * TODO: Parar de receber maxX e maxY aqui e receber no construtor como "private final float[] max;"
     *
     * @param input Entradas do jogo
     * @param delta
     * @param maxX Dimensões máximas -> X
     * @param maxY Dimensões máximas -> Y
     */
    public void processKeyboard(Input input, int delta, float maxX, float maxY) {
        // Ação TIRO
        if (input.isKeyPressed(Keyboard.KEY_SPACE)) {
            shoot(delta);
        }
        // Ações de movimentação
        if (input.isKeyDown(Keyboard.KEY_I)) {
            if (getY() <= 0) {
                return;
            }
            setSprite(up.next());
            update(delta);
            subY(delta * 0.1f);
        }
        if (input.isKeyDown(Keyboard.KEY_K)) {
            if (getY() >= maxY) {
                return;
            }
            setSprite(down.next());
            update(delta);
            addY(delta * 0.1f);
        }
        if (input.isKeyDown(Keyboard.KEY_J)) {
            if (getX() <= 0) {
                return;
            }
            setSprite(left.next());
            update(delta);
            subX(delta * 0.1f);
        }
        if (input.isKeyDown(Keyboard.KEY_L)) {
            if (getX() >= maxX) {
                return;
            }
            setSprite(right.next());
            update(delta);
            addX(delta * 0.1f);
        }
    }

    /**
     * Ação de tiro -> A IMPLEMENTAR
     *
     * @param delta
     */
    public void shoot(int delta) {
        System.out.println("Tiro");
    }

    /**
     * Renderiza o jogador nas coordenadas
     */
    public void render() {
        player.setCurrentFrame(sprite);
        player.draw(x, y);
    }

    /**
     * Atualização do player na tela
     *
     * @param delta
     */
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
