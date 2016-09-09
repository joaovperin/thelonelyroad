/*
 * TheLonelyRoad
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package br.com.tlr.core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Classe principal para controle do game
 */
public class Main {

    /** Tamanho da tela - Largura (X) */
    private static final int SCREEN_WIDTH = 990;
    /** Tamanho da tela - Altura (Y) */
    private static final int SCREEN_HEIGHT = 890;

    /**
     * Inicializa o game
     *
     * @param arguments
     */
    public static void main(String[] arguments) {
        try {
//            System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true"); // Adicionado apenas pq os PC da Feevale são LIXO.
            AppGameContainer app = new AppGameContainer(new TheLonelyRoad());
            app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

}
