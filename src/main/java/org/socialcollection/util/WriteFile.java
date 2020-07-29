package org.socialcollection.util;

import java.io.FileWriter;

public class WriteFile {
  /*
  Reference https://www.journaldev.com/20891/java-filewriter-example
   */
        /**
         * <p>writer.</p>
         *
         * @param path a {@link java.lang.String} object.
         * @param filename a {@link java.lang.String} object.
         * @param ending a {@link java.lang.String} object.
         * @param content a {@link java.lang.String} object.
         */
        public static void writer(String path, String filename, String ending,
                                  String content) {
            if (FileHelper.checkFile(path, filename, ending)) {
                try (FileWriter fileWriter = new FileWriter(
                        String.format("%s/%s.%s", path, filename, ending), true)) {
                    fileWriter.write(content);
                } catch (Exception e) {
                    Logger.error(e.getMessage());
                    e.printStackTrace();
                }
            } else {
                Logger.error("File not found");
            }
        }
    }

