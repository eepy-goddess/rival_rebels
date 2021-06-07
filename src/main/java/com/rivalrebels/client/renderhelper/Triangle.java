package com.rivalrebels.client.renderhelper;


public class Triangle {
    public Vertice			v1;
    public Vertice			v2;
    public Vertice			v3;
    public TextureVertice	t1;
    public TextureVertice	t2;
    public TextureVertice	t3;

    public Triangle(Vertice V1, Vertice V2, Vertice V3, TextureVertice T1, TextureVertice T2, TextureVertice T3)
    {
        v1 = V1;
        v2 = V2;
        v3 = V3;
        t1 = T1;
        t2 = T2;
        t3 = T3;
    }
}
