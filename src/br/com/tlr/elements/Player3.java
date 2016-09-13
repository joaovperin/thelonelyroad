/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tlr.elements;

import static br.com.tlr.core.TheLonelyRoad.provider;
import br.com.tlr.encapsulation.AnimationEnum;
import br.com.tlr.factory.AnimationFactory;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.ControllerButtonControl;
import org.newdawn.slick.command.ControllerDirectionControl;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.command.MouseButtonControl;

/**
 *
 * @author JOAO
 */
public class Player3 extends Character implements InputProviderListener {

    /** The command for attack */
    private Command attack = new BasicCommand("attack");
    /** The command for jump */
    private Command jump = new BasicCommand("jump");
    /** The command for jump */
    private Command run = new BasicCommand("run");
    /** The message to be displayed */
    public String controlMessage = "TESTE";

    /**
     * Construtor padrão de um Player
     *
     * @param animationName Nome do arquivo de animações do jogador
     * @param numFrames Número de frames por sprite
     * @param movableArea Dimensões máximas do jogador
     */
    public Player3(String animationName, int numFrames, float[][] movableArea) {
        super(animationName, numFrames, movableArea);
    }

    /**
     * Carrega as imagens e as animações
     *
     * @param container Container do jogo
     * @throws SlickException Problema no carregamento dos objetos na API
     */
    @Override
    public void load(GameContainer container) throws SlickException {

        System.out.println("asduhasdu");


        provider.bindCommand(new KeyControl(Input.KEY_UP), jump);
        provider.bindCommand(new KeyControl(Input.KEY_DOWN), run);
        provider.bindCommand(new KeyControl(Input.KEY_LEFT), run);
        provider.bindCommand(new KeyControl(Input.KEY_RIGHT), run);
        provider.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.LEFT), run);
        provider.bindCommand(new KeyControl(Input.KEY_SPACE), attack);
        provider.bindCommand(new MouseButtonControl(0), attack);
        provider.bindCommand(new ControllerButtonControl(0, 1), attack);

        
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
        g.drawString(controlMessage, 100, 150);
    }

    @Override
    public void controlPressed(Command command) {
        System.out.println("asdhasdjkhjkasdh");
    }

    @Override
    public void controlReleased(Command command) {
        System.out.println("asduhasduh");
    }

}
