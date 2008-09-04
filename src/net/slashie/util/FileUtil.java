package net.slashie.util;

import java.io.*;

public class FileUtil {

    public static void deleteFile(String what){
    	new File(what).delete();
    }

    public static void copyFile(File origen, File destino) throws Exception {
        FileInputStream fis  = new FileInputStream(origen);
        FileOutputStream fos = new FileOutputStream(destino);
        byte[] buf = new byte[1024];
        int i = 0;
        while((i=fis.read(buf))!=-1) {
          fos.write(buf, 0, i);
          }
        fis.close();
        fos.close();
    }

    public static BufferedReader getReader(String fileName) throws IOException{
        BufferedReader x = new BufferedReader(new FileReader(fileName));
		return x;
	}

	public static BufferedWriter getWriter(String fileName) throws IOException{
        BufferedWriter x = new BufferedWriter(new FileWriter(fileName));
		return x;
	}

	public static boolean fileExists(String filename) {
		return new File(filename).exists();
	}
	
	public static String replaceFilename(String fullPath, String newFilename){
		int lastIndex = fullPath.lastIndexOf("/");
		if (lastIndex == -1)
			return fullPath;
		else
			return fullPath.substring(0,lastIndex+1).concat(newFilename);
	}
	
	public static void main (String args[]){
		System.out.println(replaceFilename("/res/public/slashie.gig", "santi.jpg"));
	}
}