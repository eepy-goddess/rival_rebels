package com.rivalrebels.client.render;

import org.lwjgl.opengl.GL11;

// TEMP CLASS to help with finding the correct transformations with the models
public class ModelDebugTransform {

    public static float translateX = 0;
    public static float translateY = 0;
    public static float translateZ = 0;

    public static float rotateX = 0;
    public static float rotateY = 0;
    public static float rotateZ = 0;

    public static void apply() {
        GL11.glTranslatef(translateX, translateY, translateZ);

        GL11.glRotatef(rotateX, 1, 0, 0);
        GL11.glRotatef(rotateY, 0, 1, 0);
        GL11.glRotatef(rotateZ, 0, 0, 1);
    }

    public static void reset() {
        translateX = 0;
        translateY = 0;
        translateZ = 0;

        rotateX = 0;
        rotateY = 0;
        rotateZ = 0;
    }
}