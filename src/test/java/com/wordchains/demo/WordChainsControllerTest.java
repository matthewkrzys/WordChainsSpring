package com.wordchains.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.wordchains.demo.TestUtil.jsonContent;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WordChainsControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void checkStartMessage() throws Exception {
        mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello User")))
                .andDo(print());
    }

    @Test
    public void test1() throws Exception {
        addParams("cat", "dog", "data-1");
    }

    @Test
    public void test2() throws Exception {
        addParams("lead", "gold", "data-2");
    }

    @Test
    public void test3() throws Exception {
        addParams("gold", "lead", "data-3");
    }

    public void addParams(String word1, String word2, String fileName) throws Exception {
        mockMvc
                .perform(get("/wordchains")
                        .param("firstword", word1)
                        .param("secondword", word2))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent(fileName)))
                .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
                .andDo(print());
    }

}
