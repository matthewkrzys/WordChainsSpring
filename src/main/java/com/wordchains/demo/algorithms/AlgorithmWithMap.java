package com.wordchains.demo.algorithms;

import com.wordchains.demo.model.Word;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AlgorithmWithMap implements Algorithm {
    final static Logger logger = LoggerFactory.getLogger(AlgorithmWithMap.class);
    final static String TAG = AlgorithmWithMap.class.getName();

    private Collection dictionary;
    private Word word;
    private List<String> listResult;
    private int counterSetChar;
    private int counterChar;
    private int errorCounter;
    private int counterTrialFindWords;
    private StringBuilder currentWord;
    private StringBuilder lastGoogWord;
    private StringBuilder firstWord;
    private StringBuilder secondWord;
    private String message;

    @Override
    public void setDictionary(Collection collection) {
        dictionary = collection;
    }

    @Override
    public Collection getResult(StringBuilder firstWord, StringBuilder secondWord) {
        logger.info(TAG + " firstWord " + firstWord + " secondWord " + secondWord);
        this.firstWord=firstWord;
        this.secondWord=secondWord;
        createModel(firstWord);
        return checkWordsinDictionary(firstWord,secondWord)? null : findResultWords();
    }

    private boolean checkWordsinDictionary(StringBuilder firstWord, StringBuilder secondWord) {
        if(!dictionary.contains(firstWord.toString())){
            logger.info(TAG+" "+firstWord+" is not in dictionary");
            message=firstWord+" is not in dictionary";
            return true;
        }
        if(!dictionary.contains(secondWord.toString())){
            logger.info(TAG+" "+secondWord+" is not in dictionary");
            message=secondWord+" is not in dictionary";
            return true;
        }
        return false;
    }

    public Collection getDictionary() {
        return dictionary;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    private void prepareVariables(){
        listResult = new ArrayList<>();
        counterTrialFindWords =0;
        setValueInCounters(0,1,0);
        currentWord = new StringBuilder(firstWord);
        logger.info(TAG + " currentWord " + currentWord);
        lastGoogWord = new StringBuilder(firstWord);
        logger.info(TAG + " lastGoodWord " + lastGoogWord);
        listResult.add(firstWord.toString());
    }
    private Collection findResultWords() {
        prepareVariables();
        while (counterSetChar != firstWord.length()) {
            logger.info(TAG + " counterSetChar " + counterSetChar);
            if (word.getCheckCharInWord()[counterChar])
                counterChar = (counterChar + 1) % firstWord.length();
            else {
                currentWord.setCharAt(counterChar, secondWord.charAt(counterChar));
                if (dictionary.contains(currentWord.toString()) && !listResult.contains(currentWord.toString())) {
                    isInDictionary();
                    if (lastGoogWord.toString().equals(secondWord.toString())) {
                        break;
                    }
                } else {
                    isNotInDictionary();
                }
                if (errorCounter == currentWord.length()) {
                    notFindWord();
                }
                if (counterTrialFindWords ==currentWord.length()){
                    logger.info("I can not find words");
                    message="I can not find words";
                    return null;
                }
            }
        }
        return listResult;

    }
    private void setValueInCounters(int counterSetChar, int counterChar, int errorCounter){
        this.counterSetChar = counterSetChar;
        this.counterChar = counterChar;
        this.errorCounter = errorCounter;
    }
    private void notFindWord(){
        lastGoogWord.replace(0, firstWord.length(), firstWord.toString());
        currentWord.replace(0, firstWord.length(), firstWord.toString());
        word.setCheckCharInWord(new boolean[firstWord.length()]);
        counterTrialFindWords++;
        setValueInCounters(0,2,0);
        listResult = new ArrayList<>();
        listResult.add(firstWord.toString());
    }
    private void isInDictionary(){
        logger.info(TAG + " word " + counterChar + " is in dictionary");
        word.getCheckCharInWord()[counterChar] = true;
        setValueInCounters(counterSetChar++,1,0);
        lastGoogWord.replace(0, currentWord.length(), currentWord.toString());
        logger.info(TAG + " lastGoodWord " + lastGoogWord);
        listResult.add(lastGoogWord.toString());
    }
    private void isNotInDictionary(){
        errorCounter++;
        logger.info(TAG + " word " + counterChar + " is not in dictionary");
        counterChar = (counterChar + 1) % firstWord.length();
        currentWord.replace(0, lastGoogWord.length(), lastGoogWord.toString());
    }

    private void createModel(StringBuilder firstWord) {
        word = new Word(firstWord);
    }

    @Override
    public String getMessage() {
        logger.info(TAG+" message "+message);
        return message;
    }
}
