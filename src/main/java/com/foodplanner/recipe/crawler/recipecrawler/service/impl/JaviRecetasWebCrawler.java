package com.foodplanner.recipe.crawler.recipecrawler.service.impl;

import java.io.IOException;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service("JaviRecetasWebCrawler")
@ConfigurationProperties(prefix = "javirecetasconfig")
public class JaviRecetasWebCrawler implements WebCrawler {
    private final Logger LOGGER = LogManager.getLogger(JaviRecetasWebCrawler.class);

    private final HashSet<String> links = new HashSet<>();

    int count = 0;

    private String linksRegex;

    /*
     * public JaviRecetasWebCrawler() {
     * links = new HashSet<String>();
     * }
     */

    public HashSet<String> getLinks() {
        return links;
    }

    public void getPageLinks(String url) {
        // 4. Check if you have already crawled the URLs
        // (we are intentionally not checking for duplicate content in this example)
        if (!links.contains(url)) {
            try {
                // 4. (i) If not add it to the index
                if (links.add(url)) {
                    LOGGER.info(url + "  ------  " + ++count);
                }

                // 2. Fetch the HTML code
                Document document = Jsoup.connect(url).get();
                // 3. Parse the HTML to extract links to other URLs
                Elements linksOnPage = document
                        .select(linksRegex);

                // 5. For each extracted URL... go back to Step 4.
                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println("For '" + url + "': " + e.getMessage());
            }
        }
    }

    public String getLinksRegex() {
        return linksRegex;
    }

    public void setLinksRegex(String linksRegex) {
        this.linksRegex = linksRegex;
    }

}
