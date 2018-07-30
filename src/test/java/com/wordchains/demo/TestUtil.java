package com.wordchains.demo;

import java.io.*;

public class TestUtil {

    static String jsonContent(String fileName) {
        StringBuilder b = new StringBuilder();
        File file = new File(System.getProperty("user.dir")
                +"/src/test/resources/com.wordchains.demo/"+fileName+".json");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        br.lines().forEach(b::append);
        return b.toString();
    }
}
