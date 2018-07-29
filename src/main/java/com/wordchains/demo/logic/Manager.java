package com.wordchains.demo.logic;

import com.wordchains.demo.algorithms.Algorithm;
import com.wordchains.demo.reader.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.Collection;

@Service
public class Manager {
    final static Logger logger = LoggerFactory.getLogger(Manager.class);
    final static String TAG= Manager.class.getName();

    private StringBuilder firstWord;
    private StringBuilder secondWord;

    public void setWords(String[] args){
        firstWord=new StringBuilder(args[0]);
        logger.info(TAG+" Insert firstWord "+args[0]);
        secondWord=new StringBuilder(args[1]);
        logger.info(TAG+" Insert secondWord "+args[1]);
    }
    public void setWords(String firstWord,String secondWord){
        this.firstWord=new StringBuilder(firstWord);
        logger.info(TAG+" Insert firstWord "+firstWord);
        this.secondWord=new StringBuilder(secondWord);
        logger.info(TAG+" Insert secondWord "+secondWord);
    }
    public Collection getWordsData(Reader reader, String path){
        logger.info(TAG+" Path "+path);
        return reader.readData(path,firstWord.length());
    }

    public boolean checkLengthWords(){
        return firstWord.length()==secondWord.length();
    }
    public Collection getResult(Collection collection, Algorithm algorithm){
        algorithm.setDictionary(collection);
        return algorithm.getResult(firstWord,secondWord);
    }
}
