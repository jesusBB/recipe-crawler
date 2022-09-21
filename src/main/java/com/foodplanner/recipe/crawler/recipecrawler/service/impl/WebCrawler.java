package com.foodplanner.recipe.crawler.recipecrawler.service.impl;

import java.util.HashSet;

import org.springframework.stereotype.Component;

@Component
public interface WebCrawler {
    void getPageLinks(String url);

    HashSet<String> getLinks();
}
