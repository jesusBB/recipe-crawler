package com.foodplanner.recipe.crawler.recipecrawler.service.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.foodplanner.recipe.crawler.recipecrawler.validators.JamieOliverLinksValidator;

@Service("JamieOliverWebCrawler")
@ConfigurationProperties(prefix = "jamieoliverconfig")
public class JamieOliverWebCrawler implements WebCrawler {
    private final Logger LOGGER = LogManager.getLogger(JamieOliverWebCrawler.class);

    @Autowired
    JamieOliverLinksValidator jamieOliverLinksValidator;

    private final HashSet<String> links = new HashSet<>();

    int count = 0;

    private String linksRegex;

    public void getPageLinks(String url) {
        if (!links.contains(url)) {
            try {
                TimeUnit.SECONDS.sleep(8);
                if (jamieOliverLinksValidator.test(url) && links.add(url)) {
                    LOGGER.info(url + "  ------  " + ++count);
                }

                Document document = Jsoup.connect(url).get();
                // Elements linksOnPage = document
                // .select("a[href^=/food/recipes/]:not([href$=shopping-list])");
                Elements linksOnPage = document
                        .select(linksRegex);

                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"));
                }

            } catch (IOException | InterruptedException e) {
                LOGGER.error("For '" + url + "': " + e.getMessage());
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
