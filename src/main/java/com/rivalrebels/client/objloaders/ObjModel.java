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

import java.io.InputStream;
import java.nio.ByteBuffer;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import com.rivalrebels.RivalRebels;

public class ObjModel
{
	private Vector3[] verts;
	private short[] tris;
	public ObjModel(String path)
	{
		ByteBuffer buf = null;
		try
		{
			InputStream in = RivalRebels.instance.getClass().getResourceAsStream("/assets/rivalrebels/models/obj/" + path);
			byte[] data = new byte[(in.read()<<24)|(in.read()<<16)|(in.read()<<8)|(in.read())];
			in.read(data);
			buf = ByteBuffer.wrap(data);
		}
		catch (Exception e)
		{
			//complain
		}
		if (buf != null)
		{
			verts = new Vector3[buf.getShort()];
			for (int i = 0; i < verts.length; i++)
			{
				verts[i] = new Vector3(buf.getFloat(),buf.getFloat(),buf.getFloat(),(buf.get()-127)/127f,(buf.get()-127)/127f,(buf.get()-127)/127f,(buf.get()-127)/127f,(buf.get()-127)/127f);
			}
			tris = new short[buf.getInt()*3];
			for (int i = 0; i < tris.length; i++)
			{
				tris[i] = buf.getShort();
			}
		}
		else
		{
			//complain
		}
	}
	
	public void render()
	{
		GL11.glBegin(GL11.GL_TRIANGLES);
		for (int i = 0; i < tris.length; i++)
		{
			//GL11.
		}
		GlStateManager.enableTexture2D();

	}
	
	private class Vector3
	{
		public float x,y,z;//pos
		public float i,j,k;//norm
		public float u,v;
		public Vector3(float X,float Y,float Z,float I,float J,float K,float U,float V)
		{
			x=X;
			y=Y;
			z=Z;
			i=I;
			j=J;
			k=K;
			u=U;
			v=V;
		}
	}
}