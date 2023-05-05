package com.example.javaexamples.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ReadJarFile {
    public static void main(String[] args) throws Exception {
        String filePath = "/Users/amol/Repos/customer-sources/Davivienda/old-davivienda-safe/client-sources/FUENTES/SafeV43_0_0/Aplicacion/SafeLibraries/public_html/WEB-INF/lib/wsclient.jar";
        JarFile jarFile = new JarFile(filePath);

        Enumeration<? extends JarEntry> enumeration = jarFile.entries();
        while (enumeration.hasMoreElements()) {
            JarEntry jarEntry = enumeration.nextElement();
            //System.out.println(jarEntry.getName());

            if (jarEntry.getName().startsWith("META-INF")) {
                System.out.println(jarEntry.getName() + " contents:");
                if (jarEntry.isDirectory()) {
                    continue;
                }

                List<String> lines = new ArrayList<>();
                try (
                        Reader reader = new InputStreamReader(jarFile.getInputStream(jarEntry));
                        BufferedReader br  = new BufferedReader(reader);
                        ) {
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        lines.add(line);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                System.out.println(lines);
                System.out.println("========");
            }
        }
    }
}
