package com.rivalrebels.client.renderhelper;

public class Vertice {
    public float	x;
    public float	y;
    public float	z;
    public float[] idk = new float[]{
            x, y, z
    };

    public Vertice(float X, float Y, float Z)
    {
        x = X;
        y = Y;
        z = Z;
    }

    public Vertice normalize()
    {
        float l = (float) Math.sqrt(x * x + y * y + z * z);
        x /= l;
        y /= l;
        z /= l;
        return this;
    }
    public float[] toInt(){
        return idk;
    }
}
