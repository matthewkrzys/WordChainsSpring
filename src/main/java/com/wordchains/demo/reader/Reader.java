package com.wordchains.demo.reader;

import java.util.Collection;

public interface Reader {
    Collection readData(String path, long lengthWord);
}
