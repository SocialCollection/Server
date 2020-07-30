package org.socialcollection.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class Config {
    /**
     * reads values from .prop(erties) file
     *
     * @param path      path to file
     * @param filename  filename (without ending)
     * @param key       category for value
     * @return          returns value
     */
    public static String readConfig(String path, String filename, String key) {
        Properties prop = new Properties();
        InputStream input = null;
        if (FileHelper.checkFile(path, filename, "prop")) {
            try {

                input = new FileInputStream(new File(path + "/" + filename + ".prop"));

                // load a properties file
                prop.load(input);

                // get the property value and print it out
                prop.getProperty(key);

            } catch (IOException ex) {
                Logger.error(ex.getMessage());
                ex.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        Logger.error(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
        return prop.getProperty(key);
    }
    /**
     * Writes Properties in a .prop(erties) file
     *
     * @param path      path to file
     * @param filename  filename (without ending)
     * @param key       category
     * @param value     value for category
     */
    public static void writeConfig(String path, String filename, String key, String value) {
        if (FileHelper.checkFile(path, filename, "prop")) {
            /**
             * init Properties and Output stream
             */

            Properties properties = new Properties();
            OutputStream outputStream = null;

            /**
             * set output- directory and file name
             */
            try {
                outputStream =
                        new FileOutputStream(path + "/" + filename + ".prop", true);
            } catch (FileNotFoundException e) {
                Logger.error(e.getMessage());
                e.printStackTrace();
            }

            /**
             * set first properties to enter in file
             * like :
             * var1=example
             */
            properties.setProperty(key, value);

            /**
             * saving properties file and content
             */
            try {
                properties.store(outputStream, null);
            } catch (IOException e1) {
                Logger.error(e1.getMessage());
                e1.printStackTrace();
            }
        }
    }
}
class ReadFile {

    /**
     * <p>read.</p>
     *
     * @param path a {@link java.lang.String} object.
     * @param filename a {@link java.lang.String} object.
     * @param ending a {@link java.lang.String} object.
     * @return a {@link java.util.List} object.
     */
    public static List<String> read(String path, String filename, String ending) {
        ArrayList<String> content = new ArrayList<>();
        try {
            File fileDir =
                    new File(String.format("%s/%s.%s", path, filename, ending));

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileDir), StandardCharsets.UTF_8));

            String str;

            while ((str = in.readLine()) != null) {
                System.out.println(str);
                Objects.requireNonNull(content).add(str);
            }
            in.close();
            return content;
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}