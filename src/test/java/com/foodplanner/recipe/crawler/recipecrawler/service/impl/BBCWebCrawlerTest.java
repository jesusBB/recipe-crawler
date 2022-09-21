package com.foodplanner.recipe.crawler.recipecrawler.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
// @ContextConfiguration(classes = { BBCWebCrawler.class })
public class BBCWebCrawlerTest {

    private static final String LINKS_REGEX = "a[href~=^/food/recipes/(?!.*#).*(?<!([avi|png|jpgp|css|pdf|#|js]))$]";

    // @Autowired
    @SpyBean
    BBCWebCrawler bbcWebCrawler;

    @Test
    void testLinkRegex() {
        assertEquals(LINKS_REGEX, bbcWebCrawler.getLinksRegex());
    }

    @Test
    void testLinks() {
        HashSet<String> expected = Stream.of("a", "b", "c", "a")
                .collect(Collectors.toCollection(HashSet::new));
        Mockito.when(bbcWebCrawler.getLinks())
                .thenReturn(Stream.of("a", "b", "c")
                        .collect(Collectors.toCollection(HashSet::new)));

        assert (bbcWebCrawler.getLinks()).equals(expected);
        //Mockito.verify(bbcWebCrawler.getLinks(), times(3));

    }
}
