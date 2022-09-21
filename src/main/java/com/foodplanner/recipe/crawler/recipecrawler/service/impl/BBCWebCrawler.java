package com.foodplanner.recipe.crawler.recipecrawler.service.impl;

import java.io.IOException;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service("BBCWebCrawler")
@ConfigurationProperties(prefix = "bbcconfig")
public class BBCWebCrawler implements WebCrawler {
    private final Logger LOGGER = LogManager.getLogger(BBCWebCrawler.class);

    private final HashSet<String> links = new HashSet<>();

    int count = 0;

    private String linksRegex;

    public void getPageLinks(String url) {
        if (!links.contains(url)) {
            try {
                if (links.add(url)) {
                    System.out.println(url + "  ------  " + ++count);
                }

                Document document = Jsoup.connect(url).get();
                LOGGER.info(linksRegex);
                // Elements linksOnPage = document
                // .select("a[href^=/food/recipes/]:not([href$=shopping-list])");
                Elements linksOnPage = document
                        .select("a[href~=" + linksRegex + "]");
                linksOnPage.parallelStream().forEach(link -> getPageLinks(link.attr("abs:href")));

            } catch (IOException e) {
                System.err.println("For '" + url + "': " + e.getMessage());
            }
        }

    }

    public HashSet<String> getLinks() {
        return links;
    }

    public String getLinksRegex() {
        return linksRegex;
    }

    public void setLinksRegex(String linksRegex) {
        this.linksRegex = linksRegex;
    }

}
