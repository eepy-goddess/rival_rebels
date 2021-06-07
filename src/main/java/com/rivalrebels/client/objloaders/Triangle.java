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
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glLineWidth;

public class Triangle
{
	public Vertice[]	pa;
	private Tessellator tes	= Tessellator.instance;
	
	public Triangle(Vertice[] PA)
	{
		if (PA.length != 3) throw new IllegalArgumentException("Invalid Triangle! Specified Vec3 Array must have 3 Vec3s");
		pa = PA;
	}
	
	public void render()
	{
		net.minecraft.client.renderer.Tessellator.getInstance().getBuffer().begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX);
		for (int i = 0; i < pa.length; i++)
		{
			pa[i].render();
		}
		tes.draw();
	}
	
	public void renderWireframe()
	{
		glLineWidth(2);
		net.minecraft.client.renderer.Tessellator.getInstance().getBuffer().begin(GL_LINE_LOOP, DefaultVertexFormats.POSITION_TEX);
		for (int i = 0; i < pa.length; i++)
		{
			pa[i].renderWireframe();
		}
		tes.draw();
	}
	
	public void normalize()
	{
		for (int i = 0; i < pa.length; i++)
		{
			pa[i].normalize();
		}
	}
	
	public void scale(Vec3 v)
	{
		for (int i = 0; i < pa.length; i++)
		{
			pa[i].scale(v);
		}
	}
	
	public Triangle[] refine()
	{
		Triangle[] p = new Triangle[4];
		if (pa.length == 3)
		{
			Vertice mp1 = Vertice.average(pa[0], pa[1]);
			Vertice mp2 = Vertice.average(pa[1], pa[2]);
			Vertice mp3 = Vertice.average(pa[2], pa[0]);
			p[0] = new Triangle(new Vertice[] { pa[0].clone(), mp1.clone(), mp3.clone() });
			p[1] = new Triangle(new Vertice[] { pa[1].clone(), mp2.clone(), mp1.clone() });
			p[2] = new Triangle(new Vertice[] { pa[2].clone(), mp3.clone(), mp2.clone() });
			p[3] = new Triangle(new Vertice[] { mp1.clone(), mp2.clone(), mp3.clone() });
		}
		return p;
	}
}
