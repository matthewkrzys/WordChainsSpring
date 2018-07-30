package com.wordchains.demo.algorithms;

import java.util.Collection;

public interface Algorithm {
    void setDictionary(Collection collection);
    Collection getResult(StringBuilder firstWord, StringBuilder secondWord);
    Object getMessage();
}
