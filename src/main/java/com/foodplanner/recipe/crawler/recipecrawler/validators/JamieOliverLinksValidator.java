package com.foodplanner.recipe.crawler.recipecrawler.validators;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class JamieOliverLinksValidator extends GenericValidator<String> {

    private static final List<Predicate<String>> VALIDATORS = new LinkedList<>();

    private static final Pattern END_WITH_REC_PAGE = Pattern.compile(
            "^(https://www\\.jamieoliver\\.com/recipes|/recipes).*(?<!(avi|png|(jpg))|css|pdf|(js)|(rec-page=[0-9])).*(?<!\\d)$");

    private static final Pattern STARTS_WITH_HTTPS = Pattern.compile("^(https).*");

    static {
        VALIDATORS.add(url -> END_WITH_REC_PAGE.matcher(url).matches());
        VALIDATORS.add(url -> STARTS_WITH_HTTPS.matcher(url).matches());
    }

    public JamieOliverLinksValidator() {
        super(VALIDATORS);
    }

}
