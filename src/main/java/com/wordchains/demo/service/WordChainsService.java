package com.wordchains.demo.service;

import com.wordchains.demo.algorithms.Algorithm;
import com.wordchains.demo.logic.Manager;
import com.wordchains.demo.reader.Reader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class WordChainsService {

    private Manager manager;
    private Reader reader;
    private Algorithm algorithm;

    public WordChainsService(Manager manager) {
        this.manager = manager;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public Collection findwords(String firstWord, String secondWord) {
        Collection result;
        manager.setWords(firstWord, secondWord);
        if (manager.checkLengthWords()) {
            result = manager.getResult(reader.readData("http://codekata.com/data/wordlist.txt", firstWord.length()), algorithm);
            if (result != null) {
                return result;
            }
        } else {
            return Arrays.asList("Bad parameters");
        }
        return Arrays.asList("Error");
    }
}
