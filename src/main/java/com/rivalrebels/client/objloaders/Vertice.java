/*******************************************************************************
 * Copyright (c) 2012, 2016 Rodol Phito.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Mozilla Public License Version 2.0
 * which accompanies this distribution, and is available at
 * https://www.mozilla.org/en-US/MPL/2.0/
 *
 * Rival Rebels Mod. All code, art, and design by Rodol Phito.
 *
 * http://RivalRebels.com/
 *******************************************************************************/
package com.rivalrebels.client.objloaders;


import com.rivalrebels.client.oldstuff.Tessellator;

public class Vertice
{
	public Vec3			p;
	public Vec3			n;
	public Vec2			t;
	
	private Tessellator tes	= Tessellator.instance;
	
	public Vertice(float a, float b, float c, float d, float e, float f, float g, float h)
	{
		p = new Vec3(a, b, c);
		n = new Vec3(d, e, f);
		t = new Vec2(g, h);
	}
	
	public Vertice(Vec3 P, Vec3 N, Vec2 T)
	{
		p = P;
		n = N;
		t = T;
	}
	
	public void normalize()
	{
		double l = Math.sqrt(n.x * n.x + n.y * n.y + n.z * n.z);
		n.x /= l;
		n.y /= l;
		n.z /= l;
	}
	
	public void render()
	{
		tes.setTextureUV(t.x, t.y);
		tes.setNormal(n.x, n.y, n.z);
		tes.addVertex(p.x, p.y, p.z);
	}
	
	public void renderWireframe()
	{
		tes.addVertex(p.x, p.y, p.z);
	}
	
	@Override
	public Vertice clone()
	{
		return new Vertice(p.clone(), n.clone(), t.clone());
	}
	
	public static Vertice average(Vertice v1, Vertice v2)
	{
		Vertice v = new Vertice((v1.p.x + v2.p.x) / 2f, (v1.p.y + v2.p.y) / 2f, (v1.p.z + v2.p.z) / 2f, (v1.n.x + v2.n.x) / 2f, (v1.n.y + v2.n.y) / 2f, (v1.n.z + v2.n.z) / 2f, (v1.t.x + v2.t.x) / 2f, (v1.t.y + v2.t.y) / 2f);
		return v;
	}
	
	public void scale(Vec3 v)
	{
		p.x *= v.x;
		p.y *= v.y;
		p.z *= v.z;
	}
}

class Vec2
{
	float	x, y;
	
	public Vec2(float X, float Y)
	{
		x = X;
		y = Y;
	}
	
	@Override
	public Vec2 clone()
	{
		return new Vec2(x, y);
	}
}

class Vec3
{
	float	x, y, z;
	
	public Vec3(float X, float Y, float Z)
	{
		x = X;
		y = Y;
		z = Z;
	}
	
	@Override
	public Vec3 clone()
	{
		return new Vec3(x, y, z);
	}
}
