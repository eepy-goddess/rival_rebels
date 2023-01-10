package com.rivalrebels.common.init;

import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.net.URI;

public class FileRW {
    public static String read(File file)
    {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                text.append(line);
                text.append("\n");
            }
        } catch (Exception e) {
        }
        return text.toString();
    }

    public static byte[] readbytes(File file)
    {
        FileInputStream fis = null;
        byte[] data = new byte[0];
        try
        {
            fis = new FileInputStream(file);
            data = IOUtils.toByteArray(fis);
        }
        catch (Exception e) {}
        finally
        {
            try
            {
                if (fis != null) fis.close();
            }
            catch (IOException e) {}
        }
        return data;
    }

    public static void write(File file, String text)
    {
        try (Writer output = new BufferedWriter(new FileWriter(file))) {
            output.write(text);
        } catch (IOException e) {
        }
    }

    public static void writebytes(File file, byte[] data)
    {
        FileOutputStream fos = null;
        try
        {
            file.delete();
            file.createNewFile();
            fos = new FileOutputStream(file);
            fos.write(data);
        }
        catch (Exception e) {}
        finally
        {
            try
            {
                if (fos!=null) fos.close();
            }
            catch (IOException e) {}
        }
    }

    public static boolean goToURL(String string)
    {
        try
        {
            Class<?> oclass = Class.forName("java.awt.Desktop");
            Object object = oclass.getMethod("getDesktop", new Class[0]).invoke((Object) null, new Object[0]);
            oclass.getMethod("browse", new Class[] { URI.class }).invoke(object, new Object[] { new URI(string) });
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static byte[] getBytesString(String str)
    {
        char[] buffer = str.toCharArray();
        byte[] bytes = new byte[buffer.length];
        for (int i = 0; i < bytes.length; i++)
        {
            bytes[i] = (byte) buffer[i];
        }
        return bytes;
    }
    public static String getStringBytes(byte[] bytes) {
        char[] buffer = new char[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            buffer[i] = (char) bytes[i];
         //   if(buffer[i] == 'ﾧ') buffer[i] = '§';
        }
        return new String(buffer);
    }
}
