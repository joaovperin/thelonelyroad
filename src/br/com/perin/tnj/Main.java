package br.com.perin.tnj;

import br.com.perin.tnj.graphics.Square1;
import br.com.perin.tnj.graphics.Square2;
import br.com.perin.tnj.graphics.Square3;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.util.glu.GLU.gluOrtho2D;

/**
 * Classe principal do Game
 *
 * @author 0199831
 */
public class Main {

    //http://www.3dgep.com/introduction-to-opengl/
    /**
     * Título da janela
     */
    private static final String DISPLAY_TITLE = "The Mouse Atack";
    /**
     * Tamanho da tela: Altura
     */
    public static final int WINDOW_HEIGHT = 480;
    /**
     * Tamanho da tela: Largura
     */
    public static final int WINDOW_WIDTH = 640;
    /**
     * Taxa de atualização da janela em FPS
     */
    private static final int WINDOW_FPS = 60;

    /**
     * LiveSquare properties
     */
    private static final int THREAD_SLEEP_TIME = 100;
    private static final int INCREMENT_AXIS_TIMES = 5;

    /**
     * Square1 properties
     */
    private Square1 square = new Square1();
    private Square2 square2 = new Square2();
    private Square3 square3 = new Square3();

    /**
     * Construtor principal
     */
    public Main() {

    }

    /**
     * Método principal
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main main = null;
        try {
            printInfo();
            main = new Main();
            main.create(DISPLAY_TITLE);
            main.run();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (main != null) {
                main.destroy();
            }
        }
    }

    /**
     * Printa informações do jogo na tela
     */
    private static void printInfo() {
        System.out.println("Keys:");
        System.out.println("down  - Shrink");
        System.out.println("up    - Grow");
        System.out.println("left  - Rotate left");
        System.out.println("right - Rotate right");
        System.out.println("esc   - Exit");
    }

    /**
     * Cria janela
     *
     * @param title Título da janela
     * @throws LWJGLException
     */
    public void create(String title) throws LWJGLException {
        // Display
        Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT));
        Display.setFullscreen(false);
        Display.setTitle(title);
        Display.create();

        // Keyboard
        Keyboard.create();

        // Mouse
        Mouse.setGrabbed(false);
        Mouse.create();

        // OpenGL
        initGL();
        resizeGL();
    }

    /**
     * Destroi a janela
     */
    private void destroy() {
        // Methods already check if created before destroying.
        Mouse.destroy();
        Keyboard.destroy();
        Display.destroy();
    }

    /**
     * Inicializa o OpenGL em 2D
     */
    private void initGL() {
        //2D Initialization
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);
    }

    /**
     * Processa as informações do teclado
     */
    private void processKeyboard() {
        //Square's Size
        square.processKeyboard();
    }

    /**
     * Processa o mouse
     */
    private void processMouse() {
        square2.processMouse();
    }

    /**
     * Renderiza a tela
     */
    private void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();

        square.render();
        square2.render();
        square3.render();
    }

    /**
     * Redimensiona a projeção
     */
    private void resizeGL() {
        //2D Scene
        glViewport(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
//
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluOrtho2D(0.0f, WINDOW_WIDTH, 0.0f, WINDOW_HEIGHT);
        glPushMatrix();
//
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glPushMatrix();
    }

    /**
     * Laço principal
     */
    private void run() {
        // Se não tentou fechar a janela e não pressionou ESC
        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            // Processa as entradas e toma as ações necessárias
            if (Display.isVisible()) {
                processKeyboard();
                processMouse();
                update();
                render();
            } else {
                // Se algum evento externo modificou a janela, renderiza novamente
                if (Display.isDirty()) {
                    render();
                }
                // Pausa por 100 milissegundos
                try {
                    Thread.sleep(THREAD_SLEEP_TIME);
                } catch (InterruptedException ex) {
                }
            }
            // Atualiza buffer da janela e sincroniza em 60FPS
            Display.update();
            Display.sync(WINDOW_FPS);
        }
    }

    /**
     * Atualiza janela
     */
    private void update() {
        square.render();
        square2.render();
        square3.render();
    }

}
