/*
 * LWJGL_Space_Invaders
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package br.com.examples.Slick;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * Descrição da classe.
 */
public class SlickMain extends StateBasedGame {

    /**
     * https://thejavablog.wordpress.com/2008/06/08/using-slick-2d-to-write-a-game/
     *
     * http://slick.cokeandcode.com/wiki/doku.php?id=spiegel_tutorials -> NÃO CONSEGUI ABRIR
     *
     *
     *
     */



    public static final String gamename = "MyGameName";
    public static final int play = 0;
    public static final int xSize = 400;
    public static final int ySize = 300;

    public SlickMain(String gamename) {
        super(gamename);
        this.addState(new SlickPlay());
    }

    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(play).init(gc, this);
        this.enterState(play);
    }

    public static void main(String[] args) {
        AppGameContainer appgc;
        try {
            appgc = new AppGameContainer(new SlickMain(gamename));
            appgc.setDisplayMode(xSize, ySize, false);
            appgc.setTargetFrameRate(60);
            appgc.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

}
