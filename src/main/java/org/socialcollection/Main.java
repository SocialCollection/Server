package org.socialcollection;

import org.socialcollection.connectors.Connectors;

public class Main {
    static final String PREFIX = "[SERVER] ";
    public static void main(String[] args) {
        System.out.printf("%sstarting ....%n", PREFIX);
        System.out.printf("%sloading connectors%n", PREFIX);
        Connectors.load();
    }
}
