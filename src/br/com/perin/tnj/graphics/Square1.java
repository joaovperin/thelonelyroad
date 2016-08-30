/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.perin.tnj.graphics;

import br.com.perin.tnj.graphics.shapes.Square;
import br.com.perin.tnj.interfaces.Movable;
import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 *
 * @author 0199831
 */
public class Square1 extends Square implements Movable {

    //http://www.3dgep.com/introduction-to-opengl/
    

    private static final int INCREMENT_AXIS_TIMES = 5;

    public Square1() {
        size = 100;
        x = 0;
        y = 0;
        z = 0;
        color = Color3.fromInt(250, 150, 100);
    }

    /**
     * Processa as informações do teclado
     */
    @Override
    public void processKeyboard() {
        //Square's Size
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            y -= INCREMENT_AXIS_TIMES;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            y += INCREMENT_AXIS_TIMES;
        }
        //Square's Z
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            x -= INCREMENT_AXIS_TIMES;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            x += INCREMENT_AXIS_TIMES;
        }
    }

    /**
     * Processa o mouse
     */
    @Override
    public void processMouse() {
    }

    /**
     * Printa informações na tela
     */
    @Override
    public void printInfo() {

    }

    public void render() {
        glPushMatrix();
        //Draw a basic square
        glTranslatef(x, y, 0.0f);
        glRotatef(z, 0.0f, 0.0f, 1.0f);
        glTranslatef(-(size >> 1), -(size >> 1), 0.0f);
        glColor3f(color.getR(), color.getG(), color.getB());
        glBegin(GL_QUADS);

        glTexCoord2f(0.0f, 0.0f);
        glVertex2f(0.0f, 0.0f);
        glTexCoord2f(1.0f, 0.0f);
        glVertex2f(size, 0.0f);
        glTexCoord2f(1.0f, 1.0f);
        glVertex2f(size, size);
        glTexCoord2f(0.0f, 1.0f);
        glVertex2f(0.0f, size);
        glEnd();
        glPopMatrix();
    }

}
