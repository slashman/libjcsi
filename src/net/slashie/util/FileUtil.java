package net.slashie.util;

import java.io.*;

/**
 * Eases input and output with files.
 * @author Santiago Zapata
 */
public class FileUtil {

    /**
     * Deletes the specified file
     * @param fileName The file to be deleted
     */
    public static void deleteFile(String fileName) {
        new File(fileName).delete();
    }

    /**
     * Duplicates a file contents into another file
     *
     * @param source The file to take the data from
     * @param destination The filename to write the data to
     * @throws IOException
     */
    public static void copyFile(File source, File destination) throws IOException {
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(destination);
        byte[] buf = new byte[1024];
        int i = 0;
        while ((i = fis.read(buf)) != -1) {
            fos.write(buf, 0, i);
        }
        fis.close();
        fos.close();
    }

    /**
     * Acquires a Buffered reader usable to read the contents of a file
     *
     * @param fileName Name of the file that will be read
     * @return The BufferedReader
     * @throws IOException
     */
    public static BufferedReader getReader(String fileName) throws IOException {
        BufferedReader x = new BufferedReader(new FileReader(fileName));
        return x;
    }

    /**
     * Acquires a Buffered Writer usable to write a new file
     *
     * @param fileName The name of the file to be written
     * @return
     * @throws IOException
     */
    public static BufferedWriter getWriter(String fileName) throws IOException {
        BufferedWriter x = new BufferedWriter(new FileWriter(fileName));
        return x;
    }

    /**
     * Checks if the given file exists
     *
     * @param filename
     * @return
     */
    public static boolean fileExists(String filename) {
        return new File(filename).exists();
    }

    /**
     * Replaces the file part of a full path with a new file name and returns
     * the modified full path
     *
     * @param fullPath
     * @param newFilename
     * @return
     */
    public static String replaceFilename(String fullPath, String newFilename) {
        int lastIndex = fullPath.lastIndexOf("/");
        if (lastIndex == -1) {
            return fullPath;
        } else {
            return fullPath.substring(0, lastIndex + 1).concat(newFilename);
        }
    }

    /**
     * Test Unit, can be changed
     * @param args
     */
    public static void main(String args[]) {
        System.out.println(replaceFilename("/res/public/slashie.gig", "santi.jpg"));
    }
}