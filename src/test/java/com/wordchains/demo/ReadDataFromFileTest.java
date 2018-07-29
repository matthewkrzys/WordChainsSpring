package com.wordchains.demo;

import com.wordchains.demo.reader.ReadDataFromFile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReadDataFromFileTest {
    Collection collection;
    @Mock
    ReadDataFromFile readDataFromFile;
    @Before
    public void runBefore(){
        collection=new ArrayList();
        collection.add("cat");
        collection.add("dog");
        collection.add("cot");
        collection.add("cog");
        collection.add("fog");
        collection.add("log");
    }
    @Test
    public void readData_Test(){
        when(readDataFromFile.readData(any(String.class),any(Long.class))).thenReturn(collection);
        Collection result=readDataFromFile.readData("wordlist.txt",7);
        Assert.assertTrue(result.containsAll(collection));
    }
}
