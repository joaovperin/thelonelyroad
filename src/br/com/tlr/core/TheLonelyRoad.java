/*
 * LWJGL_Space_Invaders
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package br.com.tlr.core;

import br.com.tlr.elements.Player;
import br.com.tlr.elements.Player2;
import br.com.tlr.elements.Player3;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Coração do jogo TheLonelyRoad
 */
public class TheLonelyRoad extends BasicGame {

    /** Mapa principal do jogo -> Grama */
    private TiledMap grassMap;
    /** The input provider abstracting input */
    public static InputProvider provider;
    /** Jogador */
    private Player player;
    /** Jogador 2 */
    private Player2 player2;
    /** Jogador 3 */
    private Player3 player3;


    /**
     * Construtor padrão do game
     */
    public TheLonelyRoad() {
        super("The Lonely Road");
    }

    /**
     * Carrega as imagens e as animações
     *
     * @param container Container do jogo
     * @throws SlickException Problema no carregamento dos objetos na API
     */
    @Override
    public void init(GameContainer container) throws SlickException {

        provider = new InputProvider(container.getInput());
        provider.addListener(player3);

        // Carrega o mapa em memória
        grassMap = new TiledMap("data/maps/desert.tmx");
        // Área movível do jogador
        float[][] movableArea = {
            {0, grassMap.getWidth() * 32.0f}, // X axis
            {0, grassMap.getHeight() * 28.7f} // Y axis
        };
        // Instancia o jogador
        player = new Player("player.png", 4, movableArea);
        // Instancia o jogador 2
        player2 = new Player2("player.png", 4, movableArea);
        player3 = new Player3("player.png", 4, movableArea);
        // Carrega os sprites e animações do jogador
        player.load(container);
        player2.load(container);
        player3.load(container);
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
        // Atualiza o jogador
        player.update(container, delta);
        player2.update(container, delta);
        player3.update(container, delta);
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
        // Renderiza o mapa e o jogador
        grassMap.render(0, 0);
        player.render(container, g);
        player2.render(container, g);
        player3.render(container, g);
    }
}
