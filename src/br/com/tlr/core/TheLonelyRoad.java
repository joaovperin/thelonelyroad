/*
 * LWJGL_Space_Invaders
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package br.com.tlr.core;

import br.com.tlr.elements.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Classe principal do game
 */
public class TheLonelyRoad extends BasicGame {

    /**
     * Tamanho da tela - Largura (X)
     */
    private static final int SCREEN_WIDTH = 990;
    /**
     * Tamanho da tela - Altura (Y)
     */
    private static final int SCREEN_HEIGHT = 890;

    private Player me;
    
    int BATATA = 0;

    /**
     * Imagens de movimentação
     */
    Image[] movementUp = new Image[3];
    Image[] movementDown = new Image[3];
    Image[] movementLeft = new Image[3];
    Image[] movementRight = new Image[3];
    Image[] movementTest = new Image[3];
    int[] duration = {200, 200, 200};

    /**
     * Animaçoes
     */
    private Animation sprite, up, down, left, right;
    /**
     * Coordenadas
     */
    private float x = 600f, y = 600f;
    SpriteSheet sheet;

    private Player player;

    /**
     * Mapa principal do jogo -> Grama
     */
    private TiledMap grassMap;

    /**
     * Construtor padrão da classe
     */
    public TheLonelyRoad() {
        super("The Lonely Road");
    }

    /**
     * Inicializa o game
     *
     * @param arguments
     */
    public static void main(String[] arguments) {
        try {
            System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true"); // Adicionado apenas pq os PC da Feevale são LIXO.
            AppGameContainer app = new AppGameContainer(new TheLonelyRoad());
            app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carrega os dados necessários e inicializa o game
     *
     * @param container
     * @throws org.newdawn.slick.SlickException
     */
    @Override
    public void init(GameContainer container) throws SlickException {

        // Carrega o mapa em memória
        grassMap = new TiledMap("data/maps/desert.tmx");

        me = new Player("player.png", new int[]{300, 300, 300, 300}, new float[]{grassMap.getWidth()
            * 32.0f, grassMap.getHeight() * 28.7f});

        /**
         * TODO: SEPARAR TODOS OS SPRITES EM UM ÚNICO ARQUIVO E TESTAR CLASSE
         * PLAYER
         */
        // Carrega sprites para as animações de movimentos
        movementUp[0] = new Image("data/sprites/wmg1-fr1.png");
        movementUp[1] = new Image("data/sprites/wmg1-fr2.png");
        movementUp[2] = new Image("data/sprites/wmg1-fr3.png");
        movementDown[0] = new Image("data/sprites/wmg1-rt1.png");
        movementDown[1] = new Image("data/sprites/wmg1-rt2.png");
        movementDown[2] = new Image("data/sprites/wmg1-rt3.png");
        movementLeft[0] = new Image("data/sprites/wmg1-lf1.png");
        movementLeft[1] = new Image("data/sprites/wmg1-lf2.png");
        movementLeft[2] = new Image("data/sprites/wmg1-lf2.png");
        movementRight[0] = new Image("data/sprites/wmg1-lr1.png");
        movementRight[1] = new Image("data/sprites/wmg1-lr2.png");
        movementRight[2] = new Image("data/sprites/wmg1-lr2.png");

        // Cria animações à partir dos sprites
//        Sprite tst = new Sprite("wmg1-fr", 3);
//        up = new Animation(movementUp, duration, false);
//        SpriteSheet sheet = new SpriteSheet("data/sprites/sheets/sara-cal.png", 32, 48);
//        up = new Animation(sheet, duration, duration);
        up = new Animation(movementUp, duration, false);
        down = new Animation(movementDown, duration, false);
        left = new Animation(movementLeft, duration, false);
        right = new Animation(movementRight, duration, false);
//
//        sheet = new SpriteSheet("data/sprites/sheets/tst2.png", 32, 48);
//        player = new Animation();
//        player.setAutoUpdate(true);
//        int tX = 0;
//        int tY = 0;
//        for (int col = 0; col < 5; col++) {
//            player.addFrame(sheet.getSprite(tX++, tY), 150);
//        }

        me.load();
        // Inicializa virado para a direita
        sprite = right;
    }

    /**
     * Atualiza os frames do jogo
     *
     * @param container
     * @param delta
     * @throws SlickException
     */
    @Override
    public void update(GameContainer container, int delta) throws SlickException {

//        boolean num = Keyboard.next();
//        int key = Keyboard.getEventKey();
//        System.out.println("Key: " + key + "  num: " + num);
        if (Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W)) {
            if (y <= 0) {
                return;
            }
            sprite = up;
            sprite.update(delta);
            // The lower the delta the slowest the sprite will animate.
            y -= delta * 0.1f;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S)) {
            if (y >= grassMap.getHeight() * 28.7f) {
                return;
            }
            sprite = down;
            sprite.update(delta);
            y += delta * 0.1f;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A)) {
            if (x <= 0) {
                return;
            }
            sprite = left;
            sprite.update(delta);
            x -= delta * 0.1f;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D)) {
            if (x >= grassMap.getWidth() * 32f) {
                return;
            }
            sprite = right;
            sprite.update(delta);
            x += delta * 0.1f;
        }

        me.processKeyboard(delta, grassMap.getWidth() * 32.0f, grassMap.getHeight() * 28.7f);

    }

    /**
     * Renderiza as imagens do jogo
     *
     * @param container
     * @param g
     * @throws SlickException
     */
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        grassMap.render(0, 0);
//        player.draw(0, 0);
        me.render();
        sprite.draw((int) x, (int) y);
    }
}
