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

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.rivalrebels.RivalRebels;

public class ModelFromObj
{
	Triangle[]		pa;
	public String	name;
	
	public ModelFromObj(Triangle[] PA)
	{
		pa = PA;
	}
	
	public static ModelFromObj readObjFile(String path) throws Exception
	{
		String text = "";
		File file = new File("mods");
		String[] files = file.list();
		text = readZippedFile("/assets/rivalrebels/models/obj/" + path);
		String[] lines = text.split("\n");
		String name = "";
		ArrayList<Triangle> tri = new ArrayList<>();
		ArrayList<Vec3> v = new ArrayList<>();
		ArrayList<Vec3> nv = new ArrayList<>();
		ArrayList<Vec2> tv = new ArrayList<>();
		for (String line : lines) {
			if (line.startsWith("vt")) {
				String[] tex = line.split(" ");
				tv.add(new Vec2(Float.parseFloat(tex[1]), 1f - Float.parseFloat(tex[2])));
			} else if (line.startsWith("vn")) {
				String[] norm = line.split(" ");
				nv.add(new Vec3(Float.parseFloat(norm[1]), Float.parseFloat(norm[2]), Float.parseFloat(norm[3])));
			} else if (line.startsWith("v")) {
				String[] vert = line.split(" ");
				v.add(new Vec3(Float.parseFloat(vert[1]), Float.parseFloat(vert[2]), Float.parseFloat(vert[3])));
			} else if (line.startsWith("f")) {
				String[] coords = line.split(" ");
				Vertice[] vs = new Vertice[coords.length - 1];
				for (int j = 1; j < coords.length; j++) {
					String[] v1 = coords[j].split("/");
					vs[j - 1] = new Vertice(v.get(Integer.parseInt(v1[0]) - 1).clone(), nv.get(Integer.parseInt(v1[2]) - 1).clone(), tv.get(Integer.parseInt(v1[1]) - 1).clone());
				}
				tri.add(new Triangle(vs));
			} else if (line.startsWith("o")) {
				String[] l = line.split(" ");
				name = l[1];
			}
		}
		
		Triangle[] polygon = new Triangle[tri.size()];
		
		for (int i = 0; i < tri.size(); i++)
		{
			polygon[i] = tri.get(i);
		}
		ModelFromObj modelFromObj = new ModelFromObj(polygon);
		modelFromObj.name = name;
		return modelFromObj;
	}
	
	public void renderWireframe()
	{
		glDisable(GL_TEXTURE_2D);

		for (Triangle triangle : pa) {
			triangle.renderWireframe();
		}
		
		glEnable(GL_TEXTURE_2D);
	}
	
	public void normalize()
	{
		for (Triangle triangle : pa) {
			triangle.normalize();
		}
	}
	
	public void scale(float x, float y, float z)
	{
		for (Triangle triangle : pa) {
			triangle.scale(new Vec3(x, y, z));
		}
	}
	
	public void render()
	{

		/*
		 * if (!render) { Minecraft.getMinecraft().thePlayer.sendChatMessage("3D ERROR: Please close Minecraft, empty the config folder and remove all other mods, then re-open Minecraft.");
		 * Minecraft.getMinecraft().thePlayer.sendChatMessage("If you still get this message contact rodol. http://www.rivalrebels.com"); } else
		 */
		for (Triangle triangle : pa) {
			triangle.render();
		}
	}
	
	public void refine()
	{
		ArrayList<Triangle> lp = new ArrayList<>();
		for (Triangle value : pa) {
			Triangle[] p = value.refine();
			for (Triangle triangle : p) {
				lp.add(triangle);
			}
		}
		pa = new Triangle[lp.size()];
		for (int i = 0; i < lp.size(); i++)
		{
			pa[i] = lp.get(i);
		}
	}
	
	
	public static String readZippedFile(String file)
	{
		StringBuilder source = new StringBuilder();
		String line;
		try
		{
			InputStream in = RivalRebels.instance.getClass().getResourceAsStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			while ((line = reader.readLine()) != null)
				source.append(line).append('\n');
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return source.toString();
	}
}
