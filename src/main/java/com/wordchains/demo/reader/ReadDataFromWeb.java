package com.wordchains.demo.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ReadDataFromWeb implements Reader{

    @Override
    public Collection readData(String path, long lengthWord) {
        return readFromWeb(path,lengthWord);
    }
    private Collection readFromWeb(String url,long length){
        List<String> list = null;
        try {
            list=new ArrayList<>();
            URL oracle = new URL(url);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));
            list=in.lines().filter((s)->s.length()==length).collect(Collectors.toList());
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
