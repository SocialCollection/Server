package org.socialcollection.util;

public class Logger {
    /**
     * <p>msg.</p>
     *
     * @param content a {@link java.lang.String} object.
     */
    public static void msg(String content) {
        logger("msg", "content");
    }

    /**
     * <p>error.</p>
     *
     * @param content a {@link java.lang.String} object.
     */
    public static void error(String content) {
        logger("error", content);
    }

    /**
     * <p>warning.</p>
     *
     * @param content a {@link java.lang.String} object.
     */
    public static void warning(String content) {
        logger("warning", content);
    }

    private static void logger(String type, String content) {
        if (type.equals("warning")) {
            WriteFile.writer("logs", "LOG", "log",
                    String.format("[WARNING]: %s \n", content));
        } else if (type.equals("error")) {
            WriteFile.writer("logs", "LOG", "log",
                    String.format("[ERROR]: %s \n", content));
        } else if (type.equals("msg")){
            WriteFile.writer("logs", "LOG", "log",
                    String.format("[MESSAGE]: %s \n", content));
        }else {
            System.out.println("WRONG LOG parameter");
        }
    }

}