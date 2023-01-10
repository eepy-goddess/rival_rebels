package com.rivalrebels;

import net.minecraft.launchwrapper.IClassTransformer;

public class ASMTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if(transformedName.equals("net.minecraft.network.NetHandlerPlayServer"))
        {
            int d = 3;
            for (int i = 0; i < bytes.length - 8 && d != 0; i++) {
                if(bytes[i+2] == 0x00
                        && bytes[i+3] == 0x00
                        && bytes[i+4] == 0x00
                        && bytes[i+5] == 0x00
                        && bytes[i+6] == 0x00
                        && bytes[i+7] == 0x00)
                {
                    if (bytes[i] == (byte) 0x40 && bytes[i+1] == (byte) 0x59) //Moved too quickly
                    {
                        bytes[i]   = (byte) 0x7f;
                        bytes[i+1] = (byte) 0xf0;
                        d--;
                    }
                    else if (bytes[i] == (byte) 0x3f && bytes[i+1] == (byte) 0xb0) //Moved wrongly
                    {
                        bytes[i]   = (byte) 0x40;
                        bytes[i+1] = (byte) 0x50;
                        d--;
                    }
                    else if (bytes[i] == (byte) 0xbf && bytes[i+1] == (byte) 0xa0) //Flying
                    {
                        bytes[i]   = (byte) 0x7f;
                        bytes[i+1] = (byte) 0xf0;
                        d--;
                    }
                }
            }
        }
        /*if (transformedName.equals("net.minecraft.world.biome.Biome"))
		{
			int d = 3;
			for (int i = 0; i < bytes.length - 4 && d != 0; i++)
			{
				if (bytes[i] == (byte) 0xBF && bytes[i+1] == (byte) 0x00 && bytes[i+2] == (byte) 0x00 && bytes[i+3] == (byte) 0x00)
				{
					bytes[i]   = (byte) 0x3F;
					d--;
				}
				else if (bytes[i] == (byte) 0xBF && bytes[i+1] == (byte) 0x80 && bytes[i+2] == (byte) 0x00 && bytes[i+3] == (byte) 0x00)
				{
					bytes[i]   = (byte) 0x3F;
					d--;
				}
				else if (bytes[i] == (byte) 0xBF && bytes[i+1] == (byte) 0xE6 && bytes[i+2] == (byte) 0x66 && bytes[i+3] == (byte) 0x66)
				{
					bytes[i]   = (byte) 0x3F;
					d--;
				}

			}
		}*/
        return bytes;
    }
}
