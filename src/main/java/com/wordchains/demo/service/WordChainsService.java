package com.wordchains.demo.service;

import com.wordchains.demo.algorithms.Algorithm;
import com.wordchains.demo.logic.Manager;
import com.wordchains.demo.reader.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class WordChainsService {

    private Manager manager;
    private Reader reader;
    private Algorithm algorithm;
    final static Logger logger = LoggerFactory.getLogger(WordChainsService.class);
    final static String TAG= WordChainsService.class.getName();

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
        logger.info(TAG+" firstword "+firstWord);
        logger.info(TAG+" secondword "+secondWord);
        manager.setWords(firstWord, secondWord);
        if (manager.checkLengthWords()) {
            result = manager.getResult(reader.readData("http://codekata.com/data/wordlist.txt", firstWord.length()), algorithm);
            if (result != null) {
                logger.info(TAG+" Algorithm return collection");
                return result;
            }
            else {
                logger.info(TAG+" Algorithm return null");
                return Arrays.asList((String)algorithm.getMessage());
            }
        } else {
            return Arrays.asList("Words must be this same length");
        }
    }
}
