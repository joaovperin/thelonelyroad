/*
 * Copyright (c) 2013, Oskar Veerhoek
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */

package future;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import utility.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/** NOT DONE YET Shows lighting without using the fixed function pipeline. */
public class CoreLighting {

    private static final String MODEL_LOCATION = "res/models/bunny.obj";
    private static final String VERTEX_SHADER_LOCATION = "res/shaders/fragment_phong_lighting_core.vs";
    private static final String FRAGMENT_SHADER_LOCATION = "res/shaders/fragment_phong_lighting_core.fs";
    private static EulerCamera cam;
    private static int shaderProgram;
    private static int vboVertexHandle;
    private static int vboNormalHandle;
    private static int uniformAmbientLight;
    private static int uniformNormalMatrix;
    private static int uniformModelViewMatrix;
    private static int uniformModelViewProjectionMatrix;
    private static int uniformLightPosition;
    private static int uniformShininess;
    private static int attributeVertex;
    private static int attributeColour;
    private static int attributeNormal;
    private static Model model;
    private static FloatBuffer modelViewProjection = BufferTools.reserveData(16);
    private static FloatBuffer modelView = BufferTools.reserveData(16);

    public static void main(String[] args) {
        setUpDisplay();
        setUpVBOs();
        setUpCamera();
        setUpShaders();
        setUpLighting();
        while (!Display.isCloseRequested()) {
            configureUniforms();
            render();
            logic();
            input();
            Display.update();
            Display.sync(60);
        }
        System.err.println(GLU.gluErrorString(glGetError()));
        cleanUp();
        System.exit(0);
    }

    private static void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setVSyncEnabled(true);
            Display.setTitle("Core Lighting Demo");
            Display.create();
        } catch (LWJGLException e) {
            System.err.println("The display wasn't initialized correctly. :(");
            Display.destroy();
            System.exit(1);
        }
    }

    private static void setUpVBOs() {
        int[] vbos;
        try {
            model = OBJLoader.loadModel(new File(MODEL_LOCATION));
            int vboVertexHandle = glGenBuffers();
            int vboNormalHandle = glGenBuffers();
            FloatBuffer vertices = BufferTools.reserveData(model.getFaces().size() * 9);
            FloatBuffer normals = BufferTools.reserveData(model.getFaces().size() * 9);
            for (Model.Face face : model.getFaces()) {
                vertices.put(BufferTools.asFloats(model.getVertices().get(face.getVertexIndices()[0] - 1)));
                vertices.put(BufferTools.asFloats(model.getVertices().get(face.getVertexIndices()[1] - 1)));
                vertices.put(BufferTools.asFloats(model.getVertices().get(face.getVertexIndices()[2] - 1)));
                normals.put(BufferTools.asFloats(model.getNormals().get(face.getNormalIndices()[0] - 1)));
                normals.put(BufferTools.asFloats(model.getNormals().get(face.getNormalIndices()[1] - 1)));
                normals.put(BufferTools.asFloats(model.getNormals().get(face.getNormalIndices()[2] - 1)));
            }
            vertices.flip();
            normals.flip();
            glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
            glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
            glEnableVertexAttribArray(attributeVertex);
            glVertexAttribPointer(attributeVertex, 3, GL_FLOAT, false, 0, 0L);
            glBindBuffer(GL_ARRAY_BUFFER, vboNormalHandle);
            glEnableVertexAttribArray(attributeNormal);
            glBufferData(GL_ARRAY_BUFFER, normals, GL_STATIC_DRAW);
            glNormalPointer(GL_FLOAT, 0, 0L);
            //            glBindBuffer(GL_ARRAY_BUFFER, vboColourHandle);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            cleanUp();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            cleanUp();
            System.exit(1);
        }
    }

    private static void setUpCamera() {
        cam = new EulerCamera((float) Display.getWidth() / (float) Display.getHeight(), -2.19f, 1.36f, 11.45f);
        cam.setFieldOfView(70);
        cam.applyPerspectiveMatrix();
    }

    private static void setUpShaders() {
        shaderProgram = ShaderLoader.loadShaderPair(VERTEX_SHADER_LOCATION, FRAGMENT_SHADER_LOCATION);
        attributeVertex = glGetAttribLocation(shaderProgram, "attributeVertex");
        attributeColour = glGetAttribLocation(shaderProgram, "attributeColour");
        attributeNormal = glGetAttribLocation(shaderProgram, "attributeNormal");
        uniformAmbientLight = glGetUniformLocation(shaderProgram, "uniformAmbientLight");
        uniformLightPosition = glGetUniformLocation(shaderProgram, "uniformLightPosition");
        uniformModelViewMatrix = glGetUniformLocation(shaderProgram, "uniformModelViewMatrix");
        uniformModelViewProjectionMatrix = glGetUniformLocation(shaderProgram, "uniformModelViewProjectionMatrix");
        uniformNormalMatrix = glGetUniformLocation(shaderProgram, "uniformNormalMatrix");
        uniformShininess = glGetUniformLocation(shaderProgram, "uniformShininess");
        glValidateProgram(shaderProgram);
        glUseProgram(shaderProgram);
    }

    private static void setUpLighting() {
        glShadeModel(GL_SMOOTH);
        glEnable(GL_DEPTH_TEST);
        //        glEnable(GL_LIGHTING);
        //        glEnable(GL_LIGHT0);
        //        glLightModel(GL_LIGHT_MODEL_AMBIENT, asFloatBuffer(new float[]{0.05f, 0.05f, 0.05f, 1f}));
        //        glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(new float[]{0, 0, 0, 1}));
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        //        glEnable(GL_COLOR_MATERIAL);
        //        glColorMaterial(GL_FRONT, GL_DIFFUSE);
    }

    private static void configureUniforms() {
        glUniformMatrix4(uniformModelViewProjectionMatrix, false, modelViewProjection);
        glUniformMatrix4(uniformModelViewMatrix, false, modelView);
    }

    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        cam.applyTranslations();
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_NORMAL_ARRAY);
        glDrawArrays(GL_TRIANGLES, 0, model.getFaces().size() * 3);
        glDisableClientState(GL_VERTEX_ARRAY);
        glDisableClientState(GL_NORMAL_ARRAY);
    }

    private static void logic() {
        glLoadIdentity();
        cam.applyTranslations();
        cam.applyPerspectiveMatrix();
        glGetFloat(GL_MODELVIEW_MATRIX, modelView);
        FloatBuffer projection = BufferTools.reserveData(16);
        glGetFloat(GL_PROJECTION_MATRIX, projection);
        glLoadIdentity();
        glLoadMatrix(modelView);
        glMultMatrix(projection);
        //        glGetFloat(GL_MODELVIEW_MATRIX, modelView);
        //        for (int i = 0; i < 16; i++) {
        //            System.out.print(modelView.get(i) + " ");
        //        }
        //        System.out.println();
    }

    private static void input() {
        cam.processMouse(1, 80, -80);
        cam.processKeyboard(16, 1, 1, 1);
        if (Mouse.isButtonDown(0)) {
            Mouse.setGrabbed(true);
        } else if (Mouse.isButtonDown(1)) {
            Mouse.setGrabbed(false);
        }
    }

    private static void cleanUp() {
        glDeleteProgram(shaderProgram);
        glDeleteBuffers(vboVertexHandle);
        glDeleteBuffers(vboNormalHandle);
        Display.destroy();
    }
}
