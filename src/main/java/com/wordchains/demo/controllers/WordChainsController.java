package com.wordchains.demo.controllers;

import com.wordchains.demo.algorithms.AlgorithmWithMap;
import com.wordchains.demo.reader.ReadDataFromWeb;
import com.wordchains.demo.service.WordChainsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
public class WordChainsController {
    private WordChainsService wordChainsService;
    final static Logger logger = LoggerFactory.getLogger(WordChainsController.class);
    final static String TAG= WordChainsController.class.getName();

    public WordChainsController(WordChainsService wordChainsService) {
        this.wordChainsService = wordChainsService;
    }

    @RequestMapping(value = "/wordchains",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"},
            params = {"firstword", "secondword"}
    )
    @ResponseBody
    public Collection findwords(@RequestParam("firstword") String firstword,
                                @RequestParam("secondword") String secondword) {
        logger.info(TAG+" firstword "+firstword);
        logger.info(TAG+" secondword "+secondword);
        wordChainsService.setAlgorithm(new AlgorithmWithMap());
        wordChainsService.setReader(new ReadDataFromWeb());
        return (List<String>)wordChainsService.findwords(firstword,secondword);
    }
    @RequestMapping("/")
    public @ResponseBody String index() {
        return "Hello User";
    }
}
