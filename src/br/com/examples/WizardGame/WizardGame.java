/*
 * LWJGL_Space_Invaders
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package br.com.examples.WizardGame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Descrição da classe.
 */
public class WizardGame extends BasicGame {


    /**
     *
     * Parsing and Rendering Tiled TMX Format Maps in Your Own Game Engine
     *
     */
    // http://gamedevelopment.tutsplus.com/tutorials/parsing-and-rendering-tiled-tmx-format-maps-in-your-own-game-engine--gamedev-3104

    private TiledMap grassMap;

    public WizardGame()  {
        super("Wizard game");
    }

    public static void main(String[] arguments) {
        try {
            AppGameContainer app = new AppGameContainer(new WizardGame());
            app.setDisplayMode(500, 400, false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        grassMap = new TiledMap("data/grassmap.tmx");
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    }

    public void render(GameContainer container, Graphics g) throws SlickException {
        grassMap.render(0, 0);
    }
}
