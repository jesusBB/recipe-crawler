package com.foodplanner.recipe.crawler.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.foodplanner.recipe.crawler.recipecrawler.validators.JamieOliverLinksValidator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JamieOliverLinksValidatorTest {

    JamieOliverLinksValidator jamieOliverLinksValidator;/*  = new JamieOliverLinksValidator();*/

    @BeforeAll
    public void setUp() {
        jamieOliverLinksValidator = new JamieOliverLinksValidator();
    }

    @Test
    public void filteringUrlEndingWith1Digit() {
        String url = "https://www.jamieoliver.com/recipes/butternut-squash-recipes/?rec-page=3";
        assertFalse(jamieOliverLinksValidator.test(url));


    }

    @Test
    public void filteringUrlEndingWithMoreThan1Digit() {
        String url = "https://www.jamieoliver.com/recipes/butternut-squash-recipes/?rec-page=333";
        assertFalse(jamieOliverLinksValidator.test(url));
    }

    @Test
    public void testInvalidURLs() {
        String url = "https://www.jamieoliver.co";
        assertFalse(jamieOliverLinksValidator.test(url));
    }

    // @MethodSource will provide test data from the specified method (e.g: provideValidUrls)
    @ParameterizedTest
    @MethodSource("provideValidUrls")
    public void testValidURLs(String url) {
        assertTrue(jamieOliverLinksValidator.test(url));
    }

    private static Stream<Arguments> provideValidUrls() {
        return Stream.of(
                Arguments.of(
                        "https://www.jamieoliver.com/recipes/butternut-squash-recipes/papas-con-chocos"));
    }

}
