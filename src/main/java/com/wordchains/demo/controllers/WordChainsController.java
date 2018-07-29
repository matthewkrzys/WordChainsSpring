package com.wordchains.demo.controllers;

import com.wordchains.demo.algorithms.AlgorithmWithMap;
import com.wordchains.demo.reader.ReadDataFromWeb;
import com.wordchains.demo.service.WordChainsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
public class WordChainsController {
    private WordChainsService wordChainsService;

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
        wordChainsService.setAlgorithm(new AlgorithmWithMap());
        wordChainsService.setReader(new ReadDataFromWeb());
        return (List<String>)wordChainsService.findwords(firstword,secondword);
    }
}
